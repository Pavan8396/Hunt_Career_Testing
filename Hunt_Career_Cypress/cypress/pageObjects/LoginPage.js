import { BasePage } from "./BasePage";

export default class LoginPage extends BasePage {
    getEmailField() {
        return cy.xpath("//input[@id='email']");
    }

    getPasswordField() {
        return cy.xpath("//input[@id='password']");
    }

    getLoginButton() {
        return cy.xpath("//button[@aria-label='Login']");
    }

    getEmployerLoginButton() {
        return cy.xpath("//button[@type='submit']");
    }

    getLoginSuccessMessage() {
        return cy.xpath("//div[contains(text(), 'Logged in successfully!')]");
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

    getLoginHomeLink() {
        return cy.xpath("//a[contains(text(), 'Login')]");
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

    clickEmployerLoginButton() {
        this.clickElement(this.getEmployerLoginButton());
    }

    navigate() {
        cy.visit("/");
        this.navigateToJobSeeker();
        this.clickElement(this.getLoginHomeLink());
    }

    login(email, password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickLoginButton();
    }

    employerNavigate() {
        cy.visit("/");
        this.navigateToEmployer();
    }

    employerLogin(email, password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickEmployerLoginButton();
    }
}