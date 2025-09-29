import EmployerRegisterPage from "../pageObjects/EmployerRegisterPage";
import { employerRegisterData } from "../fixtures/employerRegisterData";

describe("Employer Registration", () => {
    let employerRegisterPage;

    beforeEach(() => {
        employerRegisterPage = new EmployerRegisterPage();
        employerRegisterPage.navigate();
    });

    employerRegisterData.validUsers.forEach((user) => {
        it(`should register successfully with ${user.description}`, () => {
            employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
            employerRegisterPage.getSuccessCreationMessage().should("be.visible");
        });
    });

    it("should show an error for duplicate credentials", () => {
        const user = employerRegisterData.duplicateUser;
        employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
        employerRegisterPage.getDuplicateErrorMessage().should("be.visible");
    });

    employerRegisterData.invalidCases.forEach((user) => {
        it(`should show an error for invalid input: ${user.description}`, () => {
            employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
            employerRegisterPage[user.expectedError]().should("be.visible");
        });
    });
});