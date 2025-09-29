import { BasePage } from "./BasePage";

export class EmployerLoginPage extends BasePage {
  constructor(page) {
    super(page);
    this.emailField = page.locator("//input[@id='email']");
    this.passwordField = page.locator("//input[@id='password']");
    this.loginButton = page.locator("//button[@aria-label='Login']");
    this.invalidCredentialsMessage = page.locator("//div[contains(text(), 'Invalid email or password')]");
    this.validEmailMsg = page.locator("//div[contains(text(), 'Please enter a valid email address.')]");
    this.passwordLengthMsg = page.locator("//div[contains(text(), 'Password must be at least 8 characters long.')]");
    this.emailRequiredMsg = page.locator("//div[contains(text(), 'Email is required.')]");
    this.passwordRequiredMsg = page.locator("//div[contains(text(), 'Password is required.')]");
    this.successCreationMessage = page.locator("//div[contains(text(),'Account created successfully')]");
  }

  async navigate() {
    await this.page.goto("/employer-login");
  }

  async login(email, password) {
    await this.typeInElement(this.emailField, email);
    await this.typeInElement(this.passwordField, password);
    await this.clickElement(this.loginButton);
  }
}