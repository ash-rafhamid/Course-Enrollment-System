# Use Java 17 (same as your system)
FROM eclipse-temurin:17-jre

# Create folder inside container
WORKDIR /app

# Copy your JAR into the container
COPY target/course-enrollment-system-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
