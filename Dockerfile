# Use official OpenJDK image
FROM eclipse-temurin:17-jdk-jammy


# Set working directory
WORKDIR /app

# Copy the jar file
COPY target/EventManagementSystem-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
