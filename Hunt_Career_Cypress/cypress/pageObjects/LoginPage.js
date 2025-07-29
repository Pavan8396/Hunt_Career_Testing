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

    //Actions
    enterEmailField(email){
        this.typeInElement(this.EmailField(), email)
    }
    enterPasswordField(password){
        this.typeInElement(this.PasswordField(), password)
    }
    clickLoginButton(){
        this.clickElement(this.LoginButton());
    }

    login(email, password){
        this.enterEmailField(email);
        this.enterPasswordField(password);
        this.clickLoginButton();
    }
}