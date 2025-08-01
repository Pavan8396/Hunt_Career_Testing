import { BasePage } from "./BasePage";

export class LoginPage extends BasePage {
  constructor(page) {
    super(page);
    this.emailField = page.locator("#email");
    this.passwordField = page.locator("#password");
    this.loginButton = page.getByRole("button", { name: "Login" });
    this.successMessage = page.getByText("Logged in successfully!");
    this.invalidCredentialsMessage = page.getByText(
      "Invalid email or password",
    );
    this.validEmailMsg = page.getByText("Please enter a valid email address.");
    this.passwordLengthMsg = page.getByText(
      "Password must be at least 8 characters long.",
    );
    this.emailRequiredMsg = page.getByText("Email is required.");
    this.passwordRequiredMsg = page.getByText("Password is required.");
  }

  async navigate() {
    await this.page.goto("/login");
  }

  async login(email, password) {
    await this.typeInElement(this.emailField, email);
    await this.typeInElement(this.passwordField, password);
    await this.clickElement(this.loginButton);
  }
}
