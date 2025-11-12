# ==========================
# Etapa 1 - Build da aplicação
# ==========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Executa o build do Maven (gera o JAR com dependências)
RUN mvn clean package -DskipTests

# ==========================
# Etapa 2 - Execução
# ==========================
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=build /app/target/mindjava-1.0.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Expõe a porta usada pela aplicação (ajuste se precisar)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
