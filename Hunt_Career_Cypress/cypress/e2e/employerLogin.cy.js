import LoginPage from "../pageObjects/LoginPage";
import eloginData from "../fixtures/employerLoginData.json"

describe("Employer Login Tests", () => {
  let eloginPage;

  beforeEach(() => {
    eloginPage = new LoginPage();
    eloginPage.employerNavigate();
  });

  //Valid Users
  eloginData.validUser.forEach((user) => {
    it(`Should login successfully with valid user: ${user.description}`, () => {
      eloginPage.Employerlogin(user.email, user.password);
      eloginPage.waitUntilVisible(eloginPage.checkLoginSuccessMessage());
      eloginPage.waitUntilNotVisible(eloginPage.checkLoginSuccessMessage());
    });
  });

  //Invalid Users (not registered)
  eloginData.invalidUser.forEach((user) => {
    it(`Should show error for invalid user: ${user.description}`, () => {
      eloginPage.Employerlogin(user.email, user.password);
      eloginPage.waitUntilVisible(eloginPage.checkInvalidEmailAndPasswordMessage());
      eloginPage.waitUntilNotVisible(eloginPage.checkInvalidEmailAndPasswordMessage());
    });
  });

  //Invalid Emails
  eloginData.invalidEmails.forEach((user) => {
    it(`Should show error for invalid email: ${user.description}`, () => {
      eloginPage.Employerlogin(user.email, user.password);
      eloginPage.waitUntilVisible(eloginPage.checkPleaseEnterValidEmailAddressMessage());
      eloginPage.waitUntilNotVisible(eloginPage.checkPleaseEnterValidEmailAddressMessage());
    });
  });

  //Invalid Passwords
  eloginData.invalidPassowrd.forEach((user) => {
    it(`Should show error for invalid password: ${user.description}`, () => {
      eloginPage.Employerlogin(user.email, user.password);
      eloginPage.waitUntilVisible(eloginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
      eloginPage.waitUntilNotVisible(eloginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    });
  });

  //Blank Email
  eloginData.NoEmail.forEach((user) => {
    it(`Should show error when email is blank: ${user.description}`, () => {
      eloginPage.Employerlogin(user.email, user.password);
      eloginPage.waitUntilVisible(eloginPage.checkEmailIsRequiredMessage());
      eloginPage.waitUntilNotVisible(eloginPage.checkEmailIsRequiredMessage());
    });
  });

  //Blank Password
  eloginData.NoPassword.forEach((user) => {
    it(`Should show error when password is blank: ${user.description}`, () => {
      eloginPage.Employerlogin(user.email, user.password);
      eloginPage.waitUntilVisible(eloginPage.checkPasswordIsRequiredMessage());
      eloginPage.waitUntilNotVisible(eloginPage.checkPasswordIsRequiredMessage());
    });
  });
});