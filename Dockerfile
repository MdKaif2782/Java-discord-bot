FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon
FROM openjdk:17-jdk-slim

COPY --from=build /build/libs/JavaCordBot-1.0-all.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]