# Step 1: Build the app
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the app (using the JRE for a smaller, faster image)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# This copies the built jar from the 'build' stage above
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]