# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image using a JRE base image
FROM openjdk:17-jre-slim
WORKDIR /app
# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar
# Expose the port the application runs on
EXPOSE 8761
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]