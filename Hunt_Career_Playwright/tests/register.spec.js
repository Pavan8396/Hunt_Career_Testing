import { test, expect } from "@playwright/test";
import { RegisterPage } from "../pages/RegisterPage";
import { LoginPage } from "../pages/LoginPage";
import { registerData } from "../data/registerData";

test.describe("User Registration", () => {
    let registerPage;
    let loginPage;

    test.beforeEach(async ({ page }) => {
        registerPage = new RegisterPage(page);
        loginPage = new LoginPage(page);
        await registerPage.navigate();
    });

    for (const user of registerData.validUsers) {
        test(`should register successfully with ${user.description}`, async () => {
            await registerPage.register(user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
            await expect(loginPage.successCreationMessage).toBeVisible();
        });
    }

    test("should show an error for duplicate credentials", async () => {
        const user = registerData.duplicateUser;
        await registerPage.register(user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
        await expect(registerPage.duplicateErrorMessage).toBeVisible();
    });

    for (const user of registerData.invalidCases) {
        test(`should show an error for invalid input: ${user.description}`, async ({ page }) => {
            const regPage = new RegisterPage(page);
            await regPage.navigate();
            await regPage[user.testRunner](user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
            await expect(regPage[user.expectedError]).toBeVisible();
        });
    }
});