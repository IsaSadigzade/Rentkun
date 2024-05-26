FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar rentkun.jar
CMD ["java", "-jar", "/rentkun.jar"]
EXPOSE 8080
