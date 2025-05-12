FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
# Download dependencies first (for better caching)
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application on Tomcat
FROM tomcat:10-jdk17
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
# Set the server port to match your application.properties
ENV CATALINA_OPTS="-Dserver.port=9999"
EXPOSE 9999
CMD ["catalina.sh", "run"]