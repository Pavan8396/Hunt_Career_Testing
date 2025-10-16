package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends PageBase {

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
    public WebElement duplicateErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'First Name is required.')]")
    public WebElement firstNameBlankMessage;

    @FindBy(xpath = "//div[contains(text(),'Last Name is required.')]")
    public WebElement lastNameBlankMessage;

    @FindBy(xpath = "//div[contains(text(),'Email is required.')]")
    public WebElement emailBlankMessage;

    @FindBy(xpath = "//div[contains(text(),'Please enter a valid email address.')]")
    public WebElement pleaseEnterValidEmailAddressMessage;

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 8 characters long.')]")
    public WebElement passwordMustHaveRequiredNumberCharactersMessage;

    @FindBy(xpath = "//div[contains(text(),'Passwords do not match.')]")
    public WebElement passwordNotMatchMessage;

    @FindBy(xpath = "//div[contains(text(),'Phone Number is required.')]")
    public WebElement phoneNumberRequiredMessage;

    @FindBy(xpath = "//div[contains(text(),'Please enter a valid 10-digit phone number.')]")
    public WebElement phoneNumberInvalidMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstNameText) {
        typeInElement(firstNameField, firstNameText);
    }

    public void enterLastName(String lastNameText) {
        typeInElement(lastNameField, lastNameText);
    }

    public void clickFirstNextButton() {
        clickElement(firstNextButton);
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

    public void clickSecondNextButton() {
        clickElement(secondNextButton);
    }

    public void enterPhoneNumber(String phoneNumber) {
        typeInElement(phoneNumberField, phoneNumber);
    }

    public LoginPage clickSignUpButton() {
        clickElement(signUpButton);
        return new LoginPage(driver);
    }
}
