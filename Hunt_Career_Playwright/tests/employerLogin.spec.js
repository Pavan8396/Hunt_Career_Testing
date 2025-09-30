import { test, expect } from "@playwright/test";
import employerLoginData from "../data/employerLoginData.json";
import { EmployerLoginPage } from "../pages/EmployerLoginPage.js";
import { SearchPage } from "../pages/SearchPage.js"

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
    })
  }
  for (const user of employerLoginData.invalidUsers) {
    test(`should show an error for ${user.description}`, async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(employerLoginPage.getInvalidEmailOrPasswordMessage).toBeVisible();
    });
  }
  for (const user of employerLoginData.invalidEmails) {
    test(`should show an error for invalid email: ${user.description}`, async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(employerLoginPage.getInvalidEmailAddressMessage).toBeVisible();
    });
  }
  for (const user of employerLoginData.invalidPasswords) {
    test(`should show an error for ${user.description}`, async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(employerLoginPage.getPasswordLengthErrorMessage).toBeVisible();
    });
  }
  for (const user of employerLoginData.blankEmail) {
    test("should show an error when email is blank", async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(employerLoginPage.getEmptyEmailMessage).toBeVisible();
    });
  }
  for (const user of employerLoginData.blankPassword) {
    test("should show an error when password is blank", async () => {
      await employerLoginPage.login(user.email, user.password);
      await expect(employerLoginPage.getEmptyPasswordMessage).toBeVisible();
    });
  }
});