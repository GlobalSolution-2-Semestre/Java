# ==========================
# 1) Etapa de build
# ==========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

COPY src ./src

RUN mvn -q -DskipTests package

# ==========================
# 2) Etapa de execução
# ==========================
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*-runner.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
