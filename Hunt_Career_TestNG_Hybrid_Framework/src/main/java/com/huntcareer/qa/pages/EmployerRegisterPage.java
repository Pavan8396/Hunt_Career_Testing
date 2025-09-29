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

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@aria-label='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//div[contains(text(), 'User with this email already exists')]")
    private WebElement duplicateErrorMessage;

    @FindBy(xpath = "//div[contains(text(), 'Name is required.')]")
    private WebElement nameRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Email is required.')]")
    private WebElement emailRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Password is required.')]")
    private WebElement passwordRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Confirm Password is required.')]")
    private WebElement confirmPasswordRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Passwords must match')]")
    private WebElement passwordMismatchMessage;

    public EmployerRegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void register(String name, String email, String password, String confirmPassword) {
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        registerButton.click();
    }

    public void verifyDuplicateErrorMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(duplicateErrorMessage)).isDisplayed());
    }

    public void verifyNameRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(nameRequiredMessage)).isDisplayed());
    }

    public void verifyEmailRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(emailRequiredMessage)).isDisplayed());
    }

    public void verifyPasswordRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordRequiredMessage)).isDisplayed());
    }

    public void verifyConfirmPasswordRequiredMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(confirmPasswordRequiredMessage)).isDisplayed());
    }

    public void verifyPasswordMismatchMessage() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordMismatchMessage)).isDisplayed());
    }
}