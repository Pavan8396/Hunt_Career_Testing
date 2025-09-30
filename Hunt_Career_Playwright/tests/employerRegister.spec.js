import { test, expect } from "@playwright/test";
import { employerRegisterData } from "../data/employerRegisterData.js";
import { EmployerRegisterPage } from "../pages/EmployerRegisterPage.js";
import { EmployerLoginPage } from "../pages/EmployerLoginPage.js";

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
      await expect(employerLoginPage.getSuccessCreationMessage).toBeVisible();
    });
  }
  test("should show an error for duplicate credentials", async () => {
    const user = employerRegisterData.duplicateUser;
    await employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
    await expect(employerRegisterPage[user.expectedError]).toBeVisible();
  });
  for (const user of employerRegisterData.invalidCases) {
    test(`should show an error for invalid input: ${user.description}`, async () => {
      await employerRegisterPage.register(user.name, user.email, user.password, user.confirmPassword);
      await expect(employerRegisterPage[user.expectedError]).toBeVisible();
    });
  }
});