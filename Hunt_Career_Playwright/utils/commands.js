import { SearchPage } from '../pages/SearchPage.js';
import { LoginPage } from '../pages/LoginPage.js';
import { RegisterPage} from '../pages/RegisterPage.js';

export async function login(page, email, password) {
  await page.goto('/login');
  const loginPage = new LoginPage(page);
  await loginPage.login(email, password);
}

export async function register(page, firstName, lastName, email, password, confirmPassword, phoneNumber) {
  await page.goto('/signup');
  const registerPage = new RegisterPage(page);
  await registerPage.register(firstName, lastName, email, password, confirmPassword, phoneNumber);
}

export async function registerFirstPage(page, firstName, lastName) {
  await page.goto('/signup');
  const registerPage = new RegisterPage(page);
  await registerPage.registerFirstPage(firstName, lastName);
}

export async function registerSecondPage(page, firstName, lastName, email, password, confirmPassword) {
  await page.goto('/signup');
  const registerPage = new RegisterPage(page);
  await registerPage.registerSecondPage(firstName, lastName, email, password, confirmPassword);
}

export async function search(page, searchTerm, location, jobType) {
  await page.goto('/');
  const searchPage = new SearchPage(page);
  await searchPage.search(searchTerm, location, jobType);
}