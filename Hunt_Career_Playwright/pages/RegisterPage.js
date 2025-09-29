import { BasePage } from "./BasePage";

export class RegisterPage extends BasePage {
  constructor(page) {
    super(page);
    this.firstNameField = page.locator("#firstName");
    this.lastNameField = page.locator("#lastName");
    this.emailField = page.locator("#email");
    this.passwordField = page.locator("#password");
    this.confirmPasswordField = page.locator("#confirmPassword");
    this.phoneNumberField = page.locator("#phoneNumber");
    this.signupButton = page.locator("//button[@aria-label='Sign up']");

    // Locators for messages
    this.successCreationMessage = page.locator("//div[contains(text(), 'Registered successfully! Please login.')]");
    this.duplicateErrorMessage = page.locator("//div[contains(text(),'User already exists')]");
    this.firstNameRequiredMessage = page.locator("//div[contains(text(),'First Name is required.')]");
    this.lastNameRequiredMessage = page.locator("//div[contains(text(),'Last Name is required.')]");
    this.emailRequiredMessage = page.locator("//div[contains(text(), 'Email is required.')]");
    this.invalidEmailMessage = page.locator("//div[contains(text(), 'Please enter a valid email address.')]");
    this.passwordRequiredMessage = page.locator("//div[contains(text(), 'Password is required.')]");
    this.passwordLengthMessage = page.locator("//div[contains(text(), 'Password must be at least 8 characters long.')]");
    this.passwordMismatchMessage = page.locator("//div[contains(text(), 'Passwords do not match.')]");
    this.phoneNumberRequiredMessage = page.locator("//div[contains(text(), 'Phone Number is required.')]");
  }

  async navigate() {
    await this.page.goto("/signup");
  }

  async register(firstName, lastName, email, password, confirmPassword, phoneNumber) {
    if (firstName) await this.typeInElement(this.firstNameField, firstName);
    if (lastName) await this.typeInElement(this.lastNameField, lastName);
    if (email) await this.typeInElement(this.emailField, email);
    if (password) await this.typeInElement(this.passwordField, password);
    if (confirmPassword) await this.typeInElement(this.confirmPasswordField, confirmPassword);
    if (phoneNumber) await this.typeInElement(this.phoneNumberField, phoneNumber);
    await this.clickElement(this.signupButton);
  }
}