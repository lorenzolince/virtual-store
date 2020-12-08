FROM openjdk:8-jdk-alpine
MAINTAINER Lorenzo Lince<lorenzolince@gmail.com>

RUN apk add --update netcat-openbsd && rm -rf /var/cache/apk/*
RUN mkdir -p /content/images
COPY store-app/target/*.jar /app.jar
RUN echo "/bin/sh -c " > entrypoint.sh
RUN echo "while ! nc -z sql-server-store 1433; " >> entrypoint.sh
RUN echo "do" >> entrypoint.sh
RUN echo "echo sleeping;" >> entrypoint.sh
RUN echo "sleep 3;" >> entrypoint.sh
RUN echo "done;" >> entrypoint.sh
RUN echo "echo Connected!;" >> entrypoint.sh
RUN echo "java -jar /app.jar" >> entrypoint.sh
RUN chmod 755 entrypoint.sh
RUN cat entrypoint.sh
EXPOSE 8080
CMD /bin/sh ./entrypoint.sh
