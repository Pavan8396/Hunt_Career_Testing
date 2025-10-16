# Hunt Career Playwright Automation Framework

## Overview

This framework is built to automate end-to-end testing for the Hunt Career application using Playwright. It provides a robust and efficient structure for writing, managing, and executing tests, aiming to ensure the application's quality, reliability, and cross-browser compatibility. The framework leverages the Page Object Model (POM) for maintainable test scripts, uses external files for test data management, and utilizes Playwright's built-in reporting capabilities.

## Architecture & Key Features

-   **Page Object Model (POM)**: The framework is built on the POM design pattern to create a scalable and maintainable test suite by encapsulating page-specific logic.
-   **Data-Driven Testing**: Supports data-driven testing using a combination of static `.json` files and dynamic `.js` modules for generating realistic test data.
-   **Cross-Browser Testing**: Designed to run tests in parallel across different browsers (Chromium, Firefox, WebKit) to ensure wide compatibility.
-   **Built-in Reporting**: Utilizes Playwright's native HTML reporter to generate detailed and interactive reports, including test steps, screenshots, and video recordings of test sessions.
-   **CI/CD Integration**: The framework is integrated with Jenkins for continuous testing. The `Jenkinsfile` in this directory is configured to run the tests in a Docker container, ensuring a consistent test environment.

## Project Structure

The project follows a standard Playwright structure, organized for clarity and maintainability.

```
Hunt_Career_Playwright/
├── data/               # Contains test data files (e.g., loginData.json, registerData.js).
├── pages/              # Implements the Page Object Model (e.g., LoginPage.js, BasePage.js).
├── tests/              # Contains all test script files (spec files), e.g., login.spec.js.
├── utils/              # Holds reusable utility functions and helpers.
│
├── playwright.config.js # Main Playwright configuration file (baseURL, browser projects, reporters).
├── package.json        # Defines project dependencies and npm scripts.
├── Jenkinsfile         # Jenkins pipeline for CI/CD.
└── README.md           # This documentation file.
```

## Prerequisites

Before you can run the tests, ensure you have the following installed:

-   **Node.js**: Playwright is a Node.js library. Download from [nodejs.org](https://nodejs.org/).
-   **npm** (Node Package Manager): Comes installed with Node.js.

## Installation

1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd Hunt_Career_Playwright
    ```

2.  **Install project dependencies**:
    From the project's root directory, run:
    ```bash
    npm ci
    ```
    This command downloads all necessary packages defined in `package.json` and `package-lock.json`, ensuring that you have the exact same dependencies as the CI environment.

3.  **Install Playwright browsers**:
    Playwright requires browser binaries to run tests. Install them using:
    ```bash
    npx playwright install --with-deps
    ```

## Running Tests

Playwright provides a powerful command-line interface to execute tests:

1.  **Run All Tests**:
    This command runs all tests across all configured browsers in headless mode.
    ```bash
    npx playwright test
    ```

2.  **Run Tests in Headed Mode**:
    To watch the tests execute in a browser UI, use the `--headed` flag.
    ```bash
    npx playwright test --headed
    ```

3.  **Run Tests in a Specific Browser**:
    ```bash
    npx playwright test --project=chromium
    ```

4.  **Using `package.json` Scripts**:
    Check the `scripts` section in `package.json` for predefined aliases. For example:
    ```json
    "scripts": {
      "test": "npx playwright test",
      "test:headed": "npx playwright test --headed",
      "report": "npx playwright show-report"
    }
    ```
    Run these using:
    ```bash
    npm test
    npm run test:headed
    npm run report
    ```

## Reporting

Playwright comes with a built-in HTML reporter that provides a detailed overview of test results.

-   **Generation**: The report is automatically generated in the `playwright-report` directory after a test run.
-   **Viewing the Report**: To open the report, run the following command:
    ```bash
    npx playwright show-report
    ```
    This command starts a local web server and opens the report in your browser.

## Test Suites

This framework includes the following test suites covering key application features:

-   **Login**: Verifies user authentication with valid and invalid credentials.
-   **Registration**: Tests the new user sign-up process and data validation.
-   **Search**: Covers job search functionality, including the use of keywords and filters.
-   **Employer Login**: Verifies employer authentication.
-   **Employer Registration**: Tests the new employer registration flow.

This README provides a comprehensive guide for getting started with the Hunt Career Playwright Automation Framework.