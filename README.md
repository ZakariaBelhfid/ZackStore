# E-commerce Spring Boot Project

## Project Overview

This project is an e-commerce application built with Spring Boot. It provides a REST API for managing customer orders and order items. The application includes features for order management, order items management, DTO and mapper implementation, validation and error handling, security, and testing.

## Features

### Order Management

- Create and manage customer orders.
- Add multiple order items to a single order.
- Endpoints for fetching, updating, and deleting orders.

### Order Items Management

- Associate products with orders.
- Endpoints for creating, updating, deleting, and fetching order items.

### DTO and Mapper Implementation

- Utilizes Data Transfer Objects (DTOs) and MapStruct for mapping between entities and DTOs.

### Validation and Error Handling

- Input validation for creating orders and order items.
- Proper error handling for invalid data (e.g., missing fields, invalid product IDs).
- Returns appropriate HTTP status codes (200, 201, 400, 404, etc.).

### Security

- Authentication required for creating and managing orders.
- Role-based access control for different user types (e.g., admin, customer).

### Testing

- Unit tests for controllers, services, and mappers.
- Integration tests for end-to-end functionality.
- Mock tests to ensure correct API behavior.
- Performance and security tests.

## Tech Stack

- **Spring Boot**
- **Spring Security**
- **MapStruct**
- **JPA (with Hibernate)**
- **Database**: MySQL/PostgreSQL
- **Testing frameworks**: JUnit, Mockito, MockMvc

## Installation Instructions

### Prerequisites

- Java 11 or higher
- Maven
- MySQL/PostgreSQL

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/ecommerce-spring-boot.git
    ```
2. Navigate to the project directory:
    ```sh
    cd ecommerce-spring-boot
    ```
3. Configure the database connection in `src/main/resources/application.properties`.

4. Build the project using Maven:
    ```sh
    mvn clean install
    ```

## Usage Guide

### Example API Requests and Responses

#### Create an Order

```http
POST /api/orders
Content-Type: application/json

{
  "userId": 1,
  "orderItems": [
    {
      "productId": 1,
      "quantity": 2,
      "price": 100.0
    }
  ],
  "totalAmount": 200.0,
  "status": "PENDING"
}
```

#### Fetch All Orders

```http
GET /api/orders
```

#### Update an Order

```http
PUT /api/orders/1
Content-Type: application/json

{
  "userId": 1,
  "orderItems": [
    {
      "productId": 1,
      "quantity": 2,
      "price": 100.0
    }
  ],
  "totalAmount": 200.0,
  "status": "COMPLETED"
}
```

#### Delete an Order

```http
DELETE /api/orders/1
```

## Running the Application Locally

1. Start the application:
    ```sh
    mvn spring-boot:run
    ```
2. The application will be available at `http://localhost:8080`.

## Running Tests

1. Run unit tests:
    ```sh
    mvn test
    ```
2. Run integration tests:
    ```sh
    mvn verify
    ```

## Security and Authentication

- The application uses Spring Security for authentication and role-based access control.
- Users must be authenticated to create and manage orders.
- Different roles (e.g., admin, customer) have different access levels.

## Contribution Guidelines

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.

## License Information

This project is licensed under the MIT License. See the `LICENSE` file for more details.
