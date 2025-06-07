import { registerPage } from "../pageObjects/RegisterPage";
import { registerData } from "../fixtures/registerData";

describe("Register Test", () =>{
 
    it("TC-1-Enter with valid cred", () =>{
        cy.registerFull(registerData.validUser.firstName, registerData.validUser.lastName, registerData.validUser.email, registerData.validUser.password, registerData.validUser.confirmPassword, registerData.validUser.phoneNumber)
        registerPage.validationMessage(registerPage.checkSuccessCreationMessage());
        registerPage.validationMessageNotExist(registerPage.checkSuccessCreationMessage());
    })

    it("TC-2-Enter with duplicate credentials", () =>{
        cy.registerFull(registerData.duplicateUser.firstName, registerData.duplicateUser.lastName, registerData.duplicateUser.email, registerData.duplicateUser.password, registerData.duplicateUser.confirmPassword, registerData.duplicateUser.phoneNumber)
        registerPage.validationMessage(registerPage.checkDuplicateErrorMessage());
        registerPage.validationMessageNotExist(registerPage.checkDuplicateErrorMessage());
    })

    it("TC-3-Enter without FirstName", () =>{
        cy.registerFirstPage(registerData.noFirstName.firstName, registerData.noFirstName.lastName)
        registerPage.validationMessage(registerPage.checkFirstNameBlankMessage());
        registerPage.validationMessageNotExist(registerPage.checkFirstNameBlankMessage());
    })

    it("TC-4-Enter without LastName", () =>{
        cy.registerFirstPage(registerData.noLastName.firstName, registerData.noLastName.lastName)
        registerPage.validationMessage(registerPage.checkLastNameBlankMessage());
        registerPage.validationMessageNotExist(registerPage.checkLastNameBlankMessage());
    })

    it("TC-5-Enter without Email", () =>{
        cy.registerSecondPage(registerData.noEmailName.firstName, registerData.noEmailName.lastName, registerData.noEmailName.email, registerData.noEmailName.password, registerData.noEmailName.confirmPassword)
        registerPage.validationMessage(registerPage.checkEmailBlankMessage());
        registerPage.validationMessageNotExist(registerPage.checkEmailBlankMessage());
    })

    it("TC-6-Enter without Password", () =>{
        cy.registerSecondPage(registerData.noPasswordName.firstName, registerData.noPasswordName.lastName, registerData.noPasswordName.email, registerData.noPasswordName.password, registerData.noPasswordName.confirmPassword)
        registerPage.validationMessage(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
        registerPage.validationMessageNotExist(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    })

    it("TC-7-Enter Password with less characters", () =>{
        cy.registerSecondPage(registerData.passwordWithLessCharacters.firstName, registerData.passwordWithLessCharacters.lastName, registerData.passwordWithLessCharacters.email, registerData.passwordWithLessCharacters.password, registerData.passwordWithLessCharacters.confirmPassword)
        registerPage.validationMessage(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
        registerPage.validationMessageNotExist(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    })

    it("TC-8-Enter without ConfirmPassword", () =>{
        cy.registerSecondPage(registerData.noConfirmPasswordName.firstName, registerData.noConfirmPasswordName.lastName, registerData.noConfirmPasswordName.email, registerData.noConfirmPasswordName.password, registerData.noConfirmPasswordName.confirmPassword, registerData.noConfirmPasswordName.phoneNumber)
        registerPage.validationMessage(registerPage.checkPasswordNotMatchMessage());
        registerPage.validationMessageNotExist(registerPage.checkPasswordNotMatchMessage());
    })

    it("TC-9-Enter without PhoneNumber", () =>{
        cy.registerFull(registerData.noPhoneNumberName.firstName, registerData.noPhoneNumberName.lastName, registerData.noPhoneNumberName.email, registerData.noPhoneNumberName.password, registerData.noPhoneNumberName.confirmPassword, registerData.noPhoneNumberName.phoneNumber)
        registerPage.validationMessage(registerPage.checkPhoneNumberRequiredMessage());
        registerPage.validationMessageNotExist(registerPage.checkPhoneNumberRequiredMessage());
    })

    it("TC-10-Enter invalid PhoneNumber", () =>{
        cy.registerFull(registerData.invalidPhoneNumber.firstName, registerData.invalidPhoneNumber.lastName, registerData.invalidPhoneNumber.email, registerData.invalidPhoneNumber.password, registerData.invalidPhoneNumber.confirmPassword, registerData.invalidPhoneNumber.phoneNumber)
        registerPage.validationMessage(registerPage.checkPhoneNumberInvalidMessage());
        registerPage.validationMessageNotExist(registerPage.checkPhoneNumberInvalidMessage());
    })


    it("TC-11-Enter email without All Domain", () =>{
        cy.registerSecondPage(registerData.emailWithoutAllDomain.firstName, registerData.emailWithoutAllDomain.lastName, registerData.emailWithoutAllDomain.email, registerData.emailWithoutAllDomain.password, registerData.emailWithoutAllDomain.confirmPassword, registerData.emailWithoutAllDomain.phoneNumber)
        registerPage.validationMessage(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.validationMessageNotExist(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-12-Enter email without Top Level Domain", () =>{
        cy.registerSecondPage(registerData.emailWithoutTopLevelDomain.firstName, registerData.emailWithoutTopLevelDomain.lastName, registerData.emailWithoutTopLevelDomain.email, registerData.emailWithoutTopLevelDomain.password, registerData.emailWithoutTopLevelDomain.confirmPassword, registerData.emailWithoutTopLevelDomain.phoneNumber)
        registerPage.validationMessage(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.validationMessageNotExist(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-13-Enter email with double at", () =>{
        cy.registerSecondPage(registerData.emailDoubleAt.firstName, registerData.emailDoubleAt.lastName, registerData.emailDoubleAt.email, registerData.emailDoubleAt.password, registerData.emailDoubleAt.confirmPassword, registerData.emailDoubleAt.phoneNumber)
        registerPage.validationMessage(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.validationMessageNotExist(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-14-Enter email with dot before at", () =>{
        cy.registerFull(registerData.emailDotBeforeAt.firstName, registerData.emailDotBeforeAt.lastName, registerData.emailDotBeforeAt.email, registerData.emailDotBeforeAt.password, registerData.emailDotBeforeAt.confirmPassword, registerData.emailDotBeforeAt.phoneNumber)
        registerPage.validationMessage(registerPage.checkSuccessCreationMessage());
        registerPage.validationMessageNotExist(registerPage.checkSuccessCreationMessage());
    })

    it("TC-15-Enter email without Domain", () =>{
        cy.registerSecondPage(registerData.emailWithoutDomain.firstName, registerData.emailWithoutDomain.lastName, registerData.emailWithoutDomain.email, registerData.emailWithoutDomain.password, registerData.emailWithoutDomain.confirmPassword, registerData.emailWithoutDomain.phoneNumber)
        registerPage.validationMessage(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.validationMessageNotExist(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-16-Enter email without username", () =>{
        cy.registerSecondPage(registerData.emailMissingUsername.firstName, registerData.emailMissingUsername.lastName, registerData.emailMissingUsername.email, registerData.emailMissingUsername.password, registerData.emailMissingUsername.confirmPassword, registerData.emailMissingUsername.phoneNumber)
        registerPage.validationMessage(registerPage.checkPleaseEnterValidEmailAddressMessage());
        registerPage.validationMessageNotExist(registerPage.checkPleaseEnterValidEmailAddressMessage());
    })
})