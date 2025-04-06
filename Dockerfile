# Build stage
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/Online-FIR-System-0.0.1-SNAPSHOT.jar Online-FIR-System.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Online-FIR-System.jar"]
