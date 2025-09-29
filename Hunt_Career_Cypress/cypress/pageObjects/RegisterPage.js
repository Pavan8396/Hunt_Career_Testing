import { BasePage } from "./BasePage";

export default class RegisterPage extends BasePage {
    getFirstNameField() {
        return cy.get("[data-cy=first-name-field]");
    }
    getLastNameField() {
        return cy.get("[data-cy=last-name-field]");
    }
    getEmailField() {
        return cy.get("[data-cy=email-field]");
    }
    getPasswordField() {
        return cy.get("[data-cy=password-field]");
    }
    getConfirmPasswordField() {
        return cy.get("[data-cy=confirm-password-field]");
    }
    getPhoneNumberField() {
        return cy.get("[data-cy=phone-number-field]");
    }
    getFirstNextButton() {
        return cy.get("[data-cy=first-next-button]");
    }
    getSecondNextButton() {
        return cy.get("[data-cy=second-next-button]");
    }
    getSignupButton() {
        return cy.get("[data-cy=signup-button]");
    }
    getSuccessCreationMessage() {
        return cy.contains("Registered successfully! Please login.");
    }
    getDuplicateErrorMessage() {
        return cy.contains("User already exists");
    }
    getDuplicateErrorMessageForEmployer() {
        return cy.contains("Employer already exists");
    }
    getFirstNameBlankMessage() {
        return cy.contains("First Name is required.");
    }
    getEmployerNameBlankMessage() {
        return cy.contains("Company Name is required.");
    }
    getLastNameBlankMessage() {
        return cy.contains("Last Name is required.");
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
    getPhoneNumberRequiredMessage() {
        return cy.contains("Phone Number is required.");
    }
    getInvalidPhoneNumberMessage() {
        return cy.contains("Please enter a valid 10-digit phone number.");
    }
    getRegisterLinkHomePage() {
        return cy.contains("Signup");
    }
    getEmployerSignupLink() {
        return cy.contains("Sign up");
    }
    getEmployerRegisterNameField() {
        return cy.get("[data-cy=employer-name-field]");
    }
    getEmployerRegisterEmailField() {
        return cy.get("[data-cy=employer-email-field]");
    }
    getEmployerRegisterPasswordField() {
        return cy.get("[data-cy=employer-password-field]");
    }
    getEmployerRegisterConfirmPasswordField() {
        return cy.get("[data-cy=employer-confirm-password-field]");
    }
    getEmployerRegisterSignupButton() {
        return cy.get("[data-cy=employer-signup-button]");
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
    enterEmployerName(name) {
        if (name) this.typeInElement(this.getEmployerRegisterNameField(), name);
    }
    enterEmployerEmail(email) {
        if (email) this.typeInElement(this.getEmployerRegisterEmailField(), email);
    }
    enterEmployerPassword(password) {
        if (password) this.typeInElement(this.getEmployerRegisterPasswordField(), password);
    }
    enterEmployerConfirmPassword(confirmPassword) {
        if (confirmPassword) this.typeInElement(this.getEmployerRegisterConfirmPasswordField(), confirmPassword);
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
    clickEmployerSignupButton() {
        this.clickElement(this.getEmployerRegisterSignupButton());
    }

    navigate() {
        cy.visit("/");
        this.navigateToJobSeeker();
        this.clickElement(this.getRegisterLinkHomePage());
    }

    employerNavigate() {
        cy.visit("/");
        this.navigateToEmployer();
        this.clickElement(this.getEmployerSignupLink());
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

    employerRegister(name, email, password, confirmPassword) {
        this.enterEmployerName(name);
        this.enterEmployerEmail(email);
        this.enterEmployerPassword(password);
        this.enterEmployerConfirmPassword(confirmPassword);
        this.clickEmployerSignupButton();
    }
}
