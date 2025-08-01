export async function loginAsValidUser(loginPage, loginData) {
  await loginPage.login(loginData.validUser.email, loginData.validUser.password);
}