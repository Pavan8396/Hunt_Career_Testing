import { loginPage } from "../pageObjects/LoginPage";
import loginData from "../fixtures/loginData.json"

describe("Login Test", () =>{

    it("TC-1-Enter with valid credcredentials", () =>{
        cy.login(loginData.validUser.email, loginData.validUser.password)
        loginPage.validationMessage(loginPage.checkLoginSuccessMessage());
        loginPage.validationMessageNotExist(loginPage.checkLoginSuccessMessage());
    })

    it("TC-2-Enter User who is Not registerd", () =>{
        cy.login(loginData.invalidUser.email, loginData.invalidUser.password)
        loginPage.validationMessage(loginPage.checkInvalidEmailAndPasswordMessage());
        loginPage.validationMessageNotExist(loginPage.checkInvalidEmailAndPasswordMessage());
    })

    it("TC-3-Enter Invalid Email and Valid password", () =>{
        cy.login(loginData.invalidEmail.email, loginData.invalidEmail.password)
        loginPage.validationMessage(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.validationMessageNotExist(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-4-Enter Valid Email and Invalid Password", ()=>{
        cy.login(loginData.invalidPassowrd.email, loginData.invalidPassowrd.password)
        loginPage.validationMessage(loginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
        loginPage.validationMessageNotExist(loginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    })

    it("TC-5-Don't enter any data into Email field and click on Login Button", ()=>{
        cy.visit("/login")
        loginPage.clickLoginButton();
        loginPage.validationMessage(loginPage.checkEmailIsRequiredMessage());
        loginPage.validationMessageNotExist(loginPage.checkEmailIsRequiredMessage());
    })

    it("TC-6-Don't enter any data into Password field and click on Login Button", () =>{
        cy.login(loginData.blankPassword.email, loginData.blankPassword.password)
        loginPage.validationMessage(loginPage.checkPasswordIsRequiredMessage());
        loginPage.validationMessageNotExist(loginPage.checkPasswordIsRequiredMessage());
    })

    it("TC-7-Enter Email without the domain name and click on login button", ()=>{
        cy.login(loginData.EmailwithoutDomain.email, loginData.EmailwithoutDomain.password)
        loginPage.validationMessage(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.validationMessageNotExist(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-8-Enter email without username and click on login button", ()=>{
        cy.login(loginData.emailWihoutName.email, loginData.emailWihoutName.password)
        loginPage.validationMessage(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.validationMessageNotExist(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-9-Enter the Email with two at the rate characters and click on login button", ()=>{
        cy.login(loginData["emailhaving2@"].email, loginData["emailhaving2@"].password)
        loginPage.validationMessage(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.validationMessageNotExist(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-10-Enter email with dot at the end and click on login button", ()=>{
        cy.login(loginData.emailWithDotAfterName.email, loginData.emailWithDotAfterName.password)
        loginPage.validationMessage(loginPage.checkLoginSuccessMessage());
        loginPage.validationMessageNotExist(loginPage.checkLoginSuccessMessage());
    })

    it("TC-11-Enter Email with dot in the middle of name and click on Login button", ()=>{
        cy.login(loginData.emailWithDotInbetweenName.email, loginData.emailWithDotInbetweenName.password)
        loginPage.validationMessage(loginPage.checkLoginSuccessMessage());
        loginPage.validationMessageNotExist(loginPage.checkLoginSuccessMessage());
    })
})