import { test, expect } from "@playwright/test";
import { LoginPage } from "../pages/LoginPage";
import { SearchPage } from "../pages/SearchPage";
import * as loginData from "../data/loginData.json";

test.describe("Job Seeker Login", () => {
    let loginPage;
    let searchPage;

    test.beforeEach(async ({ page }) => {
        loginPage = new LoginPage(page);
        searchPage = new SearchPage(page);
        await loginPage.navigate();
    });

    for (const user of loginData.validUser) {
        test(`should log in successfully with ${user.description}`, async () => {
            await loginPage.login(user.email, user.password);
            await expect(searchPage.loginSuccessMessage).toBeVisible();
        });
    }

    for (const user of loginData.invalidUser) {
        test(`should show an error for ${user.description}`, async () => {
            await loginPage.login(user.email, user.password);
            await expect(loginPage.invalidCredentialsMessage).toBeVisible();
        });
    }

    for (const user of loginData.invalidEmails) {
        test(`should show an error for invalid email: ${user.description}`, async () => {
            await loginPage.login(user.email, user.password);
            await expect(loginPage.validEmailMsg).toBeVisible();
        });
    }

    for (const user of loginData.invalidPassword) {
        test(`should show an error for ${user.description}`, async () => {
            await loginPage.login(user.email, user.password);
            await expect(loginPage.passwordLengthMsg).toBeVisible();
        });
    }

    for (const user of loginData.blankEmail) {
        test("should show an error when email is blank", async () => {
            await loginPage.login(user.email, user.password);
            await expect(loginPage.emailRequiredMsg).toBeVisible();
        });
    }

    for (const user of loginData.blankPassword) {
        test("should show an error when password is blank", async () => {
            await loginPage.login(user.email, user.password);
            await expect(loginPage.passwordRequiredMsg).toBeVisible();
        });
    }
});