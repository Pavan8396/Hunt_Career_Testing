# Hunt Career Playwright Automation Framework

## Overview

This framework is built to automate end-to-end testing for the Hunt Career application using Playwright. It provides a robust and efficient structure for writing, managing, and executing tests, aiming to ensure the application's quality, reliability, and cross-browser compatibility. The framework leverages the Page Object Model (POM) for maintainable test scripts, uses external files for test data management, and utilizes Playwright's built-in reporting capabilities.

## Project Structure

The project follows a standard Playwright directory structure with specific conventions to organize test assets effectively:

- **`.github/workflows`**: This directory contains CI/CD pipeline configurations.
  - `playwright.yml`: Defines a GitHub Actions workflow for automatically running Playwright tests, often triggered on pushes or pull requests.

- **`data`**: Stores test data used by the test scripts. This framework uses a combination of static (`.json`) and dynamic (`.js`) data sources.
  - `loginData.json`, `searchData.json`: Static JSON files for simple, unchanging test cases.
  - `registerData.js`: A JavaScript file that generates dynamic, random, and realistic test data for user registration scenarios using functions from `utils/utils.js`. This approach allows for a wider range of test inputs without hardcoding data.

- **`pages`**: Implements the Page Object Model (POM). Each `.js` file in this directory represents a page (or a significant component) of the application. It encapsulates the page's elements and the interaction methods associated with them.
  - Example: `BasePage.js`, `LoginPage.js`, `RegisterPage.js`, `SearchPage.js`

- **`tests`**: Contains all the test script files (spec files). Each `*.spec.js` file typically groups tests for a specific feature or user flow of the application.
  - Example: `login.spec.js`, `register.spec.js`, `search.spec.js`

- **`utils`**: This directory holds utility functions and custom command-like helpers that can be reused across different parts of the framework.
  - `utils.js`: General helper functions (e.g., generating random data, date formatting).
  - `commands.js`: While Playwright doesn't have a direct equivalent to Cypress's custom commands that extend its core API (`cy.`), this file can be used to centralize common sequences of actions or complex interactions that are reused in multiple tests, often by importing them into test files or page objects.

- **`playwright.config.js`**: The main configuration file for Playwright. It allows you to define global settings such as `baseURL`, browser projects (Chromium, Firefox, WebKit), viewport dimensions, test timeouts, and reporter options.

- **`playwright-report`**: This is the default directory where Playwright stores its HTML reports after test execution. It will also contain other artifacts like screenshots and videos if configured.

## Prerequisites

Before you can run the tests, ensure you have the following installed:

- **Node.js**: Playwright is a Node.js library. Download Node.js from [nodejs.org](https://nodejs.org/).
- **npm** (Node Package Manager): npm is distributed with Node.js. Alternatively, you can use **yarn**.

## Installation

1.  **Clone the repository** (if you haven't already):

    ```bash
    git clone <repository-url>
    cd Hunt_Career_Playwright
    ```

2.  **Install project dependencies**:
    Navigate to the project's root directory (`Hunt_Career_Playwright`) in your terminal and run:

    ```bash
    npm install
    ```

    This command installs all packages listed in `package.json`, including Playwright.

3.  **Install Playwright browsers**:
    Playwright requires browser binaries for Chromium, Firefox, and WebKit to run tests. Install them using:
    ```bash
    npx playwright install
    ```
    You can also install specific browsers, e.g., `npx playwright install chromium`.

## Running Tests

Playwright provides a powerful CLI to execute tests:

1.  **Run All Tests**:
    This command runs all tests found in the `tests` directory across all configured browsers (projects) in headless mode by default.

    ```bash
    npx playwright test
    ```

2.  **Run Tests in Specific Browsers (Projects)**:
    You can target specific browser projects defined in `playwright.config.js`.

    ```bash
    npx playwright test --project=chromium
    npx playwright test --project=firefox
    npx playwright test --project=webkit
    ```

3.  **Run Tests in Headed Mode**:
    To watch the tests execute in a browser UI, use the `--headed` flag.

    ```bash
    npx playwright test --headed
    ```

    You can combine this with a project flag:

    ```bash
    npx playwright test --project=chromium --headed
    ```

4.  **Run Specific Test Files or Tests**:
    To run a specific file:

    ```bash
    npx playwright test tests/login.spec.js
    ```

    To run a test with a specific title (using grep):

    ```bash
    npx playwright test -g "user registration"
    ```

5.  **Using `package.json` Scripts**:
    Check the `scripts` section in `package.json` for any predefined aliases. For example:
    ```json
    "scripts": {
      "test": "npx playwright test",
      "test:headed": "npx playwright test --headed",
      "report": "npx playwright show-report"
    }
    ```
    If such scripts exist, you can run them using:
    ```bash
    npm test
    npm run test:headed
    npm run report
    ```

## Reporting

Playwright comes with a built-in HTML reporter that provides a detailed overview of test execution results.

- **Generation**: The HTML report is automatically generated after tests are run. By default, it's saved in the `playwright-report` directory.
- **Viewing Reports**:
  After a test run, you can open the HTML report using the following command:
  ```bash
  npx playwright show-report playwright-report
  ```
  (If you've configured a custom report path in `playwright.config.js`, replace `playwright-report` with that path).
  This command starts a local web server and opens the report in your default browser. The report includes:
  - Summary of test runs (pass/fail/skipped).
  - Details for each test, including steps.
  - Screenshots, videos, and traces if configured.

## Test Suites

This framework contains the following test suites:

- **Login (`login.spec.js`)**: Covers the user login functionality with various scenarios, including:
  - Successful login with valid credentials.
  - Failed login with invalid credentials (unregistered user, incorrect password).
  - Validation messages for invalid email formats and empty fields.

- **Registration (`register.spec.js`)**: Covers the user registration flow, testing:
  - Successful new user registration with dynamically generated valid data.
  - Attempted registration with an existing email address.
  - Validation for required fields (first name, last name, etc.).
  - Password and email format validation.

- **Search (`search.spec.js`)**: Covers the job search functionality, including:
  - Searching for jobs using valid and invalid keywords.
  - Searching with special characters.
  - Applying filters for location and job type and verifying the results.

## Configuration

The `playwright.config.js` file is central to configuring Playwright for your project. Key settings include:

- **`baseURL`**: Defined within the `use` option. It's a global URL prefix for actions like `page.goto('/')`. For example, if `baseURL` is `https_huntcareer.com`, `page.goto('/login')` will navigate to `https_huntcareer.com/login`. This should be set to the base URL of the application under test.

- **Browser Configurations (`projects`)**: Playwright can run tests across different browser engines. Projects are defined in the `projects` array. Each project can specify the browser engine and other settings.

  ```javascript
  projects: [
    { name: "chromium", use: { ...devices["Desktop Chrome"] } },
    { name: "firefox", use: { ...devices["Desktop Firefox"] } },
    { name: "webkit", use: { ...devices["Desktop Safari"] } },
  ];
  ```

- **Global Test Settings (`use`)**:
  - **`headless`**: Boolean to run browsers in headless mode (default `true`). Set to `false` to always run headed.
  - **`screenshot`**: Controls automatic screenshot capture. Options include `off`, `on`, or `only-on-failure` (default).
  - **`video`**: Controls video recording. Options: `off`, `on`, `retain-on-failure`, `on-first-retry`.
  - **`trace`**: Captures detailed trace information for debugging. Options: `off`, `on`, `retain-on-failure`, `on-first-retry`. Traces are invaluable for post-mortem debugging and can be viewed in the Playwright Trace Viewer.

- **Reporter Configuration**: You can configure multiple reporters. The HTML reporter is usually configured by default.
  ```javascript
  reporter: [['html', { open: 'never', outputFolder: 'playwright-report' }]],
  ```

## Utility Functions

The `utils/` directory provides helper functions and shared logic:

- **`utils/utils.js`**: This file contains general utility functions that are used to generate dynamic test data. Examples include:
  - Generating random names, emails, and phone numbers using `@faker-js/faker`.
  - Creating unique email addresses based on the current timestamp.
  - Generating random, valid passwords that meet specific criteria.

## Page Object Model (POM)

This framework employs the Page Object Model (POM) to enhance test maintenance and readability.

- **Concept**: Each page (or major component) of the application is represented by a class in the `pages/` directory. This class includes:
  - Selectors (locators) for the elements on the page.
  - Methods that perform actions or retrieve state from these elements.
- **Implementation**:
  - Page objects are stored in `pages/`. For example, `LoginPage.js` would manage interactions with the login page.
  - A `BasePage.js` might be used to define common functionality or elements shared across multiple pages (e.g., navigation headers, footers).
- **Benefits**:
  - **Encapsulation**: UI interactions are encapsulated within the page object, separating test logic from UI specifics.
  - **Reusability**: Page objects and their methods can be reused across many tests.
  - **Maintainability**: If the UI changes, updates are typically needed only in the relevant page object, not in every test script that interacts with that part of the UI.
  - **Readability**: Tests become cleaner by calling descriptive methods on page objects (e.g., `loginPage.loginWithCredentials(user, pass)`).

  ```javascript
  // Example: pages/LoginPage.js
  export class LoginPage {
    constructor(page) {
      this.page = page;
      this.usernameInput = page.locator("#username");
      this.passwordInput = page.locator("#password");
      this.submitButton = page.locator('button[type="submit"]');
    }

    async navigate() {
      await this.page.goto("/login");
    }

    async login(username, password) {
      await this.usernameInput.fill(username);
      await this.passwordInput.fill(password);
      await this.submitButton.click();
    }
  }

  // Example usage in a test: tests/login.spec.js
  import { LoginPage } from "../pages/LoginPage";
  test("should login successfully", async ({ page }) => {
    const loginPage = new LoginPage(page);
    await loginPage.navigate();
    await loginPage.login("user@example.com", "securepassword");
    // Add assertions here
  });
  ```

This README provides a comprehensive guide for developers and testers to get started with the Hunt Career Playwright Automation Framework.
