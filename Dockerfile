FROM openjdk:8-alpine as builder
WORKDIR /build
COPY . .
RUN ["./gradlew", "bootJar", "-x", "testClasses"]

FROM openjdk:8-alpine
WORKDIR /app
COPY --from=builder /build/build/libs/Spring-Kanban.jar .
ENTRYPOINT ["java", "-jar", "Spring-Kanban.jar"]
