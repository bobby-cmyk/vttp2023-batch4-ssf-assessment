FROM eclipse-temurin:21-noble AS builder

WORKDIR /src

# copy files
COPY mvnw .
COPY pom.xml .

COPY .mvn .mvn
COPY src src

# make mvnw executable
RUN chmod a+x mvnw && /src/mvnw package -Dmaven.test.skip=true
# /src/target/revision-0.0.1-SNAPSHOT.jar

FROM eclipse-temurin:21-jre-noble

WORKDIR /app

COPY --from=builder /src/target/eventmanagement-0.0.1-SNAPSHOT.jar app.jar
COPY events.json .

# check if curl command is available
RUN apt update && apt install -y curl

ENV PORT=8080
ENV JSON_FILE_PATH=/app/events.json
ENV SPRING_DATA_REDIS_HOST=localhost
ENV SPRING_DATA_REDIS_PORT=6379
ENV SPRING_DATA_REDIS_DATABASE=0
ENV SPRING_DATA_REDIS_USERNAME=""
ENV SPRING_DATA_REDIS_PASSWORD=""

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar