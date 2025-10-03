package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployerLoginPage {
    WebDriver driver;

    // Page Factory - OR:
    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Forgot Password?')]")
    WebElement forgotPasswordLink;

    @FindBy(xpath = "//div[contains(text(),'Invalid email or password')]")
    WebElement invalidEmailOrPasswordMessage;

    @FindBy(xpath = "//div[contains(text(),'Invalid email address')]")
    WebElement invalidEmailAddressMessage;

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 8 characters')]")
    WebElement passwordLengthErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Email is required')]")
    WebElement emptyEmailMessage;

    @FindBy(xpath = "//div[contains(text(),'Password is required')]")
    WebElement emptyPasswordMessage;

    @FindBy(xpath = "//div[contains(text(),'Registered successfully! Please login.')]")
    private WebElement registerSuccessMessage;

    // Initializing the Page Objects:
    public EmployerLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions:
    public String validateLoginPageTitle() {
        return driver.getTitle();
    }

    public SearchPage login(String un, String pwd) {
        email.sendKeys(un);
        password.sendKeys(pwd);
        loginButton.click();
        return new SearchPage(driver);
    }

    public WebElement getInvalidEmailOrPasswordMessage() {
        return invalidEmailOrPasswordMessage;
    }

    public WebElement getInvalidEmailAddressMessage() {
        return invalidEmailAddressMessage;
    }

    public WebElement getPasswordLengthErrorMessage() {
        return passwordLengthErrorMessage;
    }

    public WebElement getEmptyEmailMessage() {
        return emptyEmailMessage;
    }

    public WebElement getEmptyPasswordMessage() {
        return emptyPasswordMessage;
    }

    public WebElement getSuccessCreationMessage() {
        return registerSuccessMessage;
    }
}