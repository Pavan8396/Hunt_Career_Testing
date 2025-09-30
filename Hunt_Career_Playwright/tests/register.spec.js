import { test, expect } from "@playwright/test";
import { registerData } from "../data/registerData.js";
import { RegisterPage } from "../pages/RegisterPage.js";
import { LoginPage } from "../pages/LoginPage.js";

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
      await expect(loginPage.getSuccessCreationMessage).toBeVisible();
    });
  }
  test("should show an error for duplicate credentials", async () => {
    const user = registerData.duplicateUser;
    await registerPage.register(user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
    await expect(registerPage[user.expectedError]).toBeVisible();
  });
  for (const user of registerData.invalidCases) {
    test(`should show an error for invalid input: ${user.description}`, async () => {
      //const regPage = new RegisterPage(page);
      //await regPage.navigate();
      await registerPage[user.testRunner](user.firstName, user.lastName, user.email, user.password, user.confirmPassword, user.phoneNumber);
      await expect(registerPage[user.expectedError]).toBeVisible();
    });
  }
});