package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployerLoginPage extends PageBase {

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(), 'Invalid email or password')]")
    public WebElement invalidEmailAndPassword;

    @FindBy(xpath = "//div[contains(text(), 'Password must be at least 8 characters long.')]")
    public WebElement passwordMustHaveATLeast8CharactersLongMessage;

    @FindBy(xpath = "//div[contains(text(), 'Please enter a valid email address.')]")
    public WebElement pleaseEnterValidEmailAddressMessage;

    @FindBy(xpath = "//div[contains(text(), 'Email is required.')]")
    public WebElement EmailIsRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Password is required.')]")
    public WebElement passwordIsRequiredMessage;

    @FindBy(xpath = "//div[contains(text(), 'Registered successfully! Please login.')]")
    public WebElement registerSuccessMessage;

    public EmployerLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String emailText) {
        typeInElement(emailField, emailText);
    }

    public void enterPassword(String passwordText) {
        typeInElement(passwordField, passwordText);
    }

    public HomePage clickLoginButton() {
        clickElement(loginButton);
        return new HomePage(driver);
    }
}
