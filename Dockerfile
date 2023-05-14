ENV JAVA_HOME /usr/java/openjdk-17
ENV PATH $JAVA_HOME/bin:$PATH

WORKDIR /app

COPY build/libs/JavaCordBot-1.0-all.jar .

CMD ["java", "-jar", "JavaCordBot-1.0-all.jar"]
