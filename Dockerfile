FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-7.2-bin.zip && \
    unzip -d /opt/gradle gradle-7.2-bin.zip && \
    export PATH=$PATH:/opt/gradle/gradle-7.2/bin

WORKDIR /app

COPY . .

RUN chmod +x gradlew && \
    ./gradlew shadowJar
    
FROM openjdk:17-jdk-slim

COPY --from=build /build/libs/JavaCordBot-1.0-all.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]