package com.huntcareer.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.huntcareer.qa.utils.Utilities;

public class Base {
    public WebDriver driver;
    public Properties prop;
    public Properties dataProp;

    public Base() {
        prop = new Properties();
        dataProp = new Properties();

        try {
            File propFile = new File(System.getProperty("user.dir")
                    + "/src/main/java/com/huntcareer/qa/config/Config.properties");
            File dataFile = new File(System.getProperty("user.dir")
                    + "/src/main/java/com/huntcareer/qa/testdata/testdata.properties");

            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);

            FileInputStream fis1 = new FileInputStream(dataFile);
            dataProp.load(fis1);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public WebDriver inicializeBrowserAndOpenApplication(String browserName) {

        // Read headless mode flag from Config.properties (optional)
        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
                System.out.println("Headless mode: " + isHeadless + ", Browser: " + browserName);
            }
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver = new EdgeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));

        // Open application URL from Config.properties
        driver.get(prop.getProperty("url"));
        return driver;
    }
}
