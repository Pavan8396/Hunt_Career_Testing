import { BasePage } from "./BasePage";

export class EmployerRegisterPage extends BasePage {
    constructor(page) {
        super(page);
        this.nameField = page.locator("//input[@id='name']");
        this.emailField = page.locator("//input[@id='email']");
        this.passwordField = page.locator("//input[@id='password']");
        this.confirmPasswordField = page.locator("//input[@id='confirmPassword']");
        this.registerButton = page.locator("//button[@aria-label='Register']");
        this.duplicateErrorMessage = page.locator("//div[contains(text(), 'User with this email already exists')]");
        this.nameRequiredMessage = page.locator("//div[contains(text(), 'Name is required.')]");
        this.emailRequiredMessage = page.locator("//div[contains(text(), 'Email is required.')]");
        this.passwordRequiredMessage = page.locator("//div[contains(text(), 'Password is required.')]");
        this.confirmPasswordRequiredMessage = page.locator("//div[contains(text(), 'Confirm Password is required.')]");
        this.passwordMismatchMessage = page.locator("//div[contains(text(), 'Passwords must match')]");
    }

    async navigate() {
        await this.page.goto("/employer-register");
    }

    async register(name, email, password, confirmPassword) {
        await this.typeInElement(this.nameField, name);
        await this.typeInElement(this.emailField, email);
        await this.typeInElement(this.passwordField, password);
        await this.typeInElement(this.confirmPasswordField, confirmPassword);
        await this.clickElement(this.registerButton);
    }
}