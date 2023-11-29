FROM ubuntu:latest AS build
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-7.2-bin.zip && \
    unzip -d /opt/gradle gradle-7.2-bin.zip && \
    export PATH=$PATH:/opt/gradle/gradle-7.2/bin && \
    apt-get install -y \
    x11-apps \
    xauth \
    xorg

# Set the DISPLAY environment variable
ENV DISPLAY=:0

# Additional configuration for X11 forwarding
RUN echo "X11Forwarding yes" >> /etc/ssh/sshd_config
WORKDIR /app

COPY . .

RUN chmod +x gradlew && \
    ./gradlew shadowJar

FROM openjdk:17-jdk-slim

COPY --from=build /app/build/libs/JavaCordBot-1.0-all.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
