package com.huntcareer.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.lang.reflect.Field;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.huntcareer.qa.utils.AdvancedReportGenerator;
import com.huntcareer.qa.utils.ExtentReport;
import com.huntcareer.qa.utils.TestRunStatistics;
import com.huntcareer.qa.utils.Utilities;

public class MyListeners implements ITestListener {

    private ExtentReports extentReport;
    private final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private final ThreadLocal<Long> testStartTime = new ThreadLocal<>();
    private final TestRunStatistics stats = new TestRunStatistics();

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReport.generateExtentReport();
        stats.startSuite();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testStartTime.set(System.currentTimeMillis());
        String className = result.getTestClass().getName();
        String testName = result.getMethod().getMethodName();

        // Create a node for the test in the Extent Report
        ExtentTest test = extentReport.createTest(className).createNode(testName);
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "Test '" + testName + "' Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = System.currentTimeMillis() - testStartTime.get();
        String testIdentifier = result.getTestClass().getName() + "." + result.getName();
        boolean isFlaky = RetryAnalyzer.retryCounts.get().getOrDefault(testIdentifier, 0) > 0;

        // Log to Extent Report
        if (isFlaky) {
            extentTest.get().log(Status.PASS, "Test Passed (Flaky - on retry)");
        } else {
            extentTest.get().log(Status.PASS, "Test Passed");
        }
        extentTest.get().info("Duration: " + duration + " ms");

        // Record statistics
        stats.addTestResult(new TestRunStatistics.TestResultRecord(
                result.getTestClass().getName(),
                result.getName(),
                "PASS",
                duration,
                null,
                isFlaky,
                null
        ));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long duration = System.currentTimeMillis() - testStartTime.get();
        
        // Capture screenshot
        WebDriver driver = getDriverFromTestResult(result);
        String screenshotPath = Utilities.captureScreenshot(driver, result.getName());

        // Log to Extent Report
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().log(Status.FAIL, result.getThrowable());
        if (screenshotPath != null) {
            try {
                extentTest.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                System.err.println("Error adding screenshot to Extent Report: " + e.getMessage());
            }
        }
        extentTest.get().info("Duration: " + duration + " ms");

        // Record statistics
        stats.addTestResult(new TestRunStatistics.TestResultRecord(
                result.getTestClass().getName(),
                result.getName(),
                "FAIL",
                duration,
                screenshotPath,
                false,
                result.getThrowable() != null ? result.getThrowable().getMessage() : "No error message"
        ));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TestNG marks retried tests as 'skipped' on their initial failure. We ignore these.
        if (result.wasRetried()) {
            return;
        }

        long duration = System.currentTimeMillis() - testStartTime.get();
        String status;
        String logMessage;

        // Determine if the test was skipped due to a dependency failure (blocked)
        if (result.getThrowable() != null && result.getThrowable().getMessage().contains("depends on not successfully finished methods")) {
            status = "BLOCKED";
            logMessage = "Test Blocked (dependency failure)";
        } else {
            status = "SKIP";
            logMessage = "Test Skipped";
        }

        // Log to Extent Report
        extentTest.get().log(Status.SKIP, logMessage);
        if (result.getThrowable() != null) {
            extentTest.get().info(result.getThrowable());
        }
        extentTest.get().info("Duration: " + duration + " ms");

        // Record statistics
        stats.addTestResult(new TestRunStatistics.TestResultRecord(
                result.getTestClass().getName(),
                result.getName(),
                status,
                duration,
                null,
                false,
                result.getThrowable() != null ? result.getThrowable().getMessage() : "No error message"
        ));
    }

    @Override
    public void onFinish(ITestContext context) {
        stats.endSuite();
        extentReport.flush();

        try {
            // Save the current run's statistics to a timestamped JSON file
            String reportJsonPath = Utilities.getReportJsonPath();
            Utilities.writeToFile(reportJsonPath, stats.toJson());

            // Generate the multi-run dashboard from all historical JSON files
            String dashboardHtmlPath = System.getProperty("user.dir") + "/test-output/Dashboard.html";
            AdvancedReportGenerator.generateMultiRunDashboard(dashboardHtmlPath);

            // Try to open the dashboard automatically (will not work in headless environments)
            File dashboardFile = new File(dashboardHtmlPath);
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(dashboardFile.toURI());
                } catch (java.awt.HeadlessException e) {
                    System.out.println("Headless environment detected. Cannot open dashboard automatically.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error generating or opening the test dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Safely retrieves the WebDriver instance from a test result.
     *
     * @param result The ITestResult from which to extract the driver.
     * @return The WebDriver instance, or null if not found.
     */
    private WebDriver getDriverFromTestResult(ITestResult result) {
        try {
            Field field = result.getTestClass().getRealClass().getDeclaredField("driver");
            field.setAccessible(true);
            return (WebDriver) field.get(result.getInstance());
        } catch (Exception e) {
            System.err.println("Could not retrieve WebDriver instance from test result: " + e.getMessage());
            return null;
        }
    }
}
