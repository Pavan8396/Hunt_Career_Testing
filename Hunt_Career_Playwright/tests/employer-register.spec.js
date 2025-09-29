import { test, expect } from "@playwright/test";
import { EmployerRegisterPage } from "../pages/EmployerRegisterPage";
import { EmployerLoginPage } from "../pages/EmployerLoginPage";
import { employerRegisterData } from "../data/employerRegisterData";

test.describe("Employer Registration", () => {
    let employerRegisterPage;
    let employerLoginPage;

    test.beforeEach(async ({ page }) => {
        employerRegisterPage = new EmployerRegisterPage(page);
        employerLoginPage = new EmployerLoginPage(page);
        await employerRegisterPage.navigate();
    });

    for (const user of employerRegisterData.validUsers) {
        test(`should register successfully with ${user.description}`, async () => {
            await employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
            await expect(employerLoginPage.successCreationMessage).toBeVisible();
        });
    }

    test("should show an error for duplicate credentials", async () => {
        const user = employerRegisterData.duplicateUser;
        await employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
        await expect(employerRegisterPage.duplicateErrorMessage).toBeVisible();
    });

    for (const user of employerRegisterData.invalidCases) {
        test(`should show an error for invalid input: ${user.description}`, async ({ page }) => {
            const employerRegisterPage = new EmployerRegisterPage(page);
            await employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
            await expect(employerRegisterPage[user.expectedError]).toBeVisible();
        });
    }
});