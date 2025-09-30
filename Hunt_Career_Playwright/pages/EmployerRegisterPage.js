import { BasePage } from "./BasePage";

export class EmployerRegisterPage extends BasePage {
  constructor(page) {
    super(page);
    this.getNameField = page.locator("//input[@id='companyName']");
    this.getEmailField = page.locator("#email");
    this.getPasswordField = page.locator("#password");
    this.getConfirmPasswordField = page.locator("#confirmPassword");
    this.getSignupButton = page.locator("//button[@type='submit']");
    this.getDuplicateErrorMessage = page.locator(
      "//div[contains(text(),'Employer already exists')]",
    );
    this.getNameBlankMessage = page.locator(
      "//div[contains(text(),'Company Name is required.')]",
    );
    this.getInvalidEmailAddressMessage = page.locator(
      "//div[contains(text(), 'Please enter a valid email address.')]",
    );
    this.getEmailBlankMessage = page.locator(
      "//div[contains(text(), 'Email is required.')]",
    );
    this.getPasswordLengthErrorMessage = page.locator(
      "//div[contains(text(), 'Password must be at least 8 characters long.')]",
    );
    this.getPasswordMismatchMessage = page.locator(
      "//div[contains(text(), 'Passwords do not match.')]",
    );
    this.getRegisterLinkHomePage = page.locator("//a[contains(text(), 'Sign up')]");
  }

  async navigate() {
    await this.navigateToEmployer();
    await this.clickElement(this.getRegisterLinkHomePage);
  }

  async register(
    name,
    email,
    password,
    confirmPassword,
  ) {
    await this.typeInElement(this.getNameField, name);
    await this.typeInElement(this.getEmailField, email);
    await this.typeInElement(this.getPasswordField, password);
    await this.typeInElement(this.getConfirmPasswordField, confirmPassword);
    await this.clickElement(this.getSignupButton);
  }
}
