import LoginPage from "../pageObjects/LoginPage";
import loginData from "../fixtures/loginData.json";

describe("Job Seeker Login", () => {
  let loginPage;

  beforeEach(() => {
    loginPage = new LoginPage();
    loginPage.navigate();
  });

  loginData.validUser.forEach((user) => {
    it(`should log in successfully with ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.getLoginSuccessMessage().should("be.visible");
    });
  });

  loginData.invalidUser.forEach((user) => {
    it(`should show an error for ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.getInvalidEmailOrPasswordMessage().should("be.visible");
    });
  });

  loginData.invalidEmails.forEach((user) => {
    it(`should show an error for invalid email: ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.getInvalidEmailAddressMessage().should("be.visible");
    });
  });

  loginData.invalidPassword.forEach((user) => {
    it(`should show an error for ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.getPasswordLengthErrorMessage().should("be.visible");
    });
  });

  loginData.blankEmail.forEach((user) => {
    it("should show an error when email is blank", () => {
      loginPage.login(user.email, user.password);
      loginPage.getEmptyEmailMessage().should("be.visible");
    });
  });

  loginData.blankPassword.forEach((user) => {
    it("should show an error when password is blank", () => {
      loginPage.login(user.email, user.password);
      loginPage.getEmptyPasswordMessage().should("be.visible");
    });
  });
});