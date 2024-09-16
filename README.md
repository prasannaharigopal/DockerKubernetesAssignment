# Docker & Kubernetes Assignment: Java Spring Boot Application with Docker & Kubernetes 


# Docker Application 

This project demonstrates how to Dockerize a Java Spring Boot web application using Docker, Docker Compose, and Docker Hub. It includes both a simple single-container application and a multi-container setup with a MySQL database.

# Project structure
/docker-context
├── calculator/
│   ├── src/                 # Java source code
│   └── pom.xml              # Maven configuration
├── Dockerfile               # Dockerfile for building and running the app
└── docker-compose.yml       # Docker Compose configuration for multi-container

# Dockerfile

This project uses a multi-stage build Dockerfile:

# Build Stage
- A Maven image with OpenJDK 11 is used to compile the Java Spring Boot application and create a JAR file.

# Runtime Stage
- A lightweight OpenJDK image is used to run the compiled JAR file.

dockerfile
# Build Stage
FROM maven:3.6.3-openjdk-11 AS build
WORKDIR /app
COPY calculator/pom.xml .
COPY calculator/src ./src
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/calculator-0.0.1-SNAPSHOT.jar /calculator-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/calculator-app.jar"]
Build Docker Image
To build the Docker image for the application, run the following command from the docker-context folder:

# Build the image 

docker build -t calculator-app .


# Run the container from the Docker image:

docker run -p 8080:8080 calculator-app

# Multi-Container Application with Docker Compose

For the multi-container setup, Docker Compose is used to run multiple instances of the application along with a MySQL database.

docker-compose.yml 

version: '3.8'
services:
  app:
    image: calculator-app-bonus:latest
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8085:8085"
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/calculatordb
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: root
      SPRING_JPA_HIBERNATE_DDL_AUTO: update

  mysql:
    image: mysql:8.0
    container_name: mysqldb
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: calculatordb
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql  # Persisting the MySQL data

volumes:
  mysql_data:   # Named volume for database persistence

Commands to Run
# Build and start all services in detached mode:
docker-compose up -d --build

# View the running services:
docker-compose ps

# Stop the services:
docker-compose down

# Using Docker Hub
# To push the image to Docker Hub:

# Tag the image:
docker tag calculator-app <your-dockerhub-username>/calculator-app:latest

# Push the image:
docker push <your-dockerhub-username>/calculator-app:latest

# Pull and run the image from Docker Hub:
docker pull <your-dockerhub-username>/calculator-app:latest
docker run -p 8080:8080 <your-dockerhub-username>/calculator-app:latest

# Testing
You can test the running containers using curl:
curl http://localhost:8080

For multi-container, replace the port with 8081, 8082, etc., based on your docker-compose.yml setup.

# Bonus: Multi-Container with Database
This project also demonstrates a multi-container setup where the web application interacts with a MySQL database to store operation data (operands, operation type, and result).

Volume Persistence
The database is configured with a named volume to persist data across container restarts.

volumes:
  mysql_data:
#####################################################################################################################

# Kubernetes Application

This project demonstrates how to deploy a Java Spring Boot Calculator application in Kubernetes using Minikube. The project involves building and pushing a Docker image to Docker Hub, creating Kubernetes deployments, services, scaling replicas, and configuring health checks with liveness and readiness probes.

# Project Structure

/kubernetes-context
├── calculator/
│   ├── src/                 # Java source code
│   └── pom.xml              # Maven configuration
├── Dockerfile               # Dockerfile for building and running the app
├── deployment.yaml          # Kubernetes deployment configuration
└── service.yaml             # Kubernetes service configuration

# Steps for Building and Running the Application in Kubernetes
# Build Docker Image
Build the Docker image for the Java Spring Boot Calculator application:

docker build -t <your-dockerhub-username>/calculator-app .
# Push Docker Image to Docker Hub
Push the Docker image to your Docker Hub repository:

docker login
docker tag calculator-app <your-dockerhub-username>/calculator-app:latest
docker push <your-dockerhub-username>/calculator-app:latest

# Deploying to Kubernetes with Minikube
Minikube allows you to run a single-node Kubernetes cluster locally. To start Minikube:

minikube start

You can verify the running Minikube containers using Docker:

docker ps

# Apply Kubernetes Configurations
Use kubectl to apply the deployment and service configurations:

kubectl apply -f deployment.yaml
kubectl apply -f service.yaml

# Check Status of Pods and Services
You can check the status of the running services and pods with:
kubectl get pods
kubectl get services

To access the application externally, use the Minikube service IP or expose the service using NodePort or LoadBalancer in the service.yaml file.

# Access the Application
To test the running application, you can use a curl request:

curl http://<minikube-ip>:<node-port>

Minikube IP can be fetched using:
minikube ip

# Scaling the Application
You can scale the number of replicas of your application using the following command:

kubectl scale deployment <deployment-name> --replicas=<number-of-replicas>

Verify the scaled replicas using:
kubectl get pods

Bonus: Health Check with Liveness and Readiness Probes
The application is enhanced with Kubernetes health checks. Liveness and readiness probes have been added to the deployment configuration to ensure the application is functioning properly.

Liveness and Readiness Probes Example in deployment.yaml

livenessProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 10

readinessProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  initialDelaySeconds: 10
  periodSeconds: 5
These probes ensure that the application is restarted if it becomes unhealthy and that it is only marked as ready when it can serve requests.
