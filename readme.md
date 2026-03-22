# Simple CRUD API

This is a RESTful CRUD application built with Spring Boot 3 and Java 17. It provides a simple API for managing products and includes built-in H2 database setup, global exception handling, and pagination.

## Technology Stack

- Java 17
- Spring Boot 3.0.5 (Web, Data JPA, Validation, Actuator)
- H2 Database (Embedded)
- Spring Doc OpenAPI (Swagger UI)
- Maven
- Docker

## Getting Started

### Prerequisites

- Java 17 (if running without Docker)
- Maven (if running without Docker)
- Docker and Docker Compose (if running with Docker)

### Running Locally with Maven

1. Build the application:
```bash
./mvnw clean package
```

2. Run the application:
```bash
java -jar target/simplecrud-0.0.1-SNAPSHOT.jar --server.port=8080
```

### Running with Docker

1. Build and start the container using Docker Compose:
```bash
docker-compose up --build -d
```

2. Stop the container:
```bash
docker-compose down
```

## API Documentation

Once the application is running, the Swagger UI is available at:
- `http://localhost:8080/swagger-ui/index.html`

Health check endpoint (Actuator):
- `http://localhost:8080/actuator/health`

## Key Features

- **Pagination**: The `GET /products` endpoint supports pagination parameters `?page=0&size=5`.
- **Validation**: Input validation for product creation and updates, returning `400 Bad Request` if validation rules are violated.
- **Exception Handling**: Global exception handler (`@RestControllerAdvice`) for returning standardized JSON error responses.
- **Automated Tests**: Unit tests for services (Mockito) and integration tests for controllers (MockMvc).
