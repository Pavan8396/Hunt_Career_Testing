# Hunt Career - RestAssured API Test Suite

## Description

This project contains a suite of API tests for the Hunt Career job portal, written in Java using the RestAssured framework. The tests are based on the Postman collection located in the `Hunt_Career_Postman_API_Collection` directory.

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd Hunt_Career_RestAssured
   ```

## Running the tests

To run the tests, execute the following Maven command from the `Hunt_Career_RestAssured` directory:

```bash
mvn test
```

The tests will be run using the `testng.xml` suite file, which is configured in the `pom.xml`.

**Note:** The tests require the backend application to be running. If the application is not running, the tests will fail with a `Connection refused` error.
