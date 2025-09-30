import { BasePage } from "./BasePage";

export class RegisterPage extends BasePage {
  constructor(page) {
    super(page);
    this.getFirstNameField = page.locator("#firstName");
    this.getLastNameField = page.locator("#lastName");
    this.getEmailField = page.locator("#email");
    this.getPasswordField = page.locator("#password");
    this.getConfirmPasswordField = page.locator("#confirmPassword");
    this.getPhoneNumberField = page.locator("#phoneNumber");
    this.getFirstNextButton = page.locator("//button[@type='button']");
    this.getSecondNextButton = page.locator("//button[normalize-space()='Next']");
    this.getSignupButton = page.locator("//button[@aria-label='Sign up']");
    // this.checkSuccessCreationMessage = page.locator(
    //   "//div[contains(text(), 'Registered successfully! Please login.')]",
    // );
    this.getDuplicateErrorMessage = page.locator(
      "//div[contains(text(),'User already exists')]",
    );
    this.getFirstNameBlankMessage = page.locator(
      "//div[contains(text(),'First Name is required.')]",
    );
    this.getLastNameBlankMessage = page.locator(
      "//div[contains(text(),'Last Name is required.')]",
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
    this.getPhoneNumberRequiredMessage = page.locator(
      "//div[contains(text(), 'Phone Number is required.')]",
    );
    this.getInvalidPhoneNumberMessage = page.locator(
      "//div[contains(text(), 'Please enter a valid 10-digit phone number.')]",
    );
    this.getRegisterLinkHomePage = page.locator(
      "//a[contains(text(), 'Signup')]",
    );
  }

  async navigate() {
    await this.navigateToJobSeeker();
    await this.clickElement(this.getRegisterLinkHomePage);
  }

  async register(
    firstName,
    lastName,
    email,
    password,
    confirmPassword,
    phoneNumber,
  ) {
    await this.typeInElement(this.getFirstNameField, firstName);
    await this.typeInElement(this.getLastNameField, lastName);
    await this.clickElement(this.getFirstNextButton);
    await this.typeInElement(this.getEmailField, email);
    await this.typeInElement(this.getPasswordField, password);
    await this.typeInElement(this.getConfirmPasswordField, confirmPassword);
    await this.clickElement(this.getSecondNextButton);
    await this.typeInElement(this.getPhoneNumberField, phoneNumber);
    await this.clickElement(this.getSignupButton);
  }
  async fillFirstRegistrationPage(firstName, lastName) {
    await this.typeInElement(this.getFirstNameField, firstName);
    await this.typeInElement(this.getLastNameField, lastName);
    await this.clickElement(this.getFirstNextButton);
  }

  async fillSecondRegistrationPage(
    firstName,
    lastName,
    email,
    password,
    confirmPassword,
  ) {
    await this.typeInElement(this.getFirstNameField, firstName);
    await this.typeInElement(this.getLastNameField, lastName);
    await this.clickElement(this.getFirstNextButton);
    await this.typeInElement(this.getEmailField, email);
    await this.typeInElement(this.getPasswordField, password);
    await this.typeInElement(this.getConfirmPasswordField, confirmPassword);
    await this.clickElement(this.getSecondNextButton);
  }
}