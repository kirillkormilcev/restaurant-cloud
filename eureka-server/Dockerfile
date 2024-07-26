FROM amazoncorretto:17-alpine-jdk
COPY target/*.jar eureka-server.jar
ENTRYPOINT ["java","-jar","/eureka-server.jar"]