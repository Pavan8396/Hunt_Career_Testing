package com.huntcareer.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

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
        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        if (browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                options.setBinary("/home/jules/.cache/selenium/chrome/linux64/141.0.7390.76/chrome");
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
            }
            driver = new FirefoxDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
		WebDriverManager.edgedriver().setup();
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
        driver.get(appUrl);

        return driver;
    }
}
