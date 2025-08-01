import { test as baseTest } from "@playwright/test";
import testData from "../data/testData.json";
import { LoginPage } from "../pages/LoginPage.js";

const test = baseTest.extend({
  loginPage: async ({ page }, use) => {
    await use(new LoginPage(page));
  },
});

test.describe("Login Tests", () => {
  test.beforeEach(async ({ loginPage }) => {
    await loginPage.navigate();
  });

  test("LTC-1: Enter with valid credcredentials", async ({ loginPage }) => {
    await loginPage.login(
      testData.login.validUser.email,
      testData.login.validUser.password,
    );
    await loginPage.waitUntilVisible(loginPage.successMessage);
    await loginPage.waitUntilNotVisible(loginPage.successMessage);
  });

  test("LTC-2: Enter User who is Not registerd", async ({
    loginPage,
    page,
  }) => {
    await loginPage.login(
      testData.login.invalidUser.email,
      testData.login.invalidUser.password,
    );
    await loginPage.waitUntilVisible(loginPage.invalidCredentialsMessage);
    await loginPage.waitUntilNotVisible(loginPage.invalidCredentialsMessage);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-3-Enter Invalid Email and Valid password", async ({
    loginPage,
    page,
  }) => {
    await loginPage.login(
      testData.login.invalidEmail.email,
      testData.login.invalidEmail.password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-4-Enter Valid Email and Invalid Password", async ({
    loginPage,
    page,
  }) => {
    await loginPage.login(
      testData.login.invalidPassowrd.email,
      testData.login.invalidPassowrd.password,
    );
    await loginPage.waitUntilVisible(loginPage.passwordLengthMsg);
    await loginPage.waitUntilNotVisible(loginPage.passwordLengthMsg);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-5-Dont enter any data into Email field and click on Login Button", async ({
    loginPage,
    page,
  }) => {
    await loginPage.clickElement(loginPage.loginButton);
    await loginPage.waitUntilVisible(loginPage.emailRequiredMsg);
    await loginPage.waitUntilNotVisible(loginPage.emailRequiredMsg);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-6-Dont enter any data into Password field and click on Login Button", async ({
    loginPage,
    page,
  }) => {
    await loginPage.login(
      testData.login.blankPassword.email,
      testData.login.blankPassword.password,
    );
    await loginPage.waitUntilVisible(loginPage.passwordRequiredMsg);
    await loginPage.waitUntilNotVisible(loginPage.passwordRequiredMsg);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-7-Enter Email without the domain name and click on login button", async ({
    loginPage,
    page,
  }) => {
    await loginPage.login(
      testData.login.EmailwithoutDomain.email,
      testData.login.EmailwithoutDomain.password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-8-Enter email without username and click on login button", async ({
    loginPage,
    page,
  }) => {
    await loginPage.login(
      testData.login.emailWihoutName.email,
      testData.login.emailWihoutName.password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-9-Enter the Email with two at the rate characters and click on login button", async ({
    loginPage,
    page,
  }) => {
    await loginPage.login(
      testData.login["emailhaving2@"].email,
      testData.login["emailhaving2@"].password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
    expect(page.url()).not.toContain("/dashboard");
  });

  test("LTC-10-Enter email with dot at the end and click on login button", async ({
    loginPage,
  }) => {
    await loginPage.login(
      testData.login.emailWithDotAfterName.email,
      testData.login.emailWithDotAfterName.password,
    );
    await loginPage.waitUntilVisible(loginPage.successMessage);
    await loginPage.waitUntilNotVisible(loginPage.successMessage);
  });

  test("LTC-11-Enter Email with dot in the middle of name and click on Login button", async ({
    loginPage,
  }) => {
    await loginPage.login(
      testData.login.emailWithDotInbetweenName.email,
      testData.login.emailWithDotInbetweenName.password,
    );
    await loginPage.waitUntilVisible(loginPage.successMessage);
    await loginPage.waitUntilNotVisible(loginPage.successMessage);
  });
});
