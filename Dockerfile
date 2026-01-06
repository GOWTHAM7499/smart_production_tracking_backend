# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Expose port (Render uses dynamic port)
EXPOSE 8080

# Run the Spring Boot app (NO wildcard issue)
CMD java -jar target/$(ls target | grep '\.jar$')
