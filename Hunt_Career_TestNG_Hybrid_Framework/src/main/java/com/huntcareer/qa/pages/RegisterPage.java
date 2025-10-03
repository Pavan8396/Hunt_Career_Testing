package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//input[@id='firstName']")
	private WebElement firstNameField;

	@FindBy(xpath = "//input[@id='lastName']")
	private WebElement lastNameField;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@id='confirmPassword']")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//input[@id='phoneNumber']")
	private WebElement phoneNumberField;

	@FindBy(xpath = "//button[@type='button']")
	private WebElement firstNextButton;

	@FindBy(xpath = "//button[normalize-space()='Next']")
	private WebElement secondNextButton;

	@FindBy(xpath = "//button[@aria-label='Sign up']")
	private WebElement signUpButton;

	@FindBy(xpath = "//div[contains(text(),'User already exists')]")
	private WebElement duplicateErrorMessage;

	@FindBy(xpath = "//div[contains(text(),'First Name is required.')]")
	private WebElement firstNameBlankMessage;

	@FindBy(xpath = "//div[contains(text(),'Last Name is required.')]")
	private WebElement lastNameBlankMessage;

	@FindBy(xpath = "//div[contains(text(),'Email is required.')]")
	private WebElement emailBlankMessage;

	@FindBy(xpath = "//div[contains(text(),'Please enter a valid email address.')]")
	private WebElement pleaseEnterValidEmailAddressMessage;

	@FindBy(xpath = "//div[contains(text(),'Password must be at least 8 characters long.')]")
	private WebElement passwordMustHaveRequiredNumberCharactersMessage;

	@FindBy(xpath = "//div[contains(text(),'Passwords do not match.')]")
	private WebElement passwordNotMatchMessage;

	@FindBy(xpath = "//div[contains(text(),'Phone Number is required.')]")
	private WebElement phoneNumberRequiredMessage;

	@FindBy(xpath = "//div[contains(text(),'Please enter a valid 10-digit phone number.')]")
	private WebElement phoneNumberInvalidMessage;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void clickFirstNextButton() {
		firstNextButton.click();
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

	public void clickSecondNextButton() {
		secondNextButton.click();
	}

	public void enterPhoneNumber(String phoneNumber) {
		phoneNumberField.sendKeys(phoneNumber);
	}

	public LoginPage clickSignUpButton() {
		signUpButton.click();
		return new LoginPage(driver);
	}

    public void register(String fn, String ln, String em, String pwd, String cpwd, String ph) {
        enterFirstName(fn);
        enterLastName(ln);
        clickFirstNextButton();
        enterEmail(em);
        enterPassword(pwd);
        enterConfirmPassword(cpwd);
        clickSecondNextButton();
        enterPhoneNumber(ph);
        clickSignUpButton();
    }

	public WebElement getDuplicateErrorMessage() {
		return duplicateErrorMessage;
	}

    public void verifyUserAlreadyExistsMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(duplicateErrorMessage)).getText(),
				"User already exists", "Message Text Mismatch");
	}

	public void verifyBlankFirstNameMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(firstNameBlankMessage)).getText(),
				"First Name is required.", "Message Text Mismatch");
	}

	public void verifyBlankLastNameMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(lastNameBlankMessage)).getText(),
				"Last Name is required.", "Message Text Mismatch");
	}

	public void verifyBlankEmailMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(emailBlankMessage)).getText(),
				"Email is required.", "Message Text Mismatch");
	}

	public void verifyInvalidEmailAndPassword() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(pleaseEnterValidEmailAddressMessage)).getText(),
				"Please enter a valid email address.", "Message Text Mismatch");
	}

	public void verifyPasswordMustBeAtLeast8CharctersLongMessage() {
		Assert.assertEquals(
				wait.until(ExpectedConditions.visibilityOf(passwordMustHaveRequiredNumberCharactersMessage)).getText(),
				"Password must be at least 8 characters long.", "Message Text Mismatch");
	}

	public void verifyPasswordDoNotMatchMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(passwordNotMatchMessage)).getText(),
				"Passwords do not match.", "Message Text Mismatch");
	}

	public void verifyPhoneNumberIsRequiredMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(phoneNumberRequiredMessage)).getText(),
				"Phone Number is required.", "Message Text Mismatch");
	}

	public void verifyInvalidPhoneNumberMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(phoneNumberInvalidMessage)).getText(),
				"Please enter a valid 10-digit phone number.", "Message Text Mismatch");
	}
}