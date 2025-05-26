import { test } from '@playwright/test';
import { register, registerFirstPage, registerSecondPage } from '../utils/commands.js';
import { registerData } from '../data/registerData.js';
import { RegisterPage } from '../pages/RegisterPage.js';

test.describe('Register Tests', () => {
  test("TC-1: Enter with valid credcredentials", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await register(page, registerData.validUser.firstName, registerData.validUser.lastName, registerData.validUser.email, registerData.validUser.password, registerData.validUser.confirmPassword, registerData.validUser.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkSuccessCreationMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkSuccessCreationMessage);
  });

  test("TC-2-Enter with duplicate credentials", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await register(page, registerData.duplicateUser.firstName, registerData.duplicateUser.lastName, registerData.duplicateUser.email, registerData.duplicateUser.password, registerData.duplicateUser.confirmPassword, registerData.duplicateUser.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkDuplicateErrorMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkDuplicateErrorMessage);
  });

  test("TC-3-Enter without FirstName", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerFirstPage(page, registerData.noFirstName.firstName, registerData.noFirstName.lastName);
    await registerPage.waitUntilVisible(registerPage.checkFirstNameBlankMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkFirstNameBlankMessage);
  });

  test("TC-4-Enter without LastName", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerFirstPage(page, registerData.noLastName.firstName, registerData.noLastName.lastName);
    await registerPage.waitUntilVisible(registerPage.checkLastNameBlankMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkLastNameBlankMessage);
  });

  test("TC-5-Enter without Email", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.noEmailName.firstName, registerData.noEmailName.lastName, registerData.noEmailName.email, registerData.noEmailName.password, registerData.noEmailName.confirmPassword, registerData.noEmailName.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkEmailBlankMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkEmailBlankMessage);
  });

  test("TC-6-Enter without Password", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.noPasswordName.firstName, registerData.noPasswordName.lastName, registerData.noPasswordName.email, registerData.noPasswordName.password, registerData.noPasswordName.confirmPassword, registerData.noPasswordName.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage);
  });

  test("TC-7-Enter Password with less characters", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.passwordWithLessCharacters.firstName, registerData.passwordWithLessCharacters.lastName, registerData.passwordWithLessCharacters.email, registerData.passwordWithLessCharacters.password, registerData.passwordWithLessCharacters.confirmPassword, registerData.passwordWithLessCharacters.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage);
  });

  test("TC-8-Enter without ConfirmPassword", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.noConfirmPasswordName.firstName, registerData.noConfirmPasswordName.lastName, registerData.noConfirmPasswordName.email, registerData.noConfirmPasswordName.password, registerData.noConfirmPasswordName.confirmPassword, registerData.noConfirmPasswordName.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPasswordNotMatchMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPasswordNotMatchMessage);
  });

  test("TC-9-Enter without PhoneNumber", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await register(page, registerData.noPhoneNumberName.firstName, registerData.noPhoneNumberName.lastName, registerData.noPhoneNumberName.email, registerData.noPhoneNumberName.password, registerData.noPhoneNumberName.confirmPassword, registerData.noPhoneNumberName.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPhoneNumberRequiredMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPhoneNumberRequiredMessage);
  });

  test("TC-10-Enter email without All Domain", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.emailWithoutAllDomain.firstName, registerData.emailWithoutAllDomain.lastName, registerData.emailWithoutAllDomain.email, registerData.emailWithoutAllDomain.password, registerData.emailWithoutAllDomain.confirmPassword, registerData.emailWithoutAllDomain.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
  });

  test("TC-11-Enter email without Top Level Domain", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.emailWithoutTopLevelDomain.firstName, registerData.emailWithoutTopLevelDomain.lastName, registerData.emailWithoutTopLevelDomain.email, registerData.emailWithoutTopLevelDomain.password, registerData.emailWithoutTopLevelDomain.confirmPassword, registerData.emailWithoutTopLevelDomain.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
  });

  test("TC-12-Enter email with double at", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.emailDoubleAt.firstName, registerData.emailDoubleAt.lastName, registerData.emailDoubleAt.email, registerData.emailDoubleAt.password, registerData.emailDoubleAt.confirmPassword, registerData.emailDoubleAt.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
  });

  test("TC-13-Enter email with dot before at", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await register(page, registerData.emailDotBeforeAt.firstName, registerData.emailDotBeforeAt.lastName, registerData.emailDotBeforeAt.email, registerData.emailDotBeforeAt.password, registerData.emailDotBeforeAt.confirmPassword, registerData.emailDotBeforeAt.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkSuccessCreationMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkSuccessCreationMessage);
  });

  test("TC-14-Enter email without Domain", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.emailWithoutDomain.firstName, registerData.emailWithoutDomain.lastName, registerData.emailWithoutDomain.email, registerData.emailWithoutDomain.password, registerData.emailWithoutDomain.confirmPassword, registerData.emailWithoutDomain.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
  });

  test("TC-15-Enter email without username", async ({ page }) => {
    const registerPage = new RegisterPage(page);
    await registerSecondPage(page, registerData.emailMissingUsername.firstName, registerData.emailMissingUsername.lastName, registerData.emailMissingUsername.email, registerData.emailMissingUsername.password, registerData.emailMissingUsername.confirmPassword, registerData.emailMissingUsername.phoneNumber);
    await registerPage.waitUntilVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkPleaseEnterValidEmailAddressMessage);
  });
});