FROM maven:3.6.0-jdk-17-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:11
COPY --from=build /app/build/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

