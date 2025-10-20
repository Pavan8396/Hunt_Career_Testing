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

    private ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReport.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();
        String methodName = result.getName();

        // Group info (TestNG @Test groups)
        String[] groups = result.getMethod().getGroups();
        String groupLabel = (groups.length > 0) ? String.join(", ", groups) : "Ungrouped";

        // Create parent node per class (only once)
        ExtentTest parent = parentTest.get();
        if (parent == null || !parent.getModel().getName().equals(className)) {
            parent = extentReport.createTest(className)
                                 .assignCategory(groupLabel);
            parentTest.set(parent);
        }

        // Create child test node under the parent class node
        ExtentTest child = parent.createNode(methodName)
                                 .assignCategory(groupLabel);
        child.log(Status.INFO, methodName + " started executing");
        childTest.set(child);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        childTest.get().log(Status.PASS, result.getName() + " passed ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            Field field = result.getTestClass().getRealClass().getDeclaredField("driver");
            field.setAccessible(true);
            driver = (WebDriver) field.get(result.getInstance());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        String screenshotPath = Utilities.captureScreenshot(driver, result.getName());
        childTest.get().addScreenCaptureFromPath(screenshotPath);
        childTest.get().log(Status.FAIL, "❌ " + result.getName() + " failed");
        childTest.get().log(Status.INFO, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        childTest.get().log(Status.SKIP, "⚠️ " + result.getName() + " skipped");
        if (result.getThrowable() != null)
            childTest.get().log(Status.INFO, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        File reportFile = ExtentReport.getLatestReportFile();

        if (System.getenv("CI") == null && reportFile != null && reportFile.exists()) {
            try {
                Desktop.getDesktop().browse(reportFile.toURI());
                System.out.println("✅ Opened latest report: " + reportFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ℹ️ Report not opened (running in CI or file missing).");
        }
    }
}
