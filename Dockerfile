# Stage 1: Build the application using Maven with Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app
# Copy the pom.xml first and download dependencies for caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application (skip tests for speed)
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image using OpenJDK 21
FROM openjdk:21-slim
WORKDIR /app
# Copy the packaged JAR file from the builder stage.
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
