import { test as baseTest } from '@playwright/test';
import { login } from '../utils/commands.js';
import loginData from '../data/loginData.json';
import { LoginPage } from '../pages/LoginPage.js';

const test = baseTest.extend({
  loginPage : async({page}, use) => {
    await use (new LoginPage(page));
  },
});

test.describe('Login Tests', () => {
  test('TC-1: TC-1-Enter with valid credcredentials', async ({ page,loginPage }) => {
    await login(page, loginData.validUser.email, loginData.validUser.password);
    await loginPage.waitUntilVisible(loginPage.successMessage);
    await loginPage.waitUntilNotVisible(loginPage.successMessage);
  });

  test('TC-2: Enter User who is Not registerd', async ({ page,loginPage }) => {
    await login(page, loginData.invalidUser.email, loginData.invalidUser.password);
    await loginPage.waitUntilVisible(loginPage.invalidCredentialsMessage);
    await loginPage.waitUntilNotVisible(loginPage.invalidCredentialsMessage);
  });

  test('TC-3-Enter Invalid Email and Valid password', async ({page,loginPage})=>{
    await login(page, loginData.invalidEmail.email, loginData.invalidEmail.password);
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  })

  test('TC-4-Enter Valid Email and Invalid Password', async ({page,loginPage})=>{
    await login(page, loginData.invalidPassowrd.email, loginData.invalidPassowrd.password);
    await loginPage.waitUntilVisible(loginPage.passwordLengthMsg);
    await loginPage.waitUntilNotVisible(loginPage.passwordLengthMsg);
  })

  test('TC-5-Dont enter any data into Email field and click on Login Button', async ({page,loginPage})=>{
    await page.goto('/login');
    await loginPage.clickElement(loginPage.loginButton);
    await loginPage.waitUntilVisible(loginPage.emailRequiredMsg);
    await loginPage.waitUntilNotVisible(loginPage.emailRequiredMsg);
  })

  test('TC-6-Dont enter any data into Password field and click on Login Button', async ({page,loginPage})=>{
    await login(page, loginData.blankPassword.email, loginData.blankPassword.password);
    await loginPage.waitUntilVisible(loginPage.passwordRequiredMsg);
    await loginPage.waitUntilNotVisible(loginPage.passwordRequiredMsg);
  })

  test('TC-7-Enter Email without the domain name and click on login button', async ({page,loginPage})=>{
    await login(page, loginData.EmailwithoutDomain.email, loginData.EmailwithoutDomain.password);
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  })

  test('TC-8-Enter email without username and click on login button', async ({page,loginPage})=>{
    await login(page, loginData.emailWihoutName.email, loginData.emailWihoutName.password);
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  })

  test('TC-9-Enter the Email with two at the rate characters and click on login button', async ({page,loginPage})=>{
    await login(page, loginData["emailhaving2@"].email, loginData["emailhaving2@"].password);
    await loginPage.waitUntilVisible(loginPage.validEmailMsg);
    await loginPage.waitUntilNotVisible(loginPage.validEmailMsg);
  })

  test('TC-10-Enter email with dot at the end and click on login button', async ({page,loginPage})=>{
    await login(page, loginData.emailWithDotAfterName.email, loginData.emailWithDotAfterName.password);
    await loginPage.waitUntilVisible(loginPage.successMessage);
    await loginPage.waitUntilNotVisible(loginPage.successMessage);
  })

  test('TC-11-Enter Email with dot in the middle of name and click on Login button', async ({page,loginPage})=>{
    await login(page, loginData.emailWithDotInbetweenName.email, loginData.emailWithDotInbetweenName.password);
    await loginPage.waitUntilVisible(loginPage.successMessage);
    await loginPage.waitUntilNotVisible(loginPage.successMessage);
  })
});
