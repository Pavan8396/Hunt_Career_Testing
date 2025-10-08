# Hunt Career Cypress Automation Framework

## Overview

This framework is designed for automating end-to-end tests for the Hunt Career application using Cypress. It provides a robust structure for writing, managing, and executing tests, ensuring the application's quality and reliability. The framework leverages the Page Object Model (POM) for maintainable test scripts, uses fixtures for test data management, and generates comprehensive HTML reports with Mochawesome.

## Architecture & Key Features

-   **Page Object Model (POM)**: The framework is built on the POM design pattern, encapsulating page elements and user interactions into reusable and maintainable classes.
-   **Data-Driven Testing**: It supports data-driven testing using Cypress fixtures. This includes static data from `.json` files and dynamic data generated on-the-fly using JavaScript modules and libraries like `@faker-js/faker`.
-   **Custom Commands**: The framework includes custom Cypress commands for common, reusable actions, making test scripts more concise and readable.
-   **Detailed Reporting**: It is integrated with `mochawesome` to generate detailed HTML reports with test results, summaries, and screenshots for failed tests.

## Project Structure

The project follows a standard Cypress structure, organized for clarity and scalability.

```
Hunt_Career_Cypress/
├── cypress/
│   ├── e2e/            # Contains all test script files (spec files), e.g., login.cy.js.
│   ├── fixtures/       # Holds test data files (e.g., loginData.json, registerData.js).
│   ├── pageObjects/    # Implements the Page Object Model (e.g., LoginPage.js, RegisterPage.js).
│   ├── reports/        # Stores test execution reports (JSON and HTML).
│   ├── screenshots/    # Automatically captures screenshots of failed tests.
│   ├── support/        # For custom commands (commands.js) and global setup (e.g.js).
│   └── utils/          # Contains reusable utility functions.
│
├── cypress.config.js   # Main Cypress configuration file (baseUrl, viewport, env variables).
├── package.json        # Defines project dependencies and scripts.
└── README.md           # This documentation file.
```

## Prerequisites

Before running the tests, ensure you have the following installed:

-   **Node.js**: Cypress is a Node.js application. Download from [nodejs.org](https://nodejs.org/).
-   **npm** (Node Package Manager): Comes installed with Node.js.

## Installation

1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd Hunt_Career_Cypress
    ```

2.  **Install project dependencies**:
    From the project's root directory (`Hunt_Career_Cypress`), run:
    ```bash
    npm install
    ```
    This command downloads all necessary packages defined in `package.json`.

## Running Tests

You can execute the Cypress tests in several ways:

1.  **Open Cypress Test Runner (Interactive Mode)**:
    Ideal for development and debugging. It allows you to run specific tests and see the execution in real-time.
    ```bash
    npx cypress open
    ```

2.  **Run Tests in Headless Mode**:
    Runs all tests headlessly in the terminal. This is perfect for CI/CD pipelines.
    ```bash
    npx cypress run
    ```
    To run tests in a specific browser or file:
    ```bash
    npx cypress run --browser chrome
    npx cypress run --spec "cypress/e2e/login.cy.js"
    ```

3.  **Using `package.json` Scripts**:
    The project may contain predefined scripts for convenience. Check the `scripts` section in `package.json`. For example:
    ```json
    "scripts": {
      "test": "cypress run",
      "test:open": "cypress open"
    }
    ```
    Run these using:
    ```bash
    npm test
    npm run test:open
    ```

## Reporting

This framework uses **Mochawesome** to generate detailed HTML reports.

-   **How it works**: After a test run (especially headless), Mochawesome creates JSON files for each spec. These are then merged into a single, comprehensive HTML report.
-   **Viewing Reports**: The final report is typically generated in the `cypress/reports/` directory. Open the `index.html` file within the `html` sub-directory to view the results.

## Test Suites

This framework includes the following test suites, covering key functionalities of the Hunt Career application:

-   **Login**: Verifies user authentication, including success and failure scenarios.
-   **Registration**: Tests the new user registration flow, including data validation.
-   **Search**: Covers job search functionality, including keyword searches and filtering.

This README provides a comprehensive guide to understanding, setting up, and running tests with the Hunt Career Cypress Automation Framework.