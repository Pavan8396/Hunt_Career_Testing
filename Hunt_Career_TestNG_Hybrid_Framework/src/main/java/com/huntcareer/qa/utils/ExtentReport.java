package com.huntcareer.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Configures and generates the Extent HTML report.
 * This class is responsible for setting up the report's theme, title, and system information.
 * All custom styling and scripting are loaded from external files to keep this class clean.
 */
public final class ExtentReport {

    private static ExtentReports extent;

    // Private constructor to prevent instantiation
    private ExtentReport() {}

    /**
     * Creates and returns a singleton instance of the ExtentReports.
     *
     * @return The singleton ExtentReports instance.
     */
    public static synchronized ExtentReports generateExtentReport() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    /**
     * Creates a new ExtentReports instance with a configured Spark reporter.
     *
     * @return A fully configured ExtentReports object.
     */
    private static ExtentReports createInstance() {
        ExtentReports extentReport = new ExtentReports();

        // Define file paths
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/AutomationReport.html";
        File reportFile = new File(reportPath);
        reportFile.getParentFile().mkdirs(); // Ensure the directory exists

        // Configure the Spark Reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
        configureReporter(sparkReporter);

        extentReport.attachReporter(sparkReporter);

        // Load properties and set system info
        Properties config = loadConfigProperties();
        addSystemInfo(extentReport, config);

        return extentReport;
    }

    /**
     * Configures the visual aspects of the ExtentSparkReporter.
     *
     * @param reporter The reporter to configure.
     */
    private static void configureReporter(ExtentSparkReporter reporter) {
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Hunt Career | Automation Dashboard");
        reporter.config().setReportName("Test Execution Report");
        reporter.config().setEncoding("utf-8");
        reporter.config().setTimeStampFormat("EEEE, dd MMMM yyyy | hh:mm:ss a");

        // Link to external CSS and JS for customization (cleaner approach)
        reporter.config().setCss("css/extent-custom.css");
        reporter.config().setJs("js/extent-custom.js");
    }

    /**
     * Loads configuration from the `Config.properties` file.
     *
     * @return A Properties object with the loaded configuration.
     */
    private static Properties loadConfigProperties() {
        Properties prop = new Properties();
        String configPath = System.getProperty("user.dir") + "/src/main/java/com/huntcareer/qa/config/Config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            prop.load(fis);
        } catch (Exception e) {
            System.err.println("Error loading Config.properties: " + e.getMessage());
        }
        return prop;
    }

    /**
     * Adds system and environment details to the Extent Report.
     *
     * @param extentReport The ExtentReports instance to add info to.
     * @param config       The loaded configuration properties.
     */
    private static void addSystemInfo(ExtentReports extentReport, Properties config) {
        extentReport.setSystemInfo("Application URL", config.getProperty("url", "N/A"));
        extentReport.setSystemInfo("Browser", config.getProperty("browser", "N/A"));
        extentReport.setSystemInfo("Environment", config.getProperty("env", "QA"));
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("User", System.getProperty("user.name"));
        extentReport.setSystemInfo("Execution Time", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
    }
}
