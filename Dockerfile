FROM openjdk:8-jdk-alpine
MAINTAINER Lorenzo Lince<lorenzolince@gmail.com>

RUN apk add --update netcat-openbsd && rm -rf /var/cache/apk/*
RUN mkdir -p /content/images
EXPOSE 8080
COPY store-app/target/*.jar /app.jar
ENTRYPOINT exec java -jar /app.jar
