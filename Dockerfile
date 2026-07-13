FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar gestioneordini.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","gestioneordini.jar"]
