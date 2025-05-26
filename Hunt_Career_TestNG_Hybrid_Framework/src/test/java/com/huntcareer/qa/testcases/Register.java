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
import com.huntcareer.qa.utils.Utilities;

public class Register extends Base {
	WebDriver driver;
	
	public Register() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		//loadPropertiesFile();
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		//driver.get(loadPropertiesFile().getProperty("url")+"/signup");
		driver.get(prop.getProperty("url")+"/signup");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verifyRegisterWithValidData() {
		
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("FirstName"+Utilities.generateTimeStamp());
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("LastName"+Utilities.generateTimeStamp());
		driver.findElement(By.xpath("//button[@type='button']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Utilities.genarateEmailTimeStamp());
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//button[normalize-space()='Next']")).click();
		driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//button[@aria-label='Sign up']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Registered successfully! Please login.')]"))).getText(), "Registered successfully! Please login.", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Registered successfully! Please login.')]"))), "Success message should not be visible anymore");
	}
}
