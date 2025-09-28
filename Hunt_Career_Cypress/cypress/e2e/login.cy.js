import LoginPage from "../pageObjects/LoginPage";
import loginData from "../fixtures/loginData.json";

describe("Job Seeker Login Tests", () => {
  let loginPage;

  beforeEach(() => {
    loginPage = new LoginPage();
    loginPage.navigate();
  });

  //Valid Users
  loginData.validUser.forEach((user) => {
    it(`Should login successfully with valid user: ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.waitUntilVisible(loginPage.checkLoginSuccessMessage());
      loginPage.waitUntilNotVisible(loginPage.checkLoginSuccessMessage());
    });
  });

  //Invalid Users (not registered)
  loginData.invalidUser.forEach((user) => {
    it(`Should show error for invalid user: ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.waitUntilVisible(loginPage.checkInvalidEmailAndPasswordMessage());
      loginPage.waitUntilNotVisible(loginPage.checkInvalidEmailAndPasswordMessage());
    });
  });

  //Invalid Emails
  loginData.invalidEmails.forEach((user) => {
    it(`Should show error for invalid email: ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.waitUntilVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
      loginPage.waitUntilNotVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
    });
  });

  //Invalid Passwords
  loginData.invalidPassowrd.forEach((user) => {
    it(`Should show error for invalid password: ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.waitUntilVisible(loginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
      loginPage.waitUntilNotVisible(loginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    });
  });

  //Blank Email
  loginData.NoEmail.forEach((user) => {
    it(`Should show error when email is blank: ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.waitUntilVisible(loginPage.checkEmailIsRequiredMessage());
      loginPage.waitUntilNotVisible(loginPage.checkEmailIsRequiredMessage());
    });
  });

  //Blank Password
  loginData.NoPassword.forEach((user) => {
    it(`Should show error when password is blank: ${user.description}`, () => {
      loginPage.login(user.email, user.password);
      loginPage.waitUntilVisible(loginPage.checkPasswordIsRequiredMessage());
      loginPage.waitUntilNotVisible(loginPage.checkPasswordIsRequiredMessage());
    });
  });
});