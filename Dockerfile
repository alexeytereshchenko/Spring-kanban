FROM gradle:6.5-jdk8

WORKDIR /home/gradle/project

EXPOSE 8080

USER root

ENV GRADLE_USER_HOME /home/gradle/project

COPY . /home/gradle/project

RUN gradle build


FROM openjdk:8-alpine

WORKDIR /home/gradle/project

COPY --from=0 /home/gradle/project/build/libs/project-0.0.1-SNAPSHOT.jar .

ENTRYPOINT java -jar project-0.0.1-SNAPSHOT.jar
