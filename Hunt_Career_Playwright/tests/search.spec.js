import { expect, test as baseTest } from '@playwright/test';
import searchData  from '../data/searchData.json';
import loginData from '../data/loginData.json'
import { SearchPage } from '../pages/SearchPage.js';
import { loginAsValidUser } from '../utils/loginHelper.js';
import { HomePage } from '../pages/HomePage.js';
import { LoginPage } from '../pages/LoginPage.js';

const test = baseTest.extend({
  searchPage : async({page}, use) => {
    await use (new SearchPage(page));
  },
  loginPage: async ({ page }, use) => {
    await use(new LoginPage(page));
  },
  homePage: async ({ page }, use) => {
    await use(new HomePage(page));
  }
});

test.describe('Search Tests', () => {

  test.beforeEach(async({searchPage}) =>{
    await searchPage.navigate();
  })  

  test("STC-1: Search with a valid keyword", async ({ searchPage }) => {
    const searchTerm = searchData.validSearch.searchTerm
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("STC-2-Search with a invalid keyword", async ({ searchPage}) =>{
    const searchTerm = searchData.invalidSearch.searchTerm
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await expect(searchPage.noJobsFoundMessage).toBeVisible();
  })

  test("STC-3: Search with a special character", async ({ searchPage}) =>{
    const searchTerm = searchData.specialCharSearch.searchTerm
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  })

  test("STC-4: Search with filter", async ({ searchPage}) =>{
    const searchTerm = searchData.searchWithFilters.searchTerm
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await searchPage.locationButton.click();
    await searchPage.locationSearchInput.fill(searchData.searchWithFilters.location);
    await searchPage.locationOption(searchData.searchWithFilters.location).click();
    await expect(searchPage.locationTag(searchData.searchWithFilters.location)).toBeVisible();
    await searchPage.jobTypeButton.click();
    await searchPage.jobTypeSearchInput.fill(searchData.searchWithFilters.jobType);
    await searchPage.jobTypeOption(searchData.searchWithFilters.jobType).click();
    await expect(searchPage.jobTypeTag(searchData.searchWithFilters.jobType)).toBeVisible();
  })

  test("STC-5-Login and Search and click on view discription page", async ({searchPage, loginPage, homePage})=>{
    await loginAsValidUser(loginPage, loginData);
    await searchPage.search(searchData.validSearch.searchTerm);
    await homePage.viewDetailsLink.first().click();
  })
});