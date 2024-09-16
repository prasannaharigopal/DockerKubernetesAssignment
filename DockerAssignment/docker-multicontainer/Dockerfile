# -------------------- Build Stage --------------------

# Use a valid Maven official image with OpenJDK 11
FROM maven:3.6.3-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /multicontainer

# Copy the Maven project file (pom.xml) and download dependencies
COPY calculator/pom.xml .

# Copy the entire project source code into the container
COPY calculator/src ./src

# Package the application into a JAR file
RUN mvn clean package -DskipTests

# -------------------- Runtime Stage --------------------

# Use OpenJDK image for running the application
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /multicontainer

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /multicontainer/target/calculator-0.0.1-SNAPSHOT.jar /calculator-app.jar

# Expose the application port (change if necessary)
EXPOSE 8081

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/calculator-app.jar"]



