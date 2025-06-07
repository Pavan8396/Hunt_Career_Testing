import { BasePage } from "./basePage";

class RegisterPage extends BasePage {
    FirstNameField() {
        return cy.xpath("//input[@id='firstName']");
    }
    LastNameField() {
        return cy.xpath("//input[@id='lastName']");
    }
    EmailField() {
        return cy.xpath("//input[@id='email']");
    }
    PasswordField() {
        return cy.xpath("//input[@id='password']");
    }
    ConfirmPasswordField() {
        return cy.xpath("//input[@id='confirmPassword']");
    }
    PhoneNumberField() {
        return cy.xpath("//input[@id='phoneNumber']");
    }
    FirstNextButton() {
        return cy.xpath("//button[@type='button']");
    }
    SecondNextButton() {
        return cy.xpath("//button[normalize-space()='Next']");
    }
    SignupButton() {
        return cy.xpath("//button[@aria-label='Sign up']");
    }
    checkSuccessCreationMessage() {
        return cy.xpath("//div[contains(text(), 'Registered successfully! Please login.')]");
    }
    checkDuplicateErrorMessage() {
        return cy.xpath("//div[contains(text(),'User already exists')]");
    }
    checkFirstNameBlankMessage() {
        return cy.xpath("//div[contains(text(),'First Name is required.')]");
    }
    checkLastNameBlankMessage() {
        return cy.xpath("//div[contains(text(),'Last Name is required.')]");
    }
    checkPleaseEnterValidEmailAddressMessage() {
        return cy.xpath("//div[contains(text(), 'Please enter a valid email address.')]");
    }
    checkEmailBlankMessage() {
        return cy.xpath("//div[contains(text(), 'Email is required.')]");
    }
    checkPasswordMustHaveRequiredNumberCharactersMessage() {
        return cy.xpath("//div[contains(text(), 'Password must be at least 8 characters long.')]");
    }
    checkPasswordNotMatchMessage() {
        return cy.xpath("//div[contains(text(), 'Passwords do not match.')]");
    }
    checkPhoneNumberRequiredMessage() {
        return cy.xpath("//div[contains(text(), 'Phone Number is required.')]");
    }
    checkPhoneNumberInvalidMessage(){
        return cy.xpath("//div[contains(text(), 'Please enter a valid 10-digit phone number.')]")
    }


    // Actions
    enterFirstNameField(firstName) {
        if (firstName) {
            this.typeInElement(this.FirstNameField(), firstName);
        }
    }
    enterLastNameField(lastName) {
        if (lastName) {
            this.typeInElement(this.LastNameField(), lastName);
        }
    }
    enterEmailField(email) {
        if (email) {
            this.typeInElement(this.EmailField(), email);
        }
    }
    enterPasswordField(password) {
        if (password) {
            this.typeInElement(this.PasswordField(), password);
        }
    }
    enterConfirmPasswordField(confirmPassword) {
        if (confirmPassword) {
            this.typeInElement(this.ConfirmPasswordField(), confirmPassword);
        }
    }
    enterPhoneNumberField(phoneNumber) {
        if (phoneNumber) {
            this.typeInElement(this.PhoneNumberField(), phoneNumber);
        }
    }
    clickFirstNextButton() {
        this.clickElement(this.FirstNextButton());
    }
    clickSecondNextButton() {
        this.clickElement(this.SecondNextButton());
    }
    clickSignupButton() {
        this.clickElement(this.SignupButton());
    }

    register(firstName, lastName, email, password, confirmPassword, phoneNumber) {
        this.enterFirstNameField(firstName);
        this.enterLastNameField(lastName);
        this.clickFirstNextButton();
        this.enterEmailField(email);
        this.enterPasswordField(password);
        this.enterConfirmPasswordField(confirmPassword);
        this.clickSecondNextButton();
        this.enterPhoneNumberField(phoneNumber);
        this.clickSignupButton();
    }

    registerFirstPage(firstName, lastName){
        this.enterFirstNameField(firstName);
        this.enterLastNameField(lastName);
        this.clickFirstNextButton();
    }

    registerSecondPage(firstName, lastName, email, password, confirmPassword){
        this.enterFirstNameField(firstName);
        this.enterLastNameField(lastName);
        this.clickFirstNextButton();
        this.enterEmailField(email);
        this.enterPasswordField(password);
        this.enterConfirmPasswordField(confirmPassword);
        this.clickSecondNextButton();
    }

    validationMessage(element) {
        this.waitUntilVisible(element);
    }
    validationMessageNotExist(element) {
        this.waitUntilNotVisible(element);
    }
}

export const registerPage = new RegisterPage();
