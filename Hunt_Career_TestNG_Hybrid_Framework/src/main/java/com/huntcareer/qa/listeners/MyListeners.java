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
    public boolean flaky;

    public TestResultData(String className, String testName, String status, long duration, String screenshotPath, boolean flaky) {
        this.className = className;
        this.testName = testName;
        this.status = status;
        this.duration = duration;
        this.screenshotPath = screenshotPath;
        this.flaky = flaky;
    }
}

public class MyListeners implements ITestListener {

    private static ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> classNode = new ThreadLocal<>();
    private ThreadLocal<Long> testStartTime = new ThreadLocal<>();

    private List<TestResultData> allTestResults = new ArrayList<>();

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReport.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testStartTime.set(System.currentTimeMillis());
        String className = result.getTestClass().getName();
        if (classNode.get() == null || !classNode.get().getModel().getName().equals(className)) {
            classNode.set(extentReport.createTest(className));
        }
        ExtentTest test = classNode.get().createNode(result.getMethod().getMethodName());
        test.log(Status.INFO, result.getName() + " Started Executing");
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testIdentifier = result.getTestClass().getName() + "." + result.getName();
        boolean isFlaky = RetryAnalyzer.retryCounts.get().getOrDefault(testIdentifier, 0) > 0;
        if (isFlaky) {
            allTestResults.removeIf(tr -> tr.testName.equals(result.getName()) && tr.className.equals(result.getTestClass().getName()));
            extentTest.get().log(Status.PASS, result.getName() + " passed after retry (flaky)");
        } else {
            extentTest.get().log(Status.PASS, result.getName() + " executed successfully");
        }
        long duration = System.currentTimeMillis() - testStartTime.get();
        extentTest.get().info("Execution Time: " + duration + " ms");
        allTestResults.add(new TestResultData(result.getTestClass().getName(), result.getName(), "PASS", duration, null, isFlaky));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Remove any previous results for this test to avoid duplicates
        allTestResults.removeIf(tr -> tr.testName.equals(result.getName()) && tr.className.equals(result.getTestClass().getName()));

        extentTest.get().log(Status.FAIL, result.getName() + " got failed");
        
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
        extentTest.get().info("Execution Time: " + duration + " ms");

        allTestResults.add(new TestResultData(result.getTestClass().getName(), result.getName(), "FAIL", duration, screenshotPath, false));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // If a test is retried, TestNG marks the initial failure as a skip.
        // We want to ignore these skips to avoid duplicate reporting.
        if (result.wasRetried()) {
            return;
        }

        String status = "SKIP";
        if (result.getThrowable() != null && result.getThrowable().getMessage().contains("depends on not successfully finished methods")) {
            status = "BLOCKED";
            extentTest.get().log(Status.WARNING, result.getName() + " blocked due to dependency failure");
        } else {
            extentTest.get().log(Status.SKIP, result.getName() + " skipped");
        }
        long duration = System.currentTimeMillis() - testStartTime.get();
        extentTest.get().log(Status.INFO, result.getThrowable());
        extentTest.get().info("Execution Time: " + duration + " ms");

        allTestResults.add(new TestResultData(result.getTestClass().getName(), result.getName(), status, duration, null, false));
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

            try {
                Desktop.getDesktop().browse(new File(dashboardHtml).toURI());
            } catch (java.awt.HeadlessException e) {
                // Ignore, this happens in headless environments
            }

        } catch (Exception e) { e.printStackTrace(); }
    }
}
