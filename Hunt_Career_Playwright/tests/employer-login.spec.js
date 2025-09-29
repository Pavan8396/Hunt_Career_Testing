import { test, expect } from "@playwright/test";
import { EmployerLoginPage } from "../pages/EmployerLoginPage";
import { SearchPage } from "../pages/SearchPage";
import * as employerLoginData from "../data/employerLoginData.json";

test.describe("Employer Login", () => {
  let employerLoginPage;
  let searchPage;

  test.beforeEach(async ({ page }) => {
    employerLoginPage = new EmployerLoginPage(page);
    searchPage = new SearchPage(page);
    await employerLoginPage.navigate();
  });

  for (const user of employerLoginData.validUsers) {
    test(`should log in successfully with ${user.description}`, async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(searchPage.loginSuccessMessage).toBeVisible();
    });
  }

  for (const user of employerLoginData.invalidUsers) {
    test(`should show an error for ${user.description}`, async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(employerLoginPage.invalidCredentialsMessage).toBeVisible();
    });
  }

    for (const user of employerLoginData.invalidEmails) {
    test(`should show an error for an invalid email: ${user.description}`, async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(employerLoginPage.validEmailMsg).toBeVisible();
    });
  }

  for (const user of employerLoginData.invalidPasswords) {
    test(`should show an error for ${user.description}`, async () => {
        await employerLoginPage.login(user.email, user.password);
        await expect(employerLoginPage.passwordLengthMsg).toBeVisible();
    });
    }

    for (const user of employerLoginData.blankEmail) {
        test("should show an error when the email is blank", async () => {
            await employerLoginPage.login(user.email, user.password);
            await expect(employerLoginPage.emailRequiredMsg).toBeVisible();
        });
    }

    for (const user of employerLoginData.blankPassword) {
        test("should show an error when the password is blank", async () => {
            await employerLoginPage.login(user.email, user.password);
            await expect(employerLoginPage.passwordRequiredMsg).toBeVisible();
        });
    }
});