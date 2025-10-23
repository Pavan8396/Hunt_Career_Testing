package com.huntcareer.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.huntcareer.qa.utils.Utilities;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public Base() {
		prop = new Properties();
		dataProp = new Properties();

		try {
			File propFile = new File(
					System.getProperty("user.dir") + "/src/main/java/com/huntcareer/qa/config/Config.properties");
			File dataFile = new File(
					System.getProperty("user.dir") + "/src/main/java/com/huntcareer/qa/testdata/testdata.properties");

			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);

			FileInputStream fis1 = new FileInputStream(dataFile);
			dataProp.load(fis1);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("--- @BeforeSuite: Setting up the test suite ---");
		// Future implementation: e.g., initialize reports, connect to DB
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("--- @AfterSuite: Tearing down the test suite ---");
		// Future implementation: e.g., flush reports, close DB connection
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("--- @BeforeTest: Setting up a test block ---");
		// Future implementation: e.g., set up test-specific data
	}

	@AfterTest
	public void afterTest() {
		System.out.println("--- @AfterTest: Tearing down a test block ---");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("--- @BeforeClass: Setting up a test class ---");
		// Future implementation: e.g., user login
	}

	@AfterClass
	public void afterClass() {
		System.out.println("--- @AfterClass: Tearing down a test class ---");
		// Future implementation: e.g., user logout
	}

	@BeforeMethod
	public void setup() {
		System.out.println("--- @BeforeMethod: Initializing browser ---");
		String browserName = prop.getProperty("browser");
		boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (isHeadless) {
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (isHeadless) {
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
			}
			driver = new FirefoxDriver(options);

		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			if (isHeadless) {
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
			}
			driver = new EdgeDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));

		String appUrl = prop.getProperty("url");
		int maxRetries = 5;

		for (int i = 1; i <= maxRetries; i++) {
			try {
				driver.get(appUrl);
				break;
			} catch (WebDriverException e) {
				System.out.println("Attempt " + i + " failed to open app (frontend may not be ready). Retrying...");
				if (i == maxRetries) {
					System.out.println("Application not reachable after " + maxRetries + " attempts.");
					e.printStackTrace();
				}
				try {
					Thread.sleep(5000); // wait 5s before retrying
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("--- @AfterMethod: Tearing down browser ---");
		if (driver != null) {
			driver.quit();
		}
	}
}
