package com.huntcareer.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import com.huntcareer.qa.utils.Utilities;

public class TestBase {
    public WebDriver driver;
    public Properties prop;

    public TestBase() {
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/huntcareer/qa/config/Config.properties");

        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public WebDriver initialization() {
        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
        driver.get(prop.getProperty("url"));
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}