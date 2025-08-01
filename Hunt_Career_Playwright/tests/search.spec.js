import { expect, test as baseTest } from "@playwright/test";
import testData from "../data/testData.json";
import { SearchPage } from "../pages/SearchPage.js";
import { loginAsValidUser } from "../utils/loginHelper.js";
import { HomePage } from "../pages/HomePage.js";
import { LoginPage } from "../pages/LoginPage.js";

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
});

test.describe("Search Tests", () => {
  test.beforeEach(async ({ searchPage }) => {
    await searchPage.navigate();
  });

  test("should display search results when searching with a valid keyword", async ({
    searchPage,
  }) => {
    const searchTerm = testData.search.validSearch.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("should display 'No jobs found' message when searching with an invalid keyword", async ({
    searchPage,
  }) => {
    const searchTerm = testData.search.invalidSearch.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).not.toBeVisible();
    await expect(searchPage.noJobsFoundMessage).toBeVisible();
  });

  test("should display search results when searching with a special character", async ({
    searchPage,
  }) => {
    const searchTerm = testData.search.specialCharSearch.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("should display filtered search results when applying location and job type filters", async ({
    searchPage,
  }) => {
    const searchTerm = testData.search.searchWithFilters.searchTerm;
    await searchPage.search(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await searchPage.locationButton.click();
    await searchPage.locationSearchInput.fill(
      testData.search.searchWithFilters.location,
    );
    await searchPage
      .locationOption(testData.search.searchWithFilters.location)
      .click();
    await expect(
      searchPage.locationTag(testData.search.searchWithFilters.location),
    ).toBeVisible();
    await searchPage.jobTypeButton.click();
    await searchPage.jobTypeSearchInput.fill(
      testData.search.searchWithFilters.jobType,
    );
    await searchPage
      .jobTypeOption(testData.search.searchWithFilters.jobType)
      .click();
    await expect(
      searchPage.jobTypeTag(testData.search.searchWithFilters.jobType),
    ).toBeVisible();
  });

  test("should allow a logged-in user to search and view job details", async ({
    loginPage,
    searchPage,
    homePage,
  }) => {
    await loginAsValidUser(loginPage, testData.login);
    await searchPage.search(testData.search.validSearch.searchTerm);
    await homePage.viewDetailsLink.first().click();
  });
});
