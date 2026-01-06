# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Give permission to mvnw
RUN chmod +x mvnw

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Copy the generated jar to app.jar
RUN cp target/*.jar app.jar

# Expose port (Render uses PORT env variable)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
