import { BasePage } from "./BasePage";

export class LoginPage extends BasePage {
  constructor(page) {
    super(page);
    this.getEmailField = page.locator("//input[@id='email']");
    this.getPasswordField = page.locator("//input[@id='password']");
    this.getLoginButton = page.locator("//button[@aria-label='Login']");
    this.getSuccessCreationMessage = page.locator("//div[contains(text(), 'Registered successfully! Please login.')]")
    this.getInvalidEmailOrPasswordMessage = page.locator("//div[contains(text(), 'Invalid email or password')]");
    this.getInvalidEmailAddressMessage = page.locator("//div[contains(text(), 'Please enter a valid email address.')]");
    this.getPasswordLengthErrorMessage = page.locator("//div[contains(text(), 'Password must be at least 8 characters long.')]");
    this.getEmptyEmailMessage = page.locator("//div[contains(text(), 'Email is required.')]");
    this.getEmptyPasswordMessage = page.locator("//div[contains(text(), 'Password is required.')]");
    this.getLoginHomeLink = page.locator("//a[contains(text(), 'Login')]");
  }

  async navigate() {
    await this.navigateToJobSeeker();
    await this.clickElement(this.getLoginHomeLink);
  }

  async login(email, password) {
    await this.typeInElement(this.getEmailField, email);
    await this.typeInElement(this.getPasswordField, password);
    await this.clickElement(this.getLoginButton);
  }
}