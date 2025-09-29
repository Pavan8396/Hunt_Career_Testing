import RegisterPage from "../pageObjects/RegisterPage";
import { registerData } from "../fixtures/registerData";

describe("User Registration", () => {
    let registerPage;

    beforeEach(() => {
        registerPage = new RegisterPage();
        registerPage.navigate();
    });

    registerData.validUsers.forEach((user) => {
        it(`should register successfully with ${user.description}`, () => {
            registerPage.register(user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
            registerPage.getSuccessCreationMessage().should("be.visible");
        });
    });

    it("should show an error for duplicate credentials", () => {
        const user = registerData.duplicateUser;
        registerPage.register(user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
        registerPage.getDuplicateErrorMessage().should("be.visible");
    });

    registerData.invalidCases.forEach((user) => {
        it(`should show an error for invalid input: ${user.description}`, () => {
            registerPage[user.testRunner](user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
            registerPage[user.expectedError]().should("be.visible");
        });
    });
});