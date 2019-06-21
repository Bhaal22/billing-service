FROM openjdk:8-jre-alpine
WORKDIR /usr/share
COPY target/mediationservice-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ARG LOG_DIR='/usr/share/log/mediationservice'
RUN echo 'LOG_DIR = '
RUN echo $LOG_DIR

ENTRYPOINT ["/usr/bin/java", "-jar",  "-Dlogging.path=$LOG_DIR", "app.jar", " &"]
