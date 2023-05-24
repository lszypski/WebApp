FROM maven:3.8.3-openjdk-17 as build
WORKDIR /tmp
COPY ./ /tmp
RUN mvn clean install

FROM openjdk:17-jdk-slim
COPY --from=build /tmp/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
