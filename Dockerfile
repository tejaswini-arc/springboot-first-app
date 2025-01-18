FROM openjdk:17
EXPOSE 8080
ADD target/springboot-first-app.jar springboot-first-app.jar
ENTRYPOINT ["java","-jar","/springboot-first-app.jar"]
