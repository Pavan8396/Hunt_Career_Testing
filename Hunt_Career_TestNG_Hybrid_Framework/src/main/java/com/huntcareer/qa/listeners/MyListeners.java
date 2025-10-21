package com.huntcareer.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.huntcareer.qa.utils.ExtentReport;
import com.huntcareer.qa.utils.Utilities;

public class MyListeners implements ITestListener {

    private static ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> classNode = new ThreadLocal<>();
    private ThreadLocal<Long> testStartTime = new ThreadLocal<>();

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
        extentTest.get().log(Status.PASS, result.getName() + " executed successfully");
        ExtentReport.updateClassStats(result.getTestClass().getName(), "pass");

        long duration = System.currentTimeMillis() - testStartTime.get();
        ExtentReport.updateTestDuration(result.getTestName(), duration);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            Field field = result.getTestClass().getRealClass().getDeclaredField("driver");
            field.setAccessible(true);
            driver = (WebDriver) field.get(result.getInstance());
        } catch (Throwable e) { e.printStackTrace(); }

        String screenshotPath = Utilities.captureScreenshot(driver, result.getName());
        extentTest.get().addScreenCaptureFromPath(screenshotPath);

        extentTest.get().log(Status.FAIL, result.getThrowable());
        extentTest.get().log(Status.FAIL, result.getName() + " got failed");
        ExtentReport.updateClassStats(result.getTestClass().getName(), "fail");

        long duration = System.currentTimeMillis() - testStartTime.get();
        ExtentReport.updateTestDuration(result.getName(), duration);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, result.getName() + " skipped");
        extentTest.get().log(Status.INFO, result.getThrowable());
        ExtentReport.updateClassStats(result.getTestClass().getName(), "skip");

        long duration = System.currentTimeMillis() - testStartTime.get();
        ExtentReport.updateTestDuration(result.getName(), duration);
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();

        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html";
        File reportFile = new File(reportPath);
        if (System.getenv("CI") == null) {
            try { Desktop.getDesktop().browse(reportFile.toURI()); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }
}
