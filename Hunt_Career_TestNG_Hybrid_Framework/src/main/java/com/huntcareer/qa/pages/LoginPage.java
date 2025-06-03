package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;
	
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordField;
	
	@FindBy(xpath = "//button[@aria-label='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(text(), 'Invalid email or password')]")
	private WebElement invalidEmailAndPassword;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public HomePage clickLoginButton() {
		loginButton.click();
		return new HomePage(driver);
	}
	public void verifyInvalidEmailAndPassword() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(invalidEmailAndPassword)).getText(),
				"Invalid email or password", "Message Text Mismatch");
	}
	
	public void verifyInvalidEmailAndPasswordIsGone() {
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(invalidEmailAndPassword)), "Invalid email or password message should not be visible anymore");
	}
}
