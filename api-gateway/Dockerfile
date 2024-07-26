FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar api-gateway.jar
ENTRYPOINT ["java","-jar","/api-gateway.jar"]