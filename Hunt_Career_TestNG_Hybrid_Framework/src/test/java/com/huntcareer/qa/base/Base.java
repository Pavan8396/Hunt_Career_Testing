package com.huntcareer.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.huntcareer.qa.utils.Utilities;

public class Base {
	//WebDriver driver;
	private static ThreadLocal<WebDriver>tlDriver = new ThreadLocal<>();
	public Properties prop;
	public Properties dataProp;

	// public Properties loadPropertiesFile(){
	public Base() {

		prop = new Properties();
		File propFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\huntcareer\\qa\\config\\Config.properties");

		dataProp = new Properties();
		File dataFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\huntcareer\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream fis1 = new FileInputStream(dataFile);
		dataProp.load(fis1);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// return prop;
	}

	public WebDriver inicializeBrowserAndOpenApplication(String browserName) {

		WebDriver driver;
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else{
			throw new IllegalArgumentException("Browser Name " + browserName + " not supported");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		//return driver;
		tlDriver.set(driver);
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void unload() {
		tlDriver.remove();
	}
}
