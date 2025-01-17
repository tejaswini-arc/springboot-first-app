FROM openjdk:8
EXPOSE 9090
ADD target/springboot-first-app.jar springboot-first-app.jar
ENTRYPOINT ["java","-jar","/springboot-first-app.jar"]
