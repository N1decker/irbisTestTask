FROM openjdk:17-alpine
ADD target/testtask-0.0.1-SNAPSHOT.jar app.jar
RUN mkdir /statistics
ENTRYPOINT ["java", "-jar", "app.jar"]