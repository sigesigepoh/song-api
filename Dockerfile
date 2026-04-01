# Use a JDK 21 image if you changed your pom.xml to 21
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Use a reliable OpenJDK image to run the app
FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]