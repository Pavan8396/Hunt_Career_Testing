import LoginPage from "../pageObjects/LoginPage";
import loginData from "../fixtures/loginData.json"

describe("Login Test", () =>{

    let loginPage;

    beforeEach(() => {
        loginPage = new LoginPage();
    });

    it("TC-1-Enter with valid credcredentials", () =>{
        cy.visit('/login');
        loginPage.login(loginData.validUser.email, loginData.validUser.password);
        loginPage.waitUntilVisible(loginPage.checkLoginSuccessMessage());
        loginPage.waitUntilNotVisible(loginPage.checkLoginSuccessMessage());
    })

    it("TC-2-Enter User who is Not registerd", () =>{
        cy.visit('/login');
        loginPage.login(loginData.invalidUser.email, loginData.invalidUser.password);
        loginPage.waitUntilVisible(loginPage.checkInvalidEmailAndPasswordMessage());
        loginPage.waitUntilNotVisible(loginPage.checkInvalidEmailAndPasswordMessage());
    })

    it("TC-3-Enter Invalid Email and Valid password", () =>{
        cy.visit('/login');
        loginPage.login(loginData.invalidEmail.email, loginData.invalidEmail.password);
        loginPage.waitUntilVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.waitUntilNotVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-4-Enter Valid Email and Invalid Password", ()=>{
        cy.visit('/login');
        loginPage.login(loginData.invalidPassowrd.email, loginData.invalidPassowrd.password);
        loginPage.waitUntilVisible(loginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
        loginPage.waitUntilNotVisible(loginPage.checkPasswordMustHaveRequiredNumberCharactersMessage());
    })

    it("TC-5-Don't enter any data into Email field and click on Login Button", ()=>{
        cy.visit("/login");
        loginPage.clickLoginButton();
        loginPage.waitUntilVisible(loginPage.checkEmailIsRequiredMessage());
        loginPage.waitUntilNotVisible(loginPage.checkEmailIsRequiredMessage());
    })

    it("TC-6-Don't enter any data into Password field and click on Login Button", () =>{
        cy.visit('/login');
        loginPage.login(loginData.blankPassword.email, loginData.blankPassword.password);
        loginPage.waitUntilVisible(loginPage.checkPasswordIsRequiredMessage());
        loginPage.waitUntilNotVisible(loginPage.checkPasswordIsRequiredMessage());
    })

    it("TC-7-Enter Email without the domain name and click on login button", ()=>{
        cy.visit('/login');
        loginPage.login(loginData.EmailwithoutDomain.email, loginData.EmailwithoutDomain.password);
        loginPage.waitUntilVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.waitUntilNotVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-8-Enter email without username and click on login button", ()=>{
        cy.visit('/login');
        loginPage.login(loginData.emailWihoutName.email, loginData.emailWihoutName.password);
        loginPage.waitUntilVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.waitUntilNotVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-9-Enter the Email with two at the rate characters and click on login button", ()=>{
        cy.visit('/login');
        loginPage.login(loginData["emailhaving2@"].email, loginData["emailhaving2@"].password);
        loginPage.waitUntilVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
        loginPage.waitUntilNotVisible(loginPage.checkPleaseEnterValidEmailAddressMessage());
    })

    it("TC-10-Enter email with dot at the end and click on login button", ()=>{
        cy.visit('/login');
        loginPage.login(loginData.emailWithDotAfterName.email, loginData.emailWithDotAfterName.password);
        loginPage.waitUntilVisible(loginPage.checkLoginSuccessMessage());
        loginPage.waitUntilNotVisible(loginPage.checkLoginSuccessMessage());
    })

    it("TC-11-Enter Email with dot in the middle of name and click on Login button", ()=>{
        cy.visit('/login');
        loginPage.login(loginData.emailWithDotInbetweenName.email, loginData.emailWithDotInbetweenName.password);
        loginPage.waitUntilVisible(loginPage.checkLoginSuccessMessage());
        loginPage.waitUntilNotVisible(loginPage.checkLoginSuccessMessage());
    })
})