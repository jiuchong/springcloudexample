FROM openjdk:8-jdk-alpine
MAINTAINER linch
RUN mkdir -p /usr/local/service
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/local/service/dockerservice.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Dspring.cloud.config.uri=http://config-server:8081 -Djava.security.egd=file:/dev/./urandom -jar /usr/local/service/dockerservice.jar