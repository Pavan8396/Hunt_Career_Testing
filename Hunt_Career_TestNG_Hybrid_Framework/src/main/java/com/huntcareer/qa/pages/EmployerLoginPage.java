package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EmployerLoginPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(text(), 'Invalid email or password')]")
	private WebElement invalidEmailAndPassword;

	@FindBy(xpath = "//div[contains(text(), 'Password must be at least 8 characters long.')]")
	private WebElement passwordMustHaveATLeast8CharactersLongMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Please enter a valid email address.')]")
	private WebElement pleaseEnterValidEmailAddressMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Email is required.')]")
	private WebElement EmailIsRequiredMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Password is required.')]")
	private WebElement passwordIsRequiredMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Registered successfully! Please login.')]")
	private WebElement registerSuccessMessage;

	public EmployerLoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void enterEmail(String emailText) {
		wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(passwordText);
	}

	public HomePage clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
		return new HomePage(driver);
	}

	public void verifyInvalidEmailAndPassword(String expectedMessage) {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(invalidEmailAndPassword)).getText(),
				expectedMessage, "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(invalidEmailAndPassword)),
				"Invalid email or password message should not be visible anymore");
	}

	public void verifyEmailIsRequired(String expectedMessage) {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(EmailIsRequiredMessage)).getText(),
				expectedMessage, "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(EmailIsRequiredMessage)),
				"Email is required message should not be visible anymore");
	}
	
	public void verifyPleaseEnterValidEmailAddress(String expectedMessage) {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(pleaseEnterValidEmailAddressMessage)).getText(),
				expectedMessage, "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(pleaseEnterValidEmailAddressMessage)),
				"Please enter a valid email address message should not be visible anymore");
	}

	public void verifyPasswordIsRequired(String expectedMessage) {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(passwordIsRequiredMessage)).getText(),
				expectedMessage, "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(passwordIsRequiredMessage)),
				"Password is required message should not be visible anymore");
	}
	
	public void verifyPasswordMustHaveAtLeast8CharactersLong(String expectedMessage) {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(passwordMustHaveATLeast8CharactersLongMessage)).getText(),
				expectedMessage, "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(passwordMustHaveATLeast8CharactersLongMessage)),
				"Password must be at least 8 characters long message should not be visible anymore");
	}
	
	public void verifyRegisterSuccess(String expectedMessage) {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(registerSuccessMessage)).getText(),
				expectedMessage, "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(registerSuccessMessage)),
				"Registered successfully! Please login message should not be visible anymore");
	}
}
