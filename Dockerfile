FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
# Download dependencies first (for better caching)
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Use Spring Boot's built-in executable JAR support instead of Tomcat
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.war /app/app.war
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "/app/app.war", "--server.port=9999"]