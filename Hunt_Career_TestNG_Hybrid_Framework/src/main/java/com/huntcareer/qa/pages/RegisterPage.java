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

    @FindBy(xpath = "//button[@aria-label='Sign up']")
    private WebElement signupButton;

    @FindBy(xpath = "//div[contains(text(), 'Registered successfully! Please login.')]")
    public WebElement successCreationMessage;

    @FindBy(xpath = "//div[contains(text(),'User already exists')]")
    public WebElement duplicateErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'First Name is required.')]")
    public WebElement firstNameRequiredMessage;

    @FindBy(xpath = "//div[contains(text(),'Last Name is required.')]")
    public WebElement lastNameRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Email is required.')]")
    public WebElement emailRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Please enter a valid email address.')]")
    public WebElement invalidEmailMessage;

    @FindBy(xpath = "//div[contains(text(), 'Password is required.')]")
    public WebElement passwordRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Password must be at least 8 characters long.')]")
    public WebElement passwordLengthMessage;

    @FindBy(xpath = "//div[contains(text(), 'Passwords do not match.')]")
    public WebElement passwordMismatchMessage;

    @FindBy(xpath = "//div[contains(text(), 'Phone Number is required.')]")
    public WebElement phoneNumberRequiredMessage;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void register(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber) {
        if (firstName != null && !firstName.isEmpty()) firstNameField.sendKeys(firstName);
        if (lastName != null && !lastName.isEmpty()) lastNameField.sendKeys(lastName);
        if (email != null && !email.isEmpty()) emailField.sendKeys(email);
        if (password != null && !password.isEmpty()) passwordField.sendKeys(password);
        if (confirmPassword != null && !confirmPassword.isEmpty()) confirmPasswordField.sendKeys(confirmPassword);
        if (phoneNumber != null && !phoneNumber.isEmpty()) phoneNumberField.sendKeys(phoneNumber);
        signupButton.click();
    }

    public void verifySuccessCreationMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(successCreationMessage)).isDisplayed());
    }

    public void verifyDuplicateErrorMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(duplicateErrorMessage)).isDisplayed());
    }

    public void verifyFirstNameRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(firstNameRequiredMessage)).isDisplayed());
    }

    public void verifyLastNameRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(lastNameRequiredMessage)).isDisplayed());
    }

    public void verifyEmailRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(emailRequiredMessage)).isDisplayed());
    }

    public void verifyInvalidEmailMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(invalidEmailMessage)).isDisplayed());
    }

    public void verifyPasswordRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordRequiredMessage)).isDisplayed());
    }

    public void verifyPasswordLengthMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordLengthMessage)).isDisplayed());
    }

    public void verifyPasswordMismatchMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordMismatchMessage)).isDisplayed());
    }

    public void verifyPhoneNumberRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(phoneNumberRequiredMessage)).isDisplayed());
    }
}