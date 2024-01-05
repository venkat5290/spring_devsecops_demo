# select image
FROM maven:3.8.5-jdk-11-slim AS build

# copy the project files
WORKDIR /opt/app
COPY ./pom.xml ./pom.xml

# build all dependencies for offline use
RUN mvn dependency:go-offline -B

COPY ./src ./src

# build for release
RUN mvn package

# Docker Build Stage
FROM openjdk:11-jre-slim
COPY --from=build /opt/app/target/*.jar app.jar
ENV PORT 8090
EXPOSE $PORT
ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}","app.jar"]