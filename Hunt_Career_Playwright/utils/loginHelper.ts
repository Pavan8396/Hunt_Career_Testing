import { LoginPage } from "./../pages/LoginPage.js";

export async function loginAsValidUser(loginPage: LoginPage, loginData: any) {
  await loginPage.login(loginData.validUser.email, loginData.validUser.password);
}