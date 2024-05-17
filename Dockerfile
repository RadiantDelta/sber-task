FROM openjdk:17
EXPOSE 8080
COPY out/artifacts/sber_task_jar/sber-task.jar sber-task.jar
ENTRYPOINT ["java","-jar","sber-task.jar"]
