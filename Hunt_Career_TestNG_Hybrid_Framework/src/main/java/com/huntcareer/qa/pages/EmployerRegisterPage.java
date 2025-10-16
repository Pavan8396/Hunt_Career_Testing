package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployerRegisterPage extends PageBase {

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
    public WebElement duplicateErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Company Name is required.')]")
    public WebElement companyNameBlankMessage;

    @FindBy(xpath = "//div[contains(text(),'Email is required.')]")
    public WebElement emailBlankMessage;

    @FindBy(xpath = "//div[contains(text(),'Please enter a valid email address.')]")
    public WebElement pleaseEnterValidEmailAddressMessage;

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 8 characters long.')]")
    public WebElement passwordMustHaveRequiredNumberCharactersMessage;

    @FindBy(xpath = "//div[contains(text(),'Passwords do not match.')]")
    public WebElement passwordNotMatchMessage;

    @FindBy(xpath = "//a[contains(text(), 'Sign up')]")
    private WebElement SignupLink;

    public EmployerRegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterCompanyName(String companyNameText) {
        typeInElement(companyNameFeild, companyNameText);
    }

    public void enterEmail(String emailText) {
        typeInElement(emailField, emailText);
    }

    public void enterPassword(String passwordText) {
        typeInElement(passwordField, passwordText);
    }

    public void enterConfirmPassword(String confirmPasswordText) {
        typeInElement(confirmPasswordField, confirmPasswordText);
    }

    public void clickSignupLink() {
        clickElement(SignupLink);
    }

    public EmployerLoginPage clickSignUpButton() {
        clickElement(signUpButton);
        return new EmployerLoginPage(driver);
    }
}
