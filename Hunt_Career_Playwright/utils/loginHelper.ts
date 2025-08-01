import { LoginPage } from "./../pages/LoginPage.js";

export async function loginAsValidUser(
  loginPage: LoginPage,
  loginData: any,
  page,
) {
  await loginPage.navigate();
  await loginPage.login(
    loginData.validUser.email,
    loginData.validUser.password,
  );
}
