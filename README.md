# Spring Boot CRUD Application for Products with PostgreSQL

This is a simple CRUD application built with Spring Boot. The application manages `Products`, allowing users to create, read, update, and delete products.

## Features

- Create, read, update, and delete products
- View details of individual products
- View a list of all products

## Prerequisites

- Java 11 or later
- PostgreSQL
- Maven

## Getting Started

### 1. Clone the Repository

```sh
git clone https://github.com/mikailyusuf/SimpleSpringBootApp.git
cd SimpleSpringBootApp

```

### 2. Update application.properties
Update the src/main/resources/application.properties file with your PostgreSQL database details.

```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

```
## API Endpoints
Product Endpoints
```sh
GET /api/products: Get all products
GET /api/products/{id}: Get a product by ID
POST /api/products: Create a new product
PUT /api/products/{id}: Update a product
DELETE /api/products/{id}: Delete a product

```

