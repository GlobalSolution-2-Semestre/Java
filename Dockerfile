### STAGE 1: Build ###
# Use a imagem do Maven com a versão do JDK que seu projeto usa (ex: 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do Maven Wrapper
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa as dependências (cacheia esta camada)
# Usamos ./mvnw para garantir que é a versão correta do Maven
RUN ./mvnw dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila e empacota a aplicação
# Isso criará o JAR do Quarkus em target/quarkus-app/
RUN ./mvnw package -DskipTests


### STAGE 2: Run ###
# Use uma imagem JRE leve para rodar a aplicação (ex: JDK 21)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# O Quarkus roda na porta 8080 por padrão
# O Render irá detectar esta porta automaticamente
EXPOSE 8080

# Copia o JAR executável do 'build' stage
COPY --from=build /app/target/quarkus-app/quarkus-run.jar .

# Comando para iniciar a aplicação
CMD ["java", "-jar", "quarkus-run.jar"]