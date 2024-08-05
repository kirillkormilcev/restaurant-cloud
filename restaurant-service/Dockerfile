FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar restaurant-service.jar
ENTRYPOINT ["java","-jar","/restaurant-service.jar"]