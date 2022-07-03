FROM openjdk:11
MAINTAINER Santhakumari<Santha@gmail.com>
ARG JAR_FILE=target/TheEmartApp-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} emartapp.jar
ENTRYPOINT ["java", "-jar", "/emartapp.jar"]
