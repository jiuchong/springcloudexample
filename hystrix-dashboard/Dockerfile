FROM openjdk:8-jdk-alpine
MAINTAINER linch
RUN mkdir -p /usr/local/service
ARG JAR_FILE
ADD target/${JAR_FILE} /user/local/service/dockerservice.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user/local/service/dockerservice.jar