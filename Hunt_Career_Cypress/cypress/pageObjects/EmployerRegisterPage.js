import { BasePage } from "./BasePage";

export default class EmployerRegisterPage extends BasePage {
    getNameField() {
        return cy.xpath("//input[@id='companyName']");
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
    getSignupButton() {
        return cy.xpath("//button[@type='submit']");
    }
    getSuccessCreationMessage() {
        return cy.xpath("//div[contains(text(), 'Registered successfully! Please login.')]");
    }
    getDuplicateErrorMessage() {
        return cy.xpath("//div[contains(text(),'Employer already exists')]");
    }
    getNameBlankMessage() {
        return cy.xpath("//div[contains(text(),'Company Name is required.')]");
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
    getRegisterLinkHomePage() {
        return cy.xpath("//a[contains(text(), 'Sign up')]");
    }

    enterName(name) {
        if (name) this.typeInElement(this.getNameField(), name);
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

    clickSignupButton() {
        this.clickElement(this.getSignupButton());
    }

    navigate() {
        cy.visit("/");
        this.navigateToEmployer();
        this.clickElement(this.getRegisterLinkHomePage());
    }

    register(name, email, password, confirmPassword) {
        this.enterName(name);
        this.enterEmail(email);
        this.enterPassword(password);
        this.enterConfirmPassword(confirmPassword);
        this.clickSignupButton();
    }
}