import { BasePage } from "./BasePage";
import LoginPage from "./LoginPage";

export default class RegisterPage extends BasePage {
    getFirstNameField() {
        return cy.xpath("//input[@id='firstName']");
    }
    getLastNameField() {
        return cy.xpath("//input[@id='lastName']");
    }
    getEmailField() {
        return cy.xpath("//input[@id='email']");
    }
    getPasswordField() {
        return cy.xpath("//input[@id='password']");
    }
    getConfirmPasswordField() {
        return cy.xpath("//input[@id='confirmPassword']");
    }
    getPhoneNumberField() {
        return cy.xpath("//input[@id='phoneNumber']");
    }
    getFirstNextButton() {
        return cy.xpath("//button[@type='button']");
    }
    getSecondNextButton() {
        return cy.xpath("//button[normalize-space()='Next']");
    }
    getSignupButton() {
        return cy.xpath("//button[@aria-label='Sign up']");
    }
    getDuplicateErrorMessage() {
        return cy.xpath("//div[contains(text(),'User already exists')]");
    }
    getFirstNameBlankMessage() {
        return cy.xpath("//div[contains(text(),'First Name is required.')]");
    }
    getLastNameBlankMessage() {
        return cy.xpath("//div[contains(text(),'Last Name is required.')]");
    }
    getInvalidEmailAddressMessage() {
        return cy.xpath("//div[contains(text(), 'Please enter a valid email address.')]");
    }
    getEmailBlankMessage() {
        return cy.xpath("//div[contains(text(), 'Email is required.')]");
    }
    getPasswordLengthErrorMessage() {
        return cy.xpath("//div[contains(text(), 'Password must be at least 8 characters long.')]");
    }
    getPasswordMismatchMessage() {
        return cy.xpath("//div[contains(text(), 'Passwords do not match.')]");
    }
    getPhoneNumberRequiredMessage() {
        return cy.xpath("//div[contains(text(), 'Phone Number is required.')]");
    }
    getInvalidPhoneNumberMessage() {
        return cy.xpath("//div[contains(text(), 'Please enter a valid 10-digit phone number.')]");
    }
    getRegisterLinkHomePage() {
        return cy.xpath("//a[contains(text(), 'Signup')]");
    }

    enterFirstName(firstName) {
        if (firstName) this.typeInElement(this.getFirstNameField(), firstName);
    }
    enterLastName(lastName) {
        if (lastName) this.typeInElement(this.getLastNameField(), lastName);
    }
    enterEmail(email) {
        if (email) this.typeInElement(this.getEmailField(), email);
    }
    enterPassword(password) {
        if (password) this.typeInElement(this.getPasswordField(), password);
    }
    enterConfirmPassword(confirmPassword) {
        if (confirmPassword) this.typeInElement(this.getConfirmPasswordField(), confirmPassword);
    }
    enterPhoneNumber(phoneNumber) {
        if (phoneNumber) this.typeInElement(this.getPhoneNumberField(), phoneNumber);
    }

    clickFirstNextButton() {
        this.clickElement(this.getFirstNextButton());
    }
    clickSecondNextButton() {
        this.clickElement(this.getSecondNextButton());
    }
    clickSignupButton() {
        this.clickElement(this.getSignupButton());
    }

    navigate() {
        cy.visit("/");
        this.navigateToJobSeeker();
        this.clickElement(this.getRegisterLinkHomePage());
    }

    register(firstName, lastName, email, password, confirmPassword, phoneNumber) {
        this.enterFirstName(firstName);
        this.enterLastName(lastName);
        this.clickFirstNextButton();
        this.enterEmail(email);
        this.enterPassword(password);
        this.enterConfirmPassword(confirmPassword);
        this.clickSecondNextButton();
        this.enterPhoneNumber(phoneNumber);
        this.clickSignupButton();
    }

    fillFirstRegistrationPage(firstName, lastName) {
        this.enterFirstName(firstName);
        this.enterLastName(lastName);
        this.clickFirstNextButton();
    }

    fillSecondRegistrationPage(firstName, lastName, email, password, confirmPassword) {
        this.fillFirstRegistrationPage(firstName, lastName);
        this.enterEmail(email);
        this.enterPassword(password);
        this.enterConfirmPassword(confirmPassword);
        this.clickSecondNextButton();
    }
}