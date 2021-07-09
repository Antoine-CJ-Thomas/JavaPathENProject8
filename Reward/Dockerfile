FROM openjdk:8

ARG JAR_FILE=./build/libs/reward-1.0.0.jar

WORKDIR /app/tour_guide

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar", "app.jar"]