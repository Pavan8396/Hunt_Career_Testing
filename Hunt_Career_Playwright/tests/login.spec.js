import { test as baseTest } from "@playwright/test";
import loginData from "../data/loginData.json";
import { LoginPage } from "../pages/LoginPage.js";
import { HomePage } from "../pages/HomePage.js";

const test = baseTest.extend({
  loginPage: async ({ page }, use) => {
    await use(new LoginPage(page));
  },
  homePage: async ({ page }, use) => {
    await use(new HomePage(page));
  },
});

test.describe("Login Tests", () => {
  test.beforeEach(async ({ loginPage }) => {
    await loginPage.navigate();
  });

  test("LTC-1: Enter with valid credcredentials", async ({ loginPage, homePage }) => {
    await loginPage.login(
      loginData.validUser.email,
      loginData.validUser.password,
    );
    await loginPage.waitUntilVisible(homePage.successMessage);
    await loginPage.waitUntilNotVisible(homePage.successMessage);
  });

  test("LTC-2: Enter User who is Not registerd", async ({ loginPage }) => {
    await loginPage.login(
      loginData.invalidUser.email,
      loginData.invalidUser.password,
    );
    await loginPage.waitUntilVisible(loginPage.invalidCredentialsMessage);
    await loginPage.waitUntilNotVisible(loginPage.invalidCredentialsMessage);
  });

  test("LTC-3-Enter Invalid Email and Valid password", async ({
    loginPage,
  }) => {
    await loginPage.login(
      loginData.invalidEmail.email,
      loginData.invalidEmail.password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  });

  test("LTC-4-Enter Valid Email and Invalid Password", async ({
    loginPage,
  }) => {
    await loginPage.login(
      loginData.invalidPassowrd.email,
      loginData.invalidPassowrd.password,
    );
    await loginPage.waitUntilVisible(loginPage.passwordLengthMsg);
    await loginPage.waitUntilNotVisible(loginPage.passwordLengthMsg);
  });

  test("LTC-5-Dont enter any data into Email field and click on Login Button", async ({
    loginPage,
  }) => {
    await loginPage.clickElement(loginPage.loginButton);
    await loginPage.waitUntilVisible(loginPage.emailRequiredMsg);
    await loginPage.waitUntilNotVisible(loginPage.emailRequiredMsg);
  });

  test("LTC-6-Dont enter any data into Password field and click on Login Button", async ({
    loginPage,
  }) => {
    await loginPage.login(
      loginData.blankPassword.email,
      loginData.blankPassword.password,
    );
    await loginPage.waitUntilVisible(loginPage.passwordRequiredMsg);
    await loginPage.waitUntilNotVisible(loginPage.passwordRequiredMsg);
  });

  test("LTC-7-Enter Email without the domain name and click on login button", async ({
    loginPage,
  }) => {
    await loginPage.login(
      loginData.EmailwithoutDomain.email,
      loginData.EmailwithoutDomain.password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  });

  test("LTC-8-Enter email without username and click on login button", async ({
    loginPage,
  }) => {
    await loginPage.login(
      loginData.emailWihoutName.email,
      loginData.emailWihoutName.password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  });

  test("LTC-9-Enter the Email with two at the rate characters and click on login button", async ({
    loginPage,
  }) => {
    await loginPage.login(
      loginData["emailhaving2@"].email,
      loginData["emailhaving2@"].password,
    );
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  });

  test("LTC-10-Enter email with dot at the end and click on login button", async ({
    loginPage, homePage
  }) => {
    await loginPage.login(
      loginData.emailWithDotAfterName.email,
      loginData.emailWithDotAfterName.password,
    );
    await loginPage.waitUntilVisible(homePage.successMessage);
    await loginPage.waitUntilNotVisible(homePage.successMessage);
  });

  test("LTC-11-Enter Email with dot in the middle of name and click on Login button", async ({
    loginPage, homePage
  }) => {
    await loginPage.login(
      loginData.emailWithDotInbetweenName.email,
      loginData.emailWithDotInbetweenName.password,
    );
    await loginPage.waitUntilVisible(homePage.successMessage);
    await loginPage.waitUntilNotVisible(homePage.successMessage);
  });
});
