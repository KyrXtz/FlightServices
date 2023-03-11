FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD target/flightservices.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]