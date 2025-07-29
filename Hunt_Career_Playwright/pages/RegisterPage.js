import { BasePage } from "./BasePage";

export class RegisterPage extends BasePage {
    constructor(page) {
        super(page);
        this.firstNameField = page.locator("//input[@id='firstName']");
        this.lastNameField = page.locator("//input[@id='lastName']");
        this.emailField = page.locator("//input[@id='email']");
        this.passwordField = page.locator("//input[@id='password']");
        this.confirmPasswordField = page.locator("//input[@id='confirmPassword']");
        this.phoneNumberField = page.locator("//input[@id='phoneNumber']");
        this.firstNextButton = page.locator("//button[@type='button']");
        this.secondNextButton = page.locator("//button[normalize-space()='Next']");
        this.signupButton = page.locator("//button[@aria-label='Sign up']");
        this.checkSuccessCreationMessage = page.locator("//div[contains(text(), 'Registered successfully! Please login.')]");
        this.checkDuplicateErrorMessage = page.locator("//div[contains(text(),'User already exists')]");
        this.checkFirstNameBlankMessage = page.locator("//div[contains(text(),'First Name is required.')]");
        this.checkLastNameBlankMessage = page.locator("//div[contains(text(),'Last Name is required.')]");
        this.checkPleaseEnterValidEmailAddressMessage = page.locator("//div[contains(text(), 'Please enter a valid email address.')]");
        this.checkEmailBlankMessage = page.locator("//div[contains(text(), 'Email is required.')]");
        this.checkPasswordMustHaveRequiredNumberCharactersMessage = page.locator("//div[contains(text(), 'Password must be at least 8 characters long.')]");
        this.checkPasswordNotMatchMessage = page.locator("//div[contains(text(), 'Passwords do not match.')]");
        this.checkPhoneNumberRequiredMessage = page.locator("//div[contains(text(), 'Phone Number is required.')]");
    }

    async navigate() {
        await this.navigateToJobSeeker();
        await this.page.goto('/signup');
    }

    async register(firstName, lastName, email, password, confirmPassword, phoneNumber){
        await this.typeInElement(this.firstNameField, firstName);
        await this.typeInElement(this.lastNameField, lastName);
        await this.clickElement(this.firstNextButton);
        await this.typeInElement(this.emailField, email);
        await this.typeInElement(this.passwordField, password);
        await this.typeInElement(this.confirmPasswordField, confirmPassword);
        await this.clickElement(this.secondNextButton);
        await this.typeInElement(this.phoneNumberField, phoneNumber);
        await this.clickElement(this.signupButton);
    }

    async registerFirstPage(firstName, lastName){
        await this.typeInElement(this.firstNameField, firstName);
        await this.typeInElement(this.lastNameField, lastName);
        await this.clickElement(this.firstNextButton);
    }

    async registerSecondPage(firstName, lastName, email, password, confirmPassword){
        await this.typeInElement(this.firstNameField, firstName);
        await this.typeInElement(this.lastNameField, lastName);
        await this.clickElement(this.firstNextButton);
        await this.typeInElement(this.emailField, email);
        await this.typeInElement(this.passwordField, password);
        await this.typeInElement(this.confirmPasswordField, confirmPassword);
        await this.clickElement(this.secondNextButton);
    }
}