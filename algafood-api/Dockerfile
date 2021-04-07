FROM openjdk:11-jre-slim

ARG JAR_FILE

WORKDIR app

COPY target/${JAR_FILE} api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]
