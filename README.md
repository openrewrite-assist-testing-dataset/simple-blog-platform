# Simple Blog Platform

A modern Dropwizard-based blog platform with H2 in-memory database and API key authentication.

## Features

- Blog post CRUD operations (Create, Read, Update, Delete)
- API key authentication via Basic Auth
- H2 in-memory database with Hibernate ORM
- RESTful API endpoints
- Comprehensive test coverage with JUnit 4
- Docker support
- Kubernetes deployment with Helm charts

## Prerequisites

- Java 17
- Gradle 8.5+
- Docker (optional)

## Quick Start

### 1. Build and Run

```bash
# Build the application
./gradlew build

# Run the application
./gradlew run
```

The application will start on:
- Application server: http://localhost:8080
- Admin server: http://localhost:8081

### 2. Test the API

Default API key authentication:
- Username: `blog-api-key-123`
- Password: `api-key`

```bash
# Get all posts
curl -u blog-api-key-123:api-key http://localhost:8080/posts

# Create a new post
curl -u blog-api-key-123:api-key -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{"title":"My First Post","content":"This is my first blog post!","author":"johndoe","published":true}'

# Get published posts only
curl -u blog-api-key-123:api-key http://localhost:8080/posts/published

# Get posts by author
curl -u blog-api-key-123:api-key http://localhost:8080/posts/author/johndoe
```

## API Endpoints

- `GET /posts` - Get all posts
- `GET /posts/{id}` - Get post by ID
- `GET /posts/published` - Get published posts only
- `GET /posts/author/{author}` - Get posts by author
- `POST /posts` - Create new post
- `PUT /posts/{id}` - Update post
- `DELETE /posts/{id}` - Delete post

## Configuration

Configuration is managed through `src/main/resources/config.yml`:

```yaml
database:
  driverClass: org.h2.Driver
  url: jdbc:h2:mem:blog;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
  user: sa
  password: ""

apiKey: "blog-api-key-123"
```

## Testing

```bash
# Run all tests
./gradlew test

# Run tests with coverage
./gradlew test jacocoTestReport
```

## Docker

```bash
# Build Docker image
docker build -t simple-blog-platform .

# Run with Docker
docker run -p 8080:8080 -p 8081:8081 simple-blog-platform
```

## Kubernetes Deployment

```bash
# Deploy with Helm
helm install simple-blog-platform ./helm/simple-blog-platform

# Or with custom values
helm install simple-blog-platform ./helm/simple-blog-platform -f custom-values.yaml
```

## Technology Stack

- **Framework**: Dropwizard 3.0.7
- **Java**: 17
- **Database**: H2 in-memory with Hibernate ORM
- **Authentication**: API Key via Basic Auth
- **Testing**: JUnit 4, Mockito
- **Build Tool**: Gradle 8.5
- **Logging**: Log4j2
- **Containerization**: Docker
- **Orchestration**: Kubernetes with Helm

## Development

This application uses modern versions of dependencies:
- Dropwizard 3.0.x (latest)
- Java 17
- Gradle 8.5
- H2 database for simplicity
- Hibernate ORM for database mapping

## Data Model

### Post Entity
```java
{
  "id": 1,
  "title": "My Blog Post",
  "content": "This is the content of my blog post...",
  "author": "johndoe",
  "published": true,
  "createdAt": "2024-01-01T12:00:00Z",
  "updatedAt": "2024-01-01T12:00:00Z"
}
```

## License

This project is created for testing purposes as part of the OpenRewrite assist testing dataset.