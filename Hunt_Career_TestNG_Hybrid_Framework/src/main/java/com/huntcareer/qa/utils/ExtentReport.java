package com.huntcareer.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static Properties prop;

	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		
		prop = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"/src/main/java/com/huntcareer/qa/config/Config.properties");

		try {
		FileInputStream fisConfigProp = new FileInputStream(configPropFile);
		prop.load(fisConfigProp);
		} catch(Throwable e) {
			e.printStackTrace();
		}

		File extentReportFile = new File(System.getProperty("user.dir")+ prop.getProperty("extentReportPath"));
		ExtentSparkReporter sparkreport = new ExtentSparkReporter(extentReportFile);

		sparkreport.config().setTheme(Theme.DARK);
		sparkreport.config().setReportName("Hunt Career Project Test Results");
		sparkreport.config().setDocumentTitle("Hunt Career Automation Result");
		sparkreport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkreport);

		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
		extentReport.setSystemInfo("Operating System Name ", System.getProperty("os.name"));
		extentReport.setSystemInfo("Operating System Version ", System.getProperty("os.version"));
		extentReport.setSystemInfo("User Name ", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
	}
}