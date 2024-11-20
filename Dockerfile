# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local machine to the container
COPY nats.jar /app/nats.jar

# Expose the port the app will run on (e.g., port 9090 for your Spring Boot app)
EXPOSE 9090

# Command to run the JAR file
CMD ["java", "-jar", "nats.jar"]
