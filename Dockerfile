FROM openjdk:8-jre-alpine
COPY build/libs/*.war app.war
EXPOSE 8080
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -Dserver.port=8080 -jar app.war