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

    public void verifyEmailIsRequired() {
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(EmailIsRequiredMessage)).getText(),
                "Email is required.", "Message Text Mismatch");
    }

    public void verifyPleaseEnterValidEmailAddress() {
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(pleaseEnterValidEmailAddressMessage)).getText(),
                "Please enter a valid email address.", "Message Text Mismatch");
    }

    public void verifyPasswordIsRequired() {
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(passwordIsRequiredMessage)).getText(),
                "Password is required.", "Message Text Mismatch");
    }

    public void verifyPasswordMustHaveAtLeast8CharactersLong() {
        Assert.assertEquals(
                wait.until(ExpectedConditions.visibilityOf(passwordMustHaveATLeast8CharactersLongMessage)).getText(),
                "Password must be at least 8 characters long.", "Message Text Mismatch");
    }

    public void verifyRegisterSuccess() {
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(registerSuccessMessage)).getText(),
                "Registered successfully! Please login.", "Message Text Mismatch");
    }

    public SearchPage login(String un, String pwd) {
        emailField.sendKeys(un);
        passwordField.sendKeys(pwd);
        loginButton.click();
        return new SearchPage(driver);
    }

    public WebElement getSuccessCreationMessage() {
        return registerSuccessMessage;
    }

    public WebElement getInvalidEmailOrPasswordMessage() {
        return invalidEmailAndPassword;
    }

    public WebElement getInvalidEmailAddressMessage() {
        return pleaseEnterValidEmailAddressMessage;
    }

    public WebElement getPasswordLengthErrorMessage() {
        return passwordMustHaveATLeast8CharactersLongMessage;
    }

    public WebElement getEmptyEmailMessage() {
        return EmailIsRequiredMessage;
    }

    public WebElement getEmptyPasswordMessage() {
        return passwordIsRequiredMessage;
    }
}