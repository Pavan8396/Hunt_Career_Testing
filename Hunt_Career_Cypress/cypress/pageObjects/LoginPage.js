import { BasePage } from "./basePage";

export default class LoginPage extends BasePage{
    EmailField(){
        return cy.xpath("//input[@id='email']");
    }
    PasswordField(){
        return cy.xpath("//input[@id='password']");
    }
    LoginButton(){
        return cy.xpath("//button[@aria-label='Login']");
    }
    EmployerLoginButton(){
        return cy.xpath("//button[@type='submit']");
    }
    checkLoginSuccessMessage(){
        return cy.xpath("//div[contains(text(), 'Logged in successfully!')]");
    }
    checkInvalidEmailAndPasswordMessage(){
        return cy.xpath("//div[contains(text(), 'Invalid email or password')]");
    }
    checkPleaseEnterValidEmailAddressMessage(){
        return cy.xpath("//div[contains(text(), 'Please enter a valid email address.')]")
    }
    checkPasswordMustHaveRequiredNumberCharactersMessage(){
        return cy.xpath("//div[contains(text(), 'Password must be at least 8 characters long.')]")
    }
    checkEmailIsRequiredMessage(){
        return cy.xpath("//div[contains(text(), 'Email is required.')]")
    }
    checkPasswordIsRequiredMessage(){
        return cy.xpath("//div[contains(text(), 'Password is required.')]")
    }
    loginHomeLink(){
        return cy.xpath("//a[contains(text(), 'Login')]")
    }

    //Actions
    enterEmailField(email, options = {}){
        this.typeInElement(this.EmailField(), email, options)
    }
    enterPasswordField(password, options = {}){
        this.typeInElement(this.PasswordField(), password, options)
    }
    clickLoginButton(){
        this.clickElement(this.LoginButton());
    }
    clickEmployerLoginButton(){
        this.clickElement(this.EmployerLoginButton());
    }

    navigate(){
        cy.visit("/");
        this.navigateToJobSeeker();
        this.clickElement(this.loginHomeLink());
    }

    login(email, password){
        this.enterEmailField(email, { parseSpecialCharSequences: false });
        this.enterPasswordField(password, { parseSpecialCharSequences: false });
        this.clickLoginButton();
    }

    employerNavigate(){
        cy.visit("/");
        this.navigateToEmployer();
    }

    Employerlogin(email, password){
        this.enterEmailField(email);
        this.enterPasswordField(password);
        this.clickEmployerLoginButton();
    }
}