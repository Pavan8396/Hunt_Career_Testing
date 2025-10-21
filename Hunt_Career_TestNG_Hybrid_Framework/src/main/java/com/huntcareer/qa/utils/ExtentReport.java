package com.huntcareer.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Hunt Career - Ultimate Extent Report Builder
 * --------------------------------------------
 * Generates a modern, data-driven automation report with timestamped filenames,
 * custom theme, branding, CI/CD awareness, and robust error handling.
 */
public class ExtentReport {

    private static File latestReportFile; // ✅ To access the newest report in listener

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();

        // Create report directory if not present
        String reportDirPath = System.getProperty("user.dir") + "/test-output/ExtentReports/";
        File reportDir = new File(reportDirPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Generate timestamped report name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        latestReportFile = new File(reportDirPath + "HuntCareer_AutoReport_" + timeStamp + ".html");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(latestReportFile);

        // 🌈 Custom Styling
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Hunt Career | Automation Test Dashboard");
        sparkReporter.config().setReportName("🏆 Hunt Career QA Test Results");
        sparkReporter.config().setTimeStampFormat("EEE, dd MMM yyyy HH:mm:ss z");

        // Inject CSS for better visuals
        sparkReporter.config().setCss(
            "body { font-family: 'Segoe UI', Roboto, sans-serif; background-color: #121212; }" +
            ".badge-primary { background-color: #007bff !important; }" +
            ".card-header { background-color: #2b2b2b; color: #ffffff; font-weight: bold; text-transform: uppercase; letter-spacing: 0.5px; }" +
            ".test-status.pass { color: #00e676 !important; }" +
            ".test-status.fail { color: #ff5252 !important; }" +
            ".test-status.skip { color: #ffb300 !important; }"
        );

        // Inject small JS snippet for interactivity
        sparkReporter.config().setJs(
            "document.title = 'Hunt Career QA Dashboard';" +
            "console.log('%c🚀 Hunt Career Report Loaded', 'color:#00e676;font-weight:bold');"
        );

        extentReport.attachReporter(sparkReporter);

        // Load config safely
        Properties prop = loadProperties(System.getProperty("user.dir") +
                "/src/main/java/com/huntcareer/qa/config/Config.properties");

        // 🧭 System & Env Info
        extentReport.setSystemInfo("🌐 Application URL", prop.getProperty("url", "N/A"));
        extentReport.setSystemInfo("🧪 Environment", prop.getProperty("environment", "QA"));
        extentReport.setSystemInfo("🖥️ Browser", prop.getProperty("browser", "N/A"));
        extentReport.setSystemInfo("💻 OS Name", System.getProperty("os.name"));
        extentReport.setSystemInfo("🧩 OS Version", System.getProperty("os.version"));
        extentReport.setSystemInfo("☕ Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("👤 Executed By", System.getProperty("user.name"));
        extentReport.setSystemInfo("🕒 Run Started", new SimpleDateFormat("dd MMM yyyy, HH:mm:ss").format(new Date()));

        // CI/CD awareness
        if (System.getenv("JENKINS_HOME") != null) {
            extentReport.setSystemInfo("🏗️ CI Platform", "Jenkins");
            extentReport.setSystemInfo("🔢 Build Number", System.getenv("BUILD_NUMBER"));
            extentReport.setSystemInfo("📦 Job Name", System.getenv("JOB_NAME"));
        }

        extentReport.setSystemInfo("Framework", "Selenium | TestNG | ExtentReports");
        extentReport.setSystemInfo("Report Version", "2.0.0");

        return extentReport;
    }

    // ✅ Helper to get latest generated report
    public static File getLatestReportFile() {
        return latestReportFile;
    }

    // Safe config loader
    private static Properties loadProperties(String filePath) {
        Properties prop = new Properties();
        File configFile = new File(filePath);
        if (configFile.exists()) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                prop.load(fis);
            } catch (Exception e) {
                System.err.println("⚠️ Could not load config file: " + e.getMessage());
            }
        } else {
            System.err.println("⚠️ Config file not found at: " + filePath);
        }
        return prop;
    }
}
