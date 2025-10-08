# Hunt Career TestNG Hybrid Automation Framework

## Overview

This framework is a robust and scalable solution for automating tests for the Hunt Career web application. It is built using Java, Selenium WebDriver, and TestNG, following a hybrid model that combines the Page Object Model (POM), Data-Driven Testing, and a modular structure to ensure maintainability, reusability, and clarity of test scripts.

The framework is designed to be easily configurable for different environments and provides detailed reporting to give clear insights into test execution results.

## Architecture & Key Features

-   **Hybrid Model**:
    -   **Page Object Model (POM)**: The framework uses POM to encapsulate UI elements and interactions for each page, making the test code cleaner and easier to maintain.
    -   **Data-Driven Testing**: Test data is decoupled from test logic. This implementation uses JSON files to supply data to test cases, allowing testers to easily run the same logic with multiple data sets.

-   **Core Technologies**:
    -   **Java**: The programming language for writing all framework components and test scripts.
    -   **Selenium WebDriver**: For browser automation and interaction with web elements.
    -   **TestNG**: As the core testing framework for test management, execution, assertions, and parallel testing capabilities.
    -   **Maven**: For managing project dependencies and the build lifecycle.
    -   **ExtentReports**: For generating detailed, interactive, and visually appealing HTML test reports.
    -   **JavaFaker**: For generating realistic and random test data on the fly.
    -   **slf4j & Log4j2**: For logging framework events and test execution details, which is crucial for debugging.
    -   **JSON-simple**: For parsing and handling test data from `.json` files.

## Project Structure

The project is organized into a standard Maven structure, separating application logic, test code, and resources.

```
Hunt_Career_TestNG_Hybrid_Framework/
├── src/
│   ├── main/java/com/huntcareer/qa/
│   │   ├── base/           # TestBase class for browser setup, teardown, and driver management.
│   │   ├── config/         # Configuration reader for `config.properties`.
│   │   ├── listeners/      # TestNG listeners for custom actions during test execution (e.g., logging, reporting).
│   │   ├── pages/          # Page Object classes (e.g., LoginPage.java, RegisterPage.java).
│   │   └── utils/          # Utility classes for common functions (e.g., ExtentReporter, DataProvider, Utilities).
│   │
│   └── test/
│       ├── java/com/huntcareer/qa/testcases/
│       │                   # Test classes containing TestNG test methods (e.g., LoginTest.java, RegisterTest.java).
│       └── resource/
│           ├── fixtures/   # JSON files used for data-driven testing (e.g., loginData.json).
│           ├── log4j2.properties # Configuration for logging levels and appenders.
│           └── testng.xml  # TestNG suite file to configure and orchestrate test execution.
│
├── test-output/            # Default directory for TestNG reports and ExtentReports.
├── pom.xml                 # Maven Project Object Model file defining dependencies and build configurations.
└── README.md               # This file.
```

## Prerequisites

Before you begin, ensure you have the following installed on your system:

-   **Java Development Kit (JDK)**: Version 17 or higher.
-   **Apache Maven**: To manage dependencies and run the project.
-   **A Web Browser**: Such as Google Chrome or Mozilla Firefox.

## Installation

1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd Hunt_Career_TestNG_Hybrid_Framework
    ```

2.  **Install Dependencies**:
    Maven will handle the installation of all project dependencies. Run the following command from the project root directory:
    ```bash
    mvn clean install
    ```
    This command will download all the necessary libraries defined in the `pom.xml` file.

## Configuration

The framework's behavior can be customized through the `config.properties` file located at `src/main/java/com/huntcareer/qa/config/config.properties`.

Key properties you can configure include:
-   `url`: The base URL of the application under test.
-   `browser`: The default browser for test execution (e.g., `chrome`, `firefox`).
-   `headless`: Set to `true` to run tests in headless mode.
-   `explicitWait`: The maximum time in seconds for explicit waits.

## Test Data Management

This framework uses a data-driven approach with **JSON files** for managing test data.

-   **Location**: Test data files are stored in `src/test/resource/fixtures/`.
-   **Usage**: The `DataProvider` utility class (`src/main/java/com/huntcareer/qa/utils/DataProvider.java`) reads the JSON files. TestNG's `@DataProvider` annotation is then used in test classes to feed this data into the test methods.
-   **Benefit**: This approach allows for easy separation of test logic from test data, making it simple to add, remove, or modify test cases without changing the code.

## Running Tests

You can execute the automated tests in several ways:

1.  **Using Maven (Recommended for CI/CD)**:
    This command runs the entire test suite as defined in the `testng.xml` file.
    ```bash
    mvn test
    ```

2.  **Using TestNG Suite XML**:
    You can run the tests directly from your IDE (like Eclipse or IntelliJ IDEA) by right-clicking on the `testng.xml` file and choosing "Run as TestNG Suite". This gives you more flexibility during development and debugging.

## Reporting

After test execution, the framework generates two types of reports in the `test-output/` directory:

1.  **ExtentReports**:
    -   A comprehensive and interactive HTML report is generated at `test-output/ExtentReports/extentReport.html`.
    -   This report includes detailed steps, pass/fail status, execution times, and automatically captured screenshots for failed tests.

2.  **TestNG Reports**:
    -   TestNG also produces its own set of standard reports, including an `emailable-report.html` and `index.html`.

To view the main report, open `test-output/ExtentReports/extentReport.html` in any web browser.

## Test Suites

The framework includes the following test suites located under `src/test/java/com/huntcareer/qa/testcases/`:

-   **Login Tests**: Verifies login functionality with valid and invalid credentials.
-   **Registration Tests**: Validates the user registration process, including checks for existing users and data validation.
--   **Search Tests**: Covers the job search functionality, including keyword searches and filtering.