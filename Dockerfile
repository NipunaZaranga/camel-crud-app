FROM openjdk:8
EXPOSE 8080
ADD target/springboot-camel-crud-app.jar springboot-camel-crud-app.jar
ENTRYPOINT ["java" "jar" "springboot-camel-crud-app.jar"]