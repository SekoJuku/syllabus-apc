FROM maven:3.8.1-openjdk-17-slim AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package

RUN addgroup syllabus-apc
RUN adduser --disabled-password --gecos '' syllabus-apc-user
RUN chown -R syllabus-apc-user:syllabus-apc .
USER syllabus-apc-user

FROM openjdk:17.0.1-jdk-slim
COPY --from=builder /app/target/myapp.jar /
EXPOSE 8080
CMD ["java", "-jar", "/myapp.jar"]
