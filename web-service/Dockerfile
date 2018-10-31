FROM openjdk:8-jdk-alpine

EXPOSE 8081
VOLUME /tmp

ARG JAR_FILE=target/web-service-0.1.jar

ADD ${JAR_FILE} web-service-0.1.jar

CMD ["java","-jar","/web-service-0.1.jar"]