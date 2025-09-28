import RegisterPage from "../pageObjects/RegisterPage";
import { registerData } from "../fixtures/employerRegisterData"

describe("Employer Login Tests", () => {
  let eregisterPage;

  beforeEach(() => {
    eregisterPage = new RegisterPage();
    eregisterPage.EmployerNavigate();
  });

  //Valid Users
  registerData.validUser.forEach((user) => {
    it(`Should register successfully with valid user: ${user.description}`, () => {
      eregisterPage.employerRegister(user.name, user.email, user.password, user.confirmPassword);
      eregisterPage.waitUntilVisible(eregisterPage.checkSuccessCreationMessage());
      eregisterPage.waitUntilNotVisible(eregisterPage.checkSuccessCreationMessage());
    });
  });
});