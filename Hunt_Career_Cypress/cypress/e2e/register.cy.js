import RegisterPage from "../pageObjects/RegisterPage";
import { registerData } from "../fixtures/registerData";

describe("Register Test", () =>{
 
    let registerPage;

    beforeEach(() => {
        registerPage = new RegisterPage();
        registerPage.navigate();
    });

    it("TC-1-Enter with valid cred", () =>{
        registerPage.register(registerData.validUser.firstName, registerData.validUser.lastName, registerData.validUser.email, registerData.validUser.password, registerData.validUser.confirmPassword, registerData.validUser.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkSuccessCreationMessage());
        registerPage.waitUntilNotVisible(registerPage.checkSuccessCreationMessage());
    })

    it("TC-2-Enter with duplicate credentials", () =>{
        registerPage.register(registerData.duplicateUser.firstName, registerData.duplicateUser.lastName, registerData.duplicateUser.email, registerData.duplicateUser.password, registerData.duplicateUser.confirmPassword, registerData.duplicateUser.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkDuplicateErrorMessage());
        registerPage.waitUntilNotVisible(registerPage.checkDuplicateErrorMessage());
    })

    it("TC-3-Enter without FirstName", () =>{
        registerPage.registerFirstPage(registerData.noFirstName.firstName, registerData.noFirstName.lastName);
        registerPage.waitUntilVisible(registerPage.checkFirstNameBlankMessage());
        registerPage.waitUntilNotVisible(registerPage.checkFirstNameBlankMessage());
    })

    it("TC-4-Enter without LastName", () =>{
        registerPage.registerFirstPage(registerData.noLastName.firstName, registerData.noLastName.lastName);
        registerPage.waitUntilVisible(registerPage.checkLastNameBlankMessage());
        registerPage.waitUntilNotVisible(registerPage.checkLastNameBlankMessage());
    })

    it("TC-5-Enter without Email", () =>{
        registerPage.registerSecondPage(registerData.noEmailName.firstName, registerData.noEmailName.lastName, registerData.noEmailName.email, registerData.noEmailName.password, registerData.noEmailName.confirmPassword);
        registerPage.waitUntilVisible(registerPage.checkEmailBlankMessage());
        registerPage.waitUntilNotVisible(registerPage.checkEmailBlankMessage());
    })

    it("TC-6-Enter without Password", () =>{
        registerPage.registerSecondPage(registerData.noPasswordName.firstName, registerData.noPasswordName.lastName, registerData.noPasswordName.email, registerData.noPasswordName.password, registerData.noPasswordName.confirmPassword);
        registerPage.waitUntilVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    })

    it("TC-7-Enter Password with less characters", () =>{
        registerPage.registerSecondPage(registerData.passwordWithLessCharacters.firstName, registerData.passwordWithLessCharacters.lastName, registerData.passwordWithLessCharacters.email, registerData.passwordWithLessCharacters.password, registerData.passwordWithLessCharacters.confirmPassword);
        registerPage.waitUntilVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    })

    it("TC-8-Enter without ConfirmPassword", () =>{
        registerPage.registerSecondPage(registerData.noConfirmPasswordName.firstName, registerData.noConfirmPasswordName.lastName, registerData.noConfirmPasswordName.email, registerData.noConfirmPasswordName.password, registerData.noConfirmPasswordName.confirmPassword, registerData.noConfirmPasswordName.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkPasswordNotMatchMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPasswordNotMatchMessage());
    })

    it("TC-9-Enter without PhoneNumber", () =>{
        registerPage.register(registerData.noPhoneNumberName.firstName, registerData.noPhoneNumberName.lastName, registerData.noPhoneNumberName.email, registerData.noPhoneNumberName.password, registerData.noPhoneNumberName.confirmPassword, registerData.noPhoneNumberName.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkPhoneNumberRequiredMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPhoneNumberRequiredMessage());
    })

    it("TC-10-Enter invalid PhoneNumber", () =>{
        registerPage.register(registerData.invalidPhoneNumber.firstName, registerData.invalidPhoneNumber.lastName, registerData.invalidPhoneNumber.email, registerData.invalidPhoneNumber.password, registerData.invalidPhoneNumber.confirmPassword, registerData.invalidPhoneNumber.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkPhoneNumberInvalidMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPhoneNumberInvalidMessage());
    })


    it("TC-11-Enter email without All Domain", () =>{
        registerPage.registerSecondPage(registerData.emailWithoutAllDomain.firstName, registerData.emailWithoutAllDomain.lastName, registerData.emailWithoutAllDomain.email, registerData.emailWithoutAllDomain.password, registerData.emailWithoutAllDomain.confirmPassword, registerData.emailWithoutAllDomain.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-12-Enter email without Top Level Domain", () =>{
        registerPage.registerSecondPage(registerData.emailWithoutTopLevelDomain.firstName, registerData.emailWithoutTopLevelDomain.lastName, registerData.emailWithoutTopLevelDomain.email, registerData.emailWithoutTopLevelDomain.password, registerData.emailWithoutTopLevelDomain.confirmPassword, registerData.emailWithoutTopLevelDomain.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-13-Enter email with double at", () =>{
        registerPage.registerSecondPage(registerData.emailDoubleAt.firstName, registerData.emailDoubleAt.lastName, registerData.emailDoubleAt.email, registerData.emailDoubleAt.password, registerData.emailDoubleAt.confirmPassword, registerData.emailDoubleAt.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it.only("TC-14-Enter email with dot before at", () =>{
        registerPage.register(registerData.emailDotBeforeAt.firstName, registerData.emailDotBeforeAt.lastName, registerData.emailDotBeforeAt.email, registerData.emailDotBeforeAt.password, registerData.emailDotBeforeAt.confirmPassword, registerData.emailDotBeforeAt.phoneNumber);
        registerPage.waitUntilVisible(registerPage.checkSuccessCreationMessage());
        registerPage.waitUntilNotVisible(registerPage.checkSuccessCreationMessage());
    })

    it.only("TC-15-Enter email without Domain", () =>{
        registerPage.registerSecondPage(registerData.emailWithoutDomain.firstName, registerData.emailWithoutDomain.lastName, registerData.emailWithoutDomain.email, registerData.emailWithoutDomain.password, registerData.emailWithoutDomain.confirmPassword);
        registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it.only("TC-16-Enter email without username", () =>{
        registerPage.registerSecondPage(registerData.emailMissingUsername.firstName, registerData.emailMissingUsername.lastName, registerData.emailMissingUsername.email, registerData.emailMissingUsername.password, registerData.emailMissingUsername.confirmPassword);
        registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })
})