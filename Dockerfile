FROM eclipse-temurin:21-jdk-alpine
RUN mkdir /opt/app
COPY target/*.jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]