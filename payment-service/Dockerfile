FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar"]