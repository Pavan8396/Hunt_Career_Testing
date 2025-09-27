import { expect, test as baseTest } from "@playwright/test";
import searchData from "../data/searchData.json";
import loginData from "../data/loginData.json";
import { SearchPage } from "../pages/SearchPage.js";
import { loginAsValidUser } from "../utils/loginHelper.js";
import { HomePage } from "../pages/HomePage.js";
import { LoginPage } from "../pages/LoginPage.js";
import { ViewDetailsPage } from "../pages/ViewDetailsPage.js";
import { SavePage } from "../pages/SavePage.js";

const test = baseTest.extend({
  searchPage: async ({ page }, use) => {
    await use(new SearchPage(page));
  },
  loginPage: async ({ page }, use) => {
    await use(new LoginPage(page));
  },
  homePage: async ({ page }, use) => {
    await use(new HomePage(page));
  },
  viewDetailsPage: async ({ page }, use) => {
    await use(new ViewDetailsPage(page));
  },
  savePage: async ({ page }, use) => {
    await use(new SavePage(page));
  },
});

test.describe("Search Tests", () => {
  test.beforeEach(async ({ searchPage }) => {
    await searchPage.navigate();
  });

  test("STC-1: Search with a valid keyword", async ({ searchPage }) => {
    const searchTerm = searchData.validSearch.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("STC-2-Search with a invalid keyword", async ({ searchPage }) => {
    const searchTerm = searchData.invalidSearch.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await expect(searchPage.noJobsFoundMessage).toBeVisible();
  });

  test("STC-3: Search with a special character", async ({ searchPage }) => {
    const searchTerm = searchData.specialCharSearch.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("STC-4: Search with filter", async ({ searchPage }) => {
    const searchTerm = searchData.searchWithFilters.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await searchPage.locationButton.click();
    await searchPage.locationSearchInput.fill(
      searchData.searchWithFilters.location,
    );
    await searchPage
      .locationOption(searchData.searchWithFilters.location)
      .click();
    await expect(
      searchPage.locationTag(searchData.searchWithFilters.location),
    ).toBeVisible();
    await searchPage.jobTypeButton.click();
    await searchPage.jobTypeSearchInput.fill(
      searchData.searchWithFilters.jobType,
    );
    await searchPage
      .jobTypeOption(searchData.searchWithFilters.jobType)
      .click();
    await expect(
      searchPage.jobTypeTag(searchData.searchWithFilters.jobType),
    ).toBeVisible();
  });

  test("STC-5-Login and Search and click on view discription page", async ({
    searchPage,
    loginPage,
    homePage,
    viewDetailsPage,
  }) => {
    await loginAsValidUser(loginPage, loginData);
    await searchPage.search(searchData.validSearch.searchTerm);
    await homePage.viewDetailsLink.first().click();
    await expect(viewDetailsPage.ApplyButton).toBeVisible();
  });

  test("STC-6-Login and Save the job and view the saved job in saved jobs page", async ({
    page,
    loginPage,
    homePage,
    searchPage,
    savePage,
  }) => {
    await loginAsValidUser(loginPage, loginData, page);
    await searchPage.search(searchData.validSearch.searchTerm);
    const jobIndex = 1;
    const jobTitle = await searchPage.jobCardTitle(jobIndex).textContent();
    await homePage.saveButton.nth(jobIndex).click();
    await homePage.saveConfirmationPopup.click();
    await homePage.waitUntilVisible(homePage.savedMessage(jobTitle));
    await homePage.waitUntilNotVisible(homePage.savedMessage(jobTitle));
    //await expect(homePage.savedMessage(jobTitle)).toBeVisible();
    //await homePage.saveButton.click();
    await homePage.clickOnSavedJobs.click();
    await expect(savePage.savedJobCard(jobTitle)).toBeVisible();
  });
});
