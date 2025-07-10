FROM eclipse-temurin:17-jre

WORKDIR /app

COPY build/libs/*-fat.jar app.jar

EXPOSE 8080 8081

CMD ["java", "-jar", "app.jar", "server", "config.yml"]