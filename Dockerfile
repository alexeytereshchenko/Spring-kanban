FROM gradle:6.4-jdk8

WORKDIR /home/gradle/project

EXPOSE 8080

USER root

ENV GRADLE_USER_HOME /home/gradle/project

COPY . /home/gradle/project

WORKDIR /home/gradle/project

RUN ./gradlew bootJar


FROM openjdk:8-alpine

WORKDIR /home/gradle/project

COPY --from=0 /home/gradle/project/build/libs/*-SNAPSHOT.jar .

ENTRYPOINT java -jar *-SNAPSHOT.jar
