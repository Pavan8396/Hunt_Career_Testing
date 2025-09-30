import { test, expect } from "@playwright/test";
import loginData from "../data/loginData.json";
import { LoginPage } from "../pages/LoginPage.js";
import { SearchPage } from "../pages/SearchPage.js";

// const test = baseTest.extend({
//   loginPage: async ({ page }, use) => {
//     await use(new LoginPage(page));
//   },
//   homePage: async ({ page }, use) => {
//     await use(new HomePage(page));
//   },
// });

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
      await expect(searchPage.getLoginSuccessMessage).toBeVisible();
    })
  }
  for (const user of loginData.invalidUser) {
    test(`should show an error for ${user.description}`, async () => {
      await loginPage.login(user.email, user.password);
      await expect(loginPage.getInvalidEmailOrPasswordMessage).toBeVisible();
    });
  }
  for (const user of loginData.invalidEmails) {
    test(`should show an error for invalid email: ${user.description}`, async () => {
      await loginPage.login(user.email, user.password);
      await expect(loginPage.getInvalidEmailAddressMessage).toBeVisible();
    });
  }
  for (const user of loginData.invalidPassword) {
    test(`should show an error for ${user.description}`, async () => {
      await loginPage.login(user.email, user.password);
      await expect(loginPage.getPasswordLengthErrorMessage).toBeVisible();
    });
  }
  for (const user of loginData.blankEmail) {
    test("should show an error when email is blank", async () => {
      await loginPage.login(user.email, user.password);
      await expect(loginPage.getEmptyEmailMessage).toBeVisible();
    });
  }
  for (const user of loginData.blankPassword) {
    test("should show an error when password is blank", async () => {
      await loginPage.login(user.email, user.password);
      await expect(loginPage.getEmptyPasswordMessage).toBeVisible();
    });
  }
});