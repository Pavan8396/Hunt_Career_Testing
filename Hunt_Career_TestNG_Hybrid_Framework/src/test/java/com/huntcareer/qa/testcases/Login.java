package com.huntcareer.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;

public class Login extends Base {
	WebDriver driver;
	
	public Login() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		//loadPropertiesFile();
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.get(prop.getProperty("url")+"/login");
		//driver.get(loadPropertiesFile().getProperty("url")+"/login");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verifyWithValidData() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("upavan@mailinator.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//button[@aria-label='Login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))).isDisplayed());
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))).getText(), "Logged in successfully!", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))), "Success message should not be visible anymore");
	}
	
	@Test
	public void verifyWithInvalidData() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("pavan@mailinator.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@12345");
		driver.findElement(By.xpath("//button[@aria-label='Login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid email or password')]"))).getText(), "Invalid email or password", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Invalid email or password')]"))), "Invalid email or password message should not be visible anymore");
	}
}
