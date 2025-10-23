package com.huntcareer.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.huntcareer.qa.utils.AdvancedReportGenerator;
import com.huntcareer.qa.utils.ExtentReport;
import com.huntcareer.qa.utils.Utilities;
import com.google.gson.Gson;

class TestResultData {
    public String className;
    public String testName;
    public String status;
    public long duration;
    public String screenshotPath;

    public TestResultData(String className, String testName, String status, long duration, String screenshotPath) {
        this.className = className;
        this.testName = testName;
        this.status = status;
        this.duration = duration;
        this.screenshotPath = screenshotPath;
    }
}

public class MyListeners implements ITestListener {

    private static ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> classNode = new ThreadLocal<>();
    private ThreadLocal<Long> testStartTime = new ThreadLocal<>();
    private static Map<String, ExtentTest> testMap = new HashMap<>();

    private List<TestResultData> allTestResults = new ArrayList<>();

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReport.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testStartTime.set(System.currentTimeMillis());
        String testIdentifier = RetryTracker.getTestIdentifier(result);
        
        if (!testMap.containsKey(testIdentifier)) {
            String className = result.getTestClass().getName();
            if (classNode.get() == null || !classNode.get().getModel().getName().equals(className)) {
                classNode.set(extentReport.createTest(className));
            }
            ExtentTest test = classNode.get().createNode(result.getMethod().getMethodName());
            test.log(Status.INFO, result.getName() + " Started Executing");
            extentTest.set(test);
            testMap.put(testIdentifier, test);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = System.currentTimeMillis() - testStartTime.get();
        String status;

        if (RetryTracker.isTestRetried(result)) {
            status = "FLAKY";
            
            Throwable originalException = RetryTracker.getOriginalException(result);
            if (originalException != null) {
                extentTest.get().log(Status.INFO, "Original failure reason: " + originalException.getMessage());
            }
            
            extentTest.get().log(Status.WARNING, result.getName() + " passed after a retry (flaky)");
            
            RetryTracker.removeTestFromTracker(result); // Clean up the tracker
        } else {
            status = "PASS";
            extentTest.get().log(Status.PASS, result.getName() + " executed successfully");
        }

        extentTest.get().info("Execution Time: " + duration + " ms");
        allTestResults.add(new TestResultData(result.getTestClass().getName(), result.getName(), status, duration, null));
        ExtentReport.updateClassStats(result.getTestClass().getName(), status);
        testMap.remove(RetryTracker.getTestIdentifier(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (RetryTracker.isTestRetried(result)) {
            RetryTracker.removeTestFromTracker(result); // Clean up tracker for tests that fail all retries
        }
        
        WebDriver driver = null;
        try {
            Field field = result.getTestClass().getRealClass().getDeclaredField("driver");
            field.setAccessible(true);
            driver = (WebDriver) field.get(result.getInstance());
        } catch (Throwable e) { e.printStackTrace(); }

        String screenshotPath = Utilities.captureScreenshot(driver, result.getName());
        try { extentTest.get().addScreenCaptureFromPath(screenshotPath); } catch (Exception e) { e.printStackTrace(); }

        long duration = System.currentTimeMillis() - testStartTime.get();
        extentTest.get().log(Status.FAIL, result.getThrowable());
        extentTest.get().log(Status.FAIL, result.getName() + " got failed");
        extentTest.get().info("Execution Time: " + duration + " ms");

        allTestResults.add(new TestResultData(result.getTestClass().getName(), result.getName(), "FAIL", duration, screenshotPath));
        ExtentReport.updateClassStats(result.getTestClass().getName(), "FAIL");
        testMap.remove(RetryTracker.getTestIdentifier(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (RetryTracker.isTestRetried(result)) {
            return;
        }
        long duration = System.currentTimeMillis() - testStartTime.get();
        String status;
        String logMessage;

        Throwable throwable = result.getThrowable();
        if (throwable != null && throwable.getMessage() != null
                && throwable.getMessage().contains("depends on not successfully finished methods")) {
            status = "BLOCKED";
            logMessage = result.getName() + " is blocked due to dependency failure.";
        } else {
            status = "SKIP";
            logMessage = result.getName() + " was skipped.";
        }

        extentTest.get().log(Status.SKIP, logMessage);
        if (throwable != null) {
            extentTest.get().log(Status.INFO, throwable);
        }
        extentTest.get().info("Execution Time: " + duration + " ms");

        allTestResults.add(new TestResultData(result.getTestClass().getName(), result.getName(), status, duration, null));
        ExtentReport.updateClassStats(result.getTestClass().getName(), status);
        testMap.remove(RetryTracker.getTestIdentifier(result));
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        try {
            // Save current run JSON
            String currentJson = Utilities.getReportJsonPath();
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(currentJson);
            gson.toJson(allTestResults, writer);
            writer.flush();
            writer.close();

            // Generate merged dashboard using all historical JSONs
            String dashboardHtml = System.getProperty("user.dir") + "/test-output/MergedDashboard.html";
            AdvancedReportGenerator.generateMultiRunDashboard(dashboardHtml);

            Desktop.getDesktop().browse(new File(dashboardHtml).toURI());

        } catch (Exception e) { e.printStackTrace(); }
    }
}
