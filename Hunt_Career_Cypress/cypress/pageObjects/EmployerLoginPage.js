import { BasePage } from "./BasePage";

export default class EmployerLoginPage extends BasePage {
    getEmailField() {
        return cy.xpath("//input[@id='email']");
    }

    getPasswordField() {
        return cy.xpath("//input[@id='password']");
    }

    getLoginButton() {
        return cy.xpath("//button[@type='submit']");
    }

    getSuccessCreationMessage() {
        return cy.xpath("//div[contains(text(), 'Registered successfully! Please login.')]");
    }

    getInvalidEmailOrPasswordMessage() {
        return cy.xpath("//div[contains(text(), 'Invalid email or password')]");
    }

    getInvalidEmailAddressMessage() {
        return cy.xpath("//div[contains(text(), 'Please enter a valid email address.')]");
    }

    getPasswordLengthErrorMessage() {
        return cy.xpath("//div[contains(text(), 'Password must be at least 8 characters long.')]");
    }

    getEmptyEmailMessage() {
        return cy.xpath("//div[contains(text(), 'Email is required.')]");
    }

    getEmptyPasswordMessage() {
        return cy.xpath("//div[contains(text(), 'Password is required.')]");
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