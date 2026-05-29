FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8081
ENTRYPOINT ["java","-jar","target/aulajpa-0.0.1-SNAPSHOT.jar"]
