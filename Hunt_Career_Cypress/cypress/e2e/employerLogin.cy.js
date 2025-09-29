import EmployerLoginPage from "../pageObjects/EmployerLoginPage";
import SearchPage from "../pageObjects/SearchPage";
import employerLoginData from "../fixtures/employerLoginData.json";

describe("Employer Login", () => {
    let employerLoginPage;

    beforeEach(() => {
        employerLoginPage = new EmployerLoginPage();
        employerLoginPage.navigate();
    });

    employerLoginData.validUsers.forEach((user) => {
        it(`should log in successfully with ${user.description}`, () => {
            employerLoginPage.login(user.email, user.password);
            const searchPage = new SearchPage();
            searchPage.getLoginSuccessMessage().should("be.visible");
        });
    });

    employerLoginData.invalidUsers.forEach((user) => {
        it(`should show an error for ${user.description}`, () => {
            employerLoginPage.login(user.email, user.password);
            employerLoginPage.getInvalidEmailOrPasswordMessage().should("be.visible");
        });
    });

    employerLoginData.invalidEmails.forEach((user) => {
        it(`should show an error for an invalid email: ${user.description}`, () => {
            employerLoginPage.login(user.email, user.password);
            employerLoginPage.getInvalidEmailAddressMessage().should("be.visible");
        });
    });

    employerLoginData.invalidPasswords.forEach((user) => {
        it(`should show an error for ${user.description}`, () => {
            employerLoginPage.login(user.email, user.password);
            employerLoginPage.getPasswordLengthErrorMessage().should("be.visible");
        });
    });

    employerLoginData.blankEmail.forEach((user) => {
        it("should show an error when the email is blank", () => {
            employerLoginPage.login(user.email, user.password);
            employerLoginPage.getEmptyEmailMessage().should("be.visible");
        });
    });

    employerLoginData.blankPassword.forEach((user) => {
        it("should show an error when the password is blank", () => {
            employerLoginPage.login(user.email, user.password);
            employerLoginPage.getEmptyPasswordMessage().should("be.visible");
        });
    });
});