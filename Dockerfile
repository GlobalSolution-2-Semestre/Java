### STAGE 1: Build ###
# Use a imagem do Maven com a versão do JDK que seu projeto usa (ex: 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto da raiz (SEM 'mindjava/')
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Adiciona permissão de execução
RUN chmod +x ./mvnw

# Baixa as dependências
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila e empacota
RUN ./mvnw package -DskipTests


### STAGE 2: Run ###
# Use uma imagem JRE leve para rodar a aplicação (ex: JDK 21)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# O Quarkus roda na porta 8080 por padrão
EXPOSE 8080

# Copia a aplicação completa da pasta target
COPY --from=build /app/target/quarkus-app/ .

# Comando para iniciar
CMD ["java", "-jar", "quarkus-run.jar"]