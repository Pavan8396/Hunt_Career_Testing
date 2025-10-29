package com.huntcareer.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

    public static final ExtentReports extentReports = new ExtentReports();

    public static ExtentReports getReporterObject() {
        String reportPath = System.getProperty("user.dir") + "/reports/extent-reports/extent-report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("RestAssured API Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Jules");
        return extentReports;
    }
}
