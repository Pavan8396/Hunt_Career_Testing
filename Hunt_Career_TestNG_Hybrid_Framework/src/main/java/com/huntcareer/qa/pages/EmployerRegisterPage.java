package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EmployerRegisterPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//input[@id='companyName']")
	private WebElement companyNameFeild;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@id='confirmPassword']")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signUpButton;

	@FindBy(xpath = "//div[contains(text(),'Employer already exists')]")
	private WebElement duplicateErrorMessage;

	@FindBy(xpath = "//div[contains(text(),'Company Name is required.')]")
	private WebElement companyNameBlankMessage;

	@FindBy(xpath = "//div[contains(text(),'Email is required.')]")
	private WebElement emailBlankMessage;

	@FindBy(xpath = "//div[contains(text(),'Please enter a valid email address.')]")
	private WebElement pleaseEnterValidEmailAddressMessage;

	@FindBy(xpath = "//div[contains(text(),'Password must be at least 8 characters long.')]")
	private WebElement passwordMustHaveRequiredNumberCharactersMessage;

	@FindBy(xpath = "//div[contains(text(),'Passwords do not match.')]")
	private WebElement passwordNotMatchMessage;

	@FindBy(xpath = "//a[contains(text(), 'Sign up')]")
	private WebElement SignupLink;

	public EmployerRegisterPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void enterCompanyName(String companyNameText) {
		companyNameFeild.sendKeys(companyNameText);
	}

	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}

	public void clickSignupLink(){
		SignupLink.click();
	}

	public EmployerLoginPage clickSignUpButton() {
		signUpButton.click();
		return new EmployerLoginPage(driver);
	}

	public void verifyUserAlreadyExistsMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(duplicateErrorMessage)).getText(),
				"Employer already exists", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(duplicateErrorMessage)),
				"Employer already exists message should not be visible anymore");
	}

	/*public void verifyUserAlreadyExistsMessageIsGone() {
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(duplicateErrorMessage)),
				"User already exists message should not be visible anymore");
	}*/

	public void verifyBlankCompanyNameMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(companyNameBlankMessage)).getText(),
				"Company Name is required.", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(companyNameBlankMessage)),
						"Company Name is required message should not be visible anymore");
	}

	public void verifyBlankEmailMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(emailBlankMessage)).getText(),
				"Email is required.", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(emailBlankMessage)),
				"Email is required message should not be visible anymore");
	}

	public void verifyInvalidEmailAndPassword() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(pleaseEnterValidEmailAddressMessage)).getText(),
				"Please enter a valid email address.", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(pleaseEnterValidEmailAddressMessage)),
				"Please enter a valid email address message should not be visible anymore");
	}

	public void verifyPasswordMustBeAtLeast8CharctersLongMessage() {
		Assert.assertEquals(
				wait.until(ExpectedConditions.visibilityOf(passwordMustHaveRequiredNumberCharactersMessage)).getText(),
				"Password must be at least 8 characters long.", "Message Text Mismatch");
		Assert.assertTrue(
				wait.until(ExpectedConditions.invisibilityOf(passwordMustHaveRequiredNumberCharactersMessage)),
				"Password must be at least 8 characters long message should not be visible anymore");
	}

	public void verifyPasswordDoNotMatchMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(passwordNotMatchMessage)).getText(),
				"Passwords do not match.", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(passwordNotMatchMessage)),
				"Passwords do not match message should not be visible anymore");
	}
}
