import RegisterPage from "../pageObjects/RegisterPage";
import { registerData } from "../fixtures/registerData";

describe("Job Seeker Register Test", () =>{
 
    let registerPage;

    beforeEach(() => {
        registerPage = new RegisterPage();
        registerPage.navigate();
    });

    registerData.validUser.forEach((user)=>{
    it(`Should show sucess message: ${user.description}`, () =>{
        registerPage.register(user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkSuccessCreationMessage());
        registerPage.waitUntilNotVisible(registerPage.checkSuccessCreationMessage());
    })
})

    it("TC-2-Enter with duplicate credentials", () =>{
        registerPage.register(registerData.duplicateUser.firstName, registerData.duplicateUser.lastName, registerData.duplicateUser.email, registerData.duplicateUser.password, registerData.duplicateUser.confirmPassword, registerData.duplicateUser.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkDuplicateErrorMessage());
        registerPage.waitUntilNotVisible(registerPage.checkDuplicateErrorMessage());
    })

    registerData.invalidCases.forEach((user)=>{
        it(`Should show error for invalid: ${user.description}`, () => {
          registerPage[user.testRunner](user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber)
          registerPage.waitUntilVisible(registerPage[user.expectedError]());
          registerPage.waitUntilNotVisible(registerPage[user.expectedError]());  
        }) 
    })
})