import { expect, test as baseTest } from "@playwright/test";
import { searchData } from "../data/searchData.js";
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
    await searchPage.navigateTo();
  });

  test("should return results for a valid keyword search", async ({
    searchPage,
  }) => {
    const { searchTerm } = searchData.validSearch;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("should show a 'no jobs found' message for an invalid keyword", async ({
    searchPage,
  }) => {
    const { searchTerm } = searchData.invalidSearch;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await expect(searchPage.noJobsFoundMessage).toBeVisible();
  });

  test("should show a 'no jobs found' message for a search with special characters", async ({
    searchPage,
  }) => {
    const { searchTerm } = searchData.specialCharSearch;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("should display correct tags when searching with location and job type filters", async ({
    searchPage,
  }) => {
    const { searchTerm, location, jobType } = searchData.searchWithFilters;
    await searchPage.search(searchTerm, location, jobType);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await expect(searchPage.locationTag(location)).toBeVisible();
    await expect(searchPage.jobTypeTag(jobType)).toBeVisible();
  });

  test("should allow a logged-in user to view job details from search results", async ({
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

  test("should allow a logged-in user to save a job and see it in the saved jobs page", async ({
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
    await homePage.clickOnSavedJobs.click();
    await expect(savePage.savedJobCard(jobTitle)).toBeVisible();
  });
});