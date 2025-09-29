import { BasePage } from "./BasePage";

export default class EmployerRegisterPage extends BasePage {
    getNameField() {
        return cy.get("[data-cy=employer-name-field]");
    }
    getEmailField() {
        return cy.get("[data-cy=employer-email-field]");
    }
    getPasswordField() {
        return cy.get("[data-cy=employer-password-field]");
    }
    getConfirmPasswordField() {
        return cy.get("[data-cy=employer-confirm-password-field]");
    }
    getSignupButton() {
        return cy.get("[data-cy=employer-signup-button]");
    }
    getSuccessCreationMessage() {
        return cy.contains("Registered successfully! Please login.");
    }
    getDuplicateErrorMessage() {
        return cy.contains("Employer already exists");
    }
    getNameBlankMessage() {
        return cy.contains("Company Name is required.");
    }
    getInvalidEmailAddressMessage() {
        return cy.contains("Please enter a valid email address.");
    }
    getEmailBlankMessage() {
        return cy.contains("Email is required.");
    }
    getPasswordLengthErrorMessage() {
        return cy.contains("Password must be at least 8 characters long.");
    }
    getPasswordMismatchMessage() {
        return cy.contains("Passwords do not match.");
    }
    getRegisterLinkHomePage() {
        return cy.contains("Sign up");
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