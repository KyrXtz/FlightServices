# Use the Debian operating system as the base image
FROM debian:stable-slim

# Set the working directory in the container
WORKDIR /app

# Install required packages for building and running the application
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Copy the application source code to the container
COPY . .

# Build the application using Maven
RUN mvn package

# Expose the port that the application runs on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "target/flightservices.jar"]
