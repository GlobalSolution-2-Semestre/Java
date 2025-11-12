# ==========================
#  Etapa 1 - Build da aplicação
# ==========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Cria um diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos de configuração do Maven e o código-fonte
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o .jar (modo produção)
RUN mvn clean package -DskipTests

# ==========================
#  Etapa 2 - Execução da aplicação
# ==========================
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o .jar gerado da etapa anterior
COPY --from=build /app/target/hospitaltech-1.0.0-SNAPSHOT-runner.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


# Expõe a porta usada pela aplicação (ajuste se necessário)
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
