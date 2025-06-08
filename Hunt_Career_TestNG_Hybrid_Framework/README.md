# Hunt Career TestNG Hybrid Framework

This is a TestNG-based hybrid automation framework for testing the Hunt Career web application.

## Architecture

The framework follows a hybrid approach, combining data-driven and page object model (POM) design patterns.

- **Page Object Model:** UI elements and their interactions are encapsulated in page classes, promoting code reusability and maintainability.
- **Data-Driven Testing:** Test data is externalized from test scripts, allowing for easy modification and execution of tests with different datasets.

## Technologies and Libraries

- **Java:** Programming language for writing test scripts and framework components.
- **Selenium WebDriver:** For browser automation and web element interaction.
- **TestNG:** Testing framework for test execution, reporting, and assertions.
- **Apache POI:** For reading test data from Excel files.
- **ExtentReports:** For generating detailed HTML test reports.
- **JavaFaker:** For generating realistic test data.
- **Lombok:** To reduce boilerplate code.
- **Maven:** Build automation and dependency management tool.

## Project Structure

```
Hunt_Career_TestNG_Hybrid_Framework/
├── .mvn/                     # Maven wrapper files
├── src/
│   ├── main/java/com/huntcareer/qa/
│   │   ├── config/           # Configuration files (e.g., Config.properties)
│   │   ├── listeners/        # TestNG listeners (e.g., MyListeners.java)
│   │   ├── pages/            # Page object classes (e.g., HomePage.java, LoginPage.java)
│   │   ├── testdata/         # Test data files (e.g., DDTFile.xlsx, RegisterData.java)
│   │   └── utils/            # Utility classes (e.g., ExtentReport.java, Utilities.java)
│   └── test/
│       ├── java/com/huntcareer/qa/
│       │   ├── base/         # Base test class (e.g., Base.java)
│       │   └── testcases/    # Test case classes (e.g., Login.java, Register.java)
│       └── resource/         # Test resources (e.g., testng.xml)
├── test-output/              # Test execution reports
├── .classpath                # Eclipse classpath file
├── .gitignore                # Git ignore file
├── .project                  # Eclipse project file
└── pom.xml                   # Maven project object model file
```

## Setup

1.  **Prerequisites:**
    *   Java Development Kit (JDK) installed (version 17 or higher).
    *   Apache Maven installed.
    *   Web browser (e.g., Chrome, Firefox) installed.
2.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    ```
3.  **Navigate to the project directory:**
    ```bash
    cd Hunt_Career_TestNG_Hybrid_Framework
    ```
4.  **Install dependencies:**
    ```bash
    mvn clean install
    ```

## Running Tests

1.  **Using Maven:**
    ```bash
    mvn test
    ```
    This command will execute the test suite defined in `src/test/resource/testng.xml`.
2.  **Using TestNG XML:**
    You can also run tests directly from your IDE using the `testng.xml` file.

## Test Reports

After test execution, ExtentReports will be generated in the `test-output/ExtentReports/` directory. Open the `extentReport.html` file in a web browser to view the detailed test report.

TestNG also generates its own reports in the `test-output/` directory.

```
