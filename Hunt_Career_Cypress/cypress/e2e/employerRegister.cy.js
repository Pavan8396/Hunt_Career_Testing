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

  it("TC-2-Enter with duplicate credentials", () =>{
          eregisterPage.employerRegister(registerData.duplicateUser.name, registerData.duplicateUser.email, registerData.duplicateUser.password, registerData.duplicateUser.confirmPassword);
          eregisterPage.waitUntilVisible(eregisterPage.checkDuplicateErrorMessageForEmployer());
          eregisterPage.waitUntilNotVisible(eregisterPage.checkDuplicateErrorMessageForEmployer());
      })

  registerData.invalidCases.forEach((user)=>{
    it(`Should show error for invalid: ${user.description}`, () => {
        eregisterPage.employerRegister(user.name, user.email, user.password, user.confirmPassword)
        eregisterPage.waitUntilVisible(eregisterPage[user.expectedError]());
        eregisterPage.waitUntilNotVisible(eregisterPage[user.expectedError]());  
        }) 
      })
  });