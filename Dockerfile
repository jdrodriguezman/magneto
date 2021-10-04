FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/mutant-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} mutant-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mutant-0.0.1-SNAPSHOT.jar"]