import { test as baseTest } from "@playwright/test";
import { registerData } from "../data/registerData.js";
import { RegisterPage } from "../pages/RegisterPage.js";

const test = baseTest.extend({
  registerPage: async ({ page }, use) => {
    await use(new RegisterPage(page));
  },
});

test.describe("Register Tests", () => {
  test.beforeEach(async ({ registerPage }) => {
    await registerPage.navigate();
  });

  test("RTC-1: Enter with valid credcredentials", async ({ registerPage }) => {
    await registerPage.register(
      registerData.validUser.firstName,
      registerData.validUser.lastName,
      registerData.validUser.email,
      registerData.validUser.password,
      registerData.validUser.confirmPassword,
      registerData.validUser.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkSuccessCreationMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkSuccessCreationMessage,
    );
  });

  test("RTC-2-Enter with duplicate credentials", async ({ registerPage }) => {
    await registerPage.register(
      registerData.duplicateUser.firstName,
      registerData.duplicateUser.lastName,
      registerData.duplicateUser.email,
      registerData.duplicateUser.password,
      registerData.duplicateUser.confirmPassword,
      registerData.duplicateUser.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkDuplicateErrorMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkDuplicateErrorMessage,
    );
  });

  test("RTC-3-Enter without FirstName", async ({ registerPage }) => {
    await registerPage.registerFirstPage(
      registerData.noFirstName.firstName,
      registerData.noFirstName.lastName,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkFirstNameBlankMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkFirstNameBlankMessage,
    );
  });

  test("RTC-4-Enter without LastName", async ({ registerPage }) => {
    await registerPage.registerFirstPage(
      registerData.noLastName.firstName,
      registerData.noLastName.lastName,
    );
    await registerPage.waitUntilVisible(registerPage.checkLastNameBlankMessage);
    await registerPage.waitUntilNotVisible(
      registerPage.checkLastNameBlankMessage,
    );
  });

  test("RTC-5-Enter without Email", async ({ registerPage }) => {
    await registerPage.registerSecondPage(
      registerData.noEmailName.firstName,
      registerData.noEmailName.lastName,
      registerData.noEmailName.email,
      registerData.noEmailName.password,
      registerData.noEmailName.confirmPassword,
      registerData.noEmailName.phoneNumber,
    );
    await registerPage.waitUntilVisible(registerPage.checkEmailBlankMessage);
    await registerPage.waitUntilNotVisible(registerPage.checkEmailBlankMessage);
  });

  test("RTC-6-Enter without Password", async ({ registerPage }) => {
    await registerPage.registerSecondPage(
      registerData.noPasswordName.firstName,
      registerData.noPasswordName.lastName,
      registerData.noPasswordName.email,
      registerData.noPasswordName.password,
      registerData.noPasswordName.confirmPassword,
      registerData.noPasswordName.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage,
    );
  });

  test("RTC-7-Enter Password with less characters", async ({
    registerPage,
  }) => {
    await registerPage.registerSecondPage(
      registerData.passwordWithLessCharacters.firstName,
      registerData.passwordWithLessCharacters.lastName,
      registerData.passwordWithLessCharacters.email,
      registerData.passwordWithLessCharacters.password,
      registerData.passwordWithLessCharacters.confirmPassword,
      registerData.passwordWithLessCharacters.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPasswordMustHaveRequiredNumberCharactersMessage,
    );
  });

  test("RTC-8-Enter without ConfirmPassword", async ({ registerPage }) => {
    await registerPage.registerSecondPage(
      registerData.noConfirmPasswordName.firstName,
      registerData.noConfirmPasswordName.lastName,
      registerData.noConfirmPasswordName.email,
      registerData.noConfirmPasswordName.password,
      registerData.noConfirmPasswordName.confirmPassword,
      registerData.noConfirmPasswordName.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPasswordNotMatchMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPasswordNotMatchMessage,
    );
  });

  test("RTC-9-Enter without PhoneNumber", async ({ registerPage }) => {
    await registerPage.register(
      registerData.noPhoneNumberName.firstName,
      registerData.noPhoneNumberName.lastName,
      registerData.noPhoneNumberName.email,
      registerData.noPhoneNumberName.password,
      registerData.noPhoneNumberName.confirmPassword,
      registerData.noPhoneNumberName.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPhoneNumberRequiredMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPhoneNumberRequiredMessage,
    );
  });

  test("RTC-10-Enter email without All Domain", async ({ registerPage }) => {
    await registerPage.registerSecondPage(
      registerData.emailWithoutAllDomain.firstName,
      registerData.emailWithoutAllDomain.lastName,
      registerData.emailWithoutAllDomain.email,
      registerData.emailWithoutAllDomain.password,
      registerData.emailWithoutAllDomain.confirmPassword,
      registerData.emailWithoutAllDomain.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
  });

  test("RTC-11-Enter email without Top Level Domain", async ({
    registerPage,
  }) => {
    await registerPage.registerSecondPage(
      registerData.emailWithoutTopLevelDomain.firstName,
      registerData.emailWithoutTopLevelDomain.lastName,
      registerData.emailWithoutTopLevelDomain.email,
      registerData.emailWithoutTopLevelDomain.password,
      registerData.emailWithoutTopLevelDomain.confirmPassword,
      registerData.emailWithoutTopLevelDomain.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
  });

  test("RTC-12-Enter email with double at", async ({ registerPage }) => {
    await registerPage.registerSecondPage(
      registerData.emailDoubleAt.firstName,
      registerData.emailDoubleAt.lastName,
      registerData.emailDoubleAt.email,
      registerData.emailDoubleAt.password,
      registerData.emailDoubleAt.confirmPassword,
      registerData.emailDoubleAt.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
  });

  test("RTC-13-Enter email with dot before at", async ({ registerPage }) => {
    await registerPage.register(
      registerData.emailDotBeforeAt.firstName,
      registerData.emailDotBeforeAt.lastName,
      registerData.emailDotBeforeAt.email,
      registerData.emailDotBeforeAt.password,
      registerData.emailDotBeforeAt.confirmPassword,
      registerData.emailDotBeforeAt.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkSuccessCreationMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkSuccessCreationMessage,
    );
  });

  test("RTC-14-Enter email without Domain", async ({ registerPage }) => {
    await registerPage.registerSecondPage(
      registerData.emailWithoutDomain.firstName,
      registerData.emailWithoutDomain.lastName,
      registerData.emailWithoutDomain.email,
      registerData.emailWithoutDomain.password,
      registerData.emailWithoutDomain.confirmPassword,
      registerData.emailWithoutDomain.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
  });

  test("RTC-15-Enter email without username", async ({ registerPage }) => {
    await registerPage.registerSecondPage(
      registerData.emailMissingUsername.firstName,
      registerData.emailMissingUsername.lastName,
      registerData.emailMissingUsername.email,
      registerData.emailMissingUsername.password,
      registerData.emailMissingUsername.confirmPassword,
      registerData.emailMissingUsername.phoneNumber,
    );
    await registerPage.waitUntilVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
    await registerPage.waitUntilNotVisible(
      registerPage.checkPleaseEnterValidEmailAddressMessage,
    );
  });
});
