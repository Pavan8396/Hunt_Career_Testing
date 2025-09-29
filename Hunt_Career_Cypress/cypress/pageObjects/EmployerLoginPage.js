import { BasePage } from "./BasePage";

export default class EmployerLoginPage extends BasePage {
    getEmailField() {
        return cy.get("[data-cy=email-field]");
    }

    getPasswordField() {
        return cy.get("[data-cy=password-field]");
    }

    getLoginButton() {
        return cy.get("[data-cy=login-button]");
    }

    getLoginSuccessMessage() {
        return cy.contains("Logged in successfully!");
    }

    getInvalidEmailOrPasswordMessage() {
        return cy.contains("Invalid email or password");
    }

    getInvalidEmailAddressMessage() {
        return cy.contains("Please enter a valid email address.");
    }

    getPasswordLengthErrorMessage() {
        return cy.contains("Password must be at least 8 characters long.");
    }

    getEmptyEmailMessage() {
        return cy.contains("Email is required.");
    }

    getEmptyPasswordMessage() {
        return cy.contains("Password is required.");
    }

    enterEmail(email) {
        this.typeInElement(this.getEmailField(), email);
    }

    enterPassword(password) {
        this.typeInElement(this.getPasswordField(), password);
    }

    clickLoginButton() {
        this.clickElement(this.getLoginButton());
    }

    navigate() {
        cy.visit("/");
        this.navigateToEmployer();
    }

    login(email, password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickLoginButton();
    }
}