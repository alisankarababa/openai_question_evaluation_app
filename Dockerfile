FROM eclipse-temurin:17

RUN apt-get update && \
    apt-get install -y ffmpeg && \
    rm -rf /var/lib/apt/lists/

COPY ./target/openai_question_evaluation_app-0.0.1-SNAPSHOT.jar app.jar
COPY  ./src/main/resources resources
ENTRYPOINT ["java", "-jar", "/app.jar"]