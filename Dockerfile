# ==========================
#  Etapa 1 - Build
# ==========================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o POM e o código fonte
COPY pom.xml .
COPY src ./src

# Gera o JAR (sem testes)
RUN mvn clean package -DskipTests

# ==========================
#  Etapa 2 - Execução
# ==========================
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /app/target/mindjava-1.0.0-SNAPSHOT.jar app.jar

# Expõe a porta usada pela aplicação
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
