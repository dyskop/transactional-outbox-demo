FROM openjdk:16-jdk-alpine
COPY ./build/libs/transactional-outbox-demo-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]