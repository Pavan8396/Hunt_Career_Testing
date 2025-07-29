# Hunt Career Cypress Automation Framework

## Overview

This framework is designed for automating tests for the Hunt Career application using Cypress. It provides a robust structure for writing, managing, and executing end-to-end tests, ensuring the application's quality and reliability. The framework leverages the Page Object Model (POM) for maintainable and scalable test scripts, uses fixtures for test data management, and generates comprehensive HTML reports using Mochawesome.

## Project Structure

The project follows a standard Cypress directory structure with some additions to organize test assets effectively:

*   **`cypress/e2e`**: This directory contains all the test script files (also known as spec files). Each `.cy.js` file typically represents a test suite for a specific feature or functionality of the application.
    *   Example: `login.cy.js`, `register.cy.js`, `search.cy.js`

*   **`cypress/fixtures`**: This directory holds test data. This framework uses a combination of static JSON files and dynamic JavaScript modules.
    *   `loginData.json`, `searchData.json`: These are static data files, best for test cases that don't change. They can be loaded using `cy.fixture()`.
    *   `registerData.js`: This is a JavaScript module that programmatically generates dynamic and random test data for user registration, using the `@faker-js/faker` library. It is imported directly into the test file, not loaded with `cy.fixture()`. This approach is powerful for creating a wide variety of realistic test inputs.

*   **`cypress/pageObjects`**: This directory implements the Page Object Model (POM) design pattern. Each `.js` file represents a page in the application and encapsulates the page's elements and the actions that can be performed on them. This promotes reusability and maintainability of test code.
    *   Example: `BasePage.js`, `LoginPage.js`, `RegisterPage.js`, `SearchPage.js`

*   **`cypress/reports`**: Test execution reports are generated in this directory. This framework uses `mochawesome` to create detailed HTML reports, which include test summaries, individual test results, and often screenshots for failed tests.
    *   The main report file is typically `html/index.html` within this directory after a test run that generates reports.

*   **`cypress/screenshots`**: Cypress automatically captures screenshots when a test fails during `cypress run`. Screenshots can also be captured manually within tests using `cy.screenshot()`. These are stored in this directory, usually organized by the spec file and test name.

*   **`cypress/support`**: This directory is crucial for extending Cypress's capabilities.
    *   `commands.js`: Used to define custom Cypress commands. These commands help in creating reusable functions for common actions or assertions, making test scripts more concise and readable.
    *   `e2e.js`: This file is executed before any test files. It's used for global configurations, importing custom commands, or setting up global before/after hooks.

*   **`cypress/utils`**: Contains utility functions that can be used across different parts of the test framework. These might include functions for generating random data, formatting values, or other helper tasks.
    *   Example: `utils.js`

*   **`cypress.config.js`**: This is the main configuration file for Cypress. It allows you to define global settings such as `baseUrl`, viewport dimensions, environment variables, and configure plugins.

## Prerequisites

Before you can run the tests in this framework, ensure you have the following installed on your system:

*   **Node.js**: Cypress is a Node.js application. You can download Node.js from [nodejs.org](https://nodejs.org/).
*   **npm** (Node Package Manager): npm is distributed with Node.js, so it will be installed automatically. Alternatively, you can use **yarn**.

## Installation

1.  **Clone the repository** (if you haven't already):
    ```bash
    git clone <repository-url>
    cd Hunt_Career_Cypress
    ```

2.  **Install project dependencies**:
    Navigate to the project's root directory (`Hunt_Career_Cypress`) in your terminal and run the following command:
    ```bash
    npm install
    ```
    This command will download and install all the necessary packages defined in the `package.json` file, including Cypress and any plugins.

## Running Tests

There are several ways to execute the Cypress tests:

1.  **Open Cypress Test Runner (Interactive Mode)**:
    This command opens the Cypress application, allowing you to see your spec files and run them individually or all at once. It's great for development and debugging.
    ```bash
    npx cypress open
    ```

2.  **Run Tests in Headless Mode**:
    This command runs all tests in the Cypress Electron browser headlessly (without opening the UI). This is ideal for CI/CD environments or when you want a quick run of all tests.
    ```bash
    npx cypress run
    ```
    You can also specify a particular browser or spec file:
    ```bash
    npx cypress run --browser chrome
    npx cypress run --spec "cypress/e2e/login.cy.js"
    ```

3.  **Using `package.json` Scripts** (if available):
    Check the `scripts` section in the `package.json` file for any predefined aliases for running tests. For example, you might have scripts like:
    ```json
    "scripts": {
      "cy:open": "cypress open",
      "cy:run": "cypress run",
      "report:merge": "mochawesome-merge cypress/reports/json/*.json > cypress/reports/report.json",
      "report:generate": "marge cypress/reports/report.json -f report -o cypress/reports"
    }
    ```
    If such scripts exist, you can run them using:
    ```bash
    npm run cy:open
    npm run cy:run
    ```
    To generate reports after a headless run, you might use:
    ```bash
    npm run report:merge
    npm run report:generate
    ```

## Reporting

This framework uses the **Mochawesome** reporter to generate comprehensive HTML reports for test runs. Mochawesome provides a clear and detailed overview of test results, including pass/fail status, execution time, and individual test steps.

*   **How it works**: When tests are run (especially in headless mode), Mochawesome generates JSON files for each spec. These JSON files can then be merged and converted into a single HTML report.
*   **Viewing Reports**:
    1.  After running tests (e.g., using `npx cypress run` or a custom script that includes report generation), the HTML report will be generated in the `cypress/reports/` directory.
    2.  The main report file to open in your browser is typically located at: `cypress/reports/html/index.html` (or `cypress/reports/report.html` if using the `marge` command directly as shown in package.json scripts).

## Configuration

The primary configuration for Cypress is managed in the `cypress.config.js` file. Key configurations include:

*   **`baseUrl`**: This is a global URL that Cypress will use as a prefix for `cy.visit()` and `cy.request()` commands. For example, if `baseUrl` is `https_huntcareer.com`, then `cy.visit('/login')` will navigate to `https_huntcareer.com/login`. This should be set to the base URL of the application under test.
*   **Viewport Dimensions**: You can set default `viewportWidth` and `viewportHeight` for your tests.
*   **Environment Variables (`env`)**: Custom environment variables can be defined here and accessed within your tests using `Cypress.env('variableName')`.
*   **e2e options**:
    *   `specPattern`: Defines the pattern Cypress uses to find test files (e.g., `cypress/e2e/**/*.cy.{js,jsx,ts,tsx}`).
    *   `supportFile`: Points to the `e2e.js` file (`cypress/support/e2e.js`).
*   **Reporter Configuration**: Settings for Mochawesome (or other reporters) can be configured here, such as `reporter`, `reporterOptions` (e.g., `overwrite`, `html`, `json`).

Users might need to modify `baseUrl` or other environment-specific settings in `cypress.config.js` depending on the testing environment (e.g., development, staging, production).

## Custom Commands

The `cypress/support/commands.js` file is a great place to put reusable, low-level functions. For example, if you frequently need to interact with a specific type of complex UI element, you could create a custom command for it.

However, in this framework, high-level business logic (like "login" or "register") is intentionally kept within the Page Object methods to ensure that the tests are explicit and easy to follow. Therefore, you will not find commands like `cy.login()` here. Instead, tests will call methods directly from the page object instances.

## Page Object Model (POM)

This framework utilizes the Page Object Model (POM) design pattern to create a scalable and maintainable test automation suite.

*   **Concept**: In POM, each web page in the application is represented by a corresponding class (a "Page Object"). This class contains:
    *   Locators for the web elements on that page.
    *   Methods that represent the actions a user can perform on that page (e.g., `fillLoginForm()`, `submitLogin()`).
*   **Implementation**: Page Objects are stored in the `cypress/pageObjects` directory.
    *   Example: `LoginPage.js` might define methods like `enterUsername()`, `enterPassword()`, and `clickLoginButton()`.
*   **Benefits**:
    *   **Reusability**: Page-specific logic is encapsulated in one place and can be reused across multiple test scripts.
    *   **Maintainability**: If the UI of a page changes, only the corresponding Page Object needs to be updated, not all the test scripts that use that page.
    *   **Readability**: Test scripts become more readable as they call high-level methods from Page Objects, making the test intent clearer.

    ```javascript
    // Example of using a LoginPage object in a test
    import LoginPage from '../pageObjects/LoginPage';

    describe('Login Test', () => {
      let loginPage;

      beforeEach(() => {
        loginPage = new LoginPage();
        cy.visit('/login');
      });

      it('should login successfully using POM', () => {
        loginPage.login('user@example.com', 'password123');
        cy.url().should('include', '/dashboard');
      });
    });
    ```

This README provides a comprehensive guide to understanding, setting up, and running tests with the Hunt Career Cypress Automation Framework.
