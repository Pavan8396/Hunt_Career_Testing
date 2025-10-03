package com.huntcareer.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.utils.ExtentReport;
import com.huntcareer.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReport.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " Stated Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.INFO, result.getName() + " Got Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		try {
			Object testInstance = result.getInstance();
            driver = ((TestBase) testInstance).driver;
		} catch (Throwable e) {
			e.printStackTrace();
		}

        if (driver != null) {
		    String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		    extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        }
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, result.getName() + " Test got skipped");
		extentTest.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
		File extentReportFile = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}