FROM openjdk:8-jre-slim
WORKDIR /home
COPY /target/ExamAnalysis-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]