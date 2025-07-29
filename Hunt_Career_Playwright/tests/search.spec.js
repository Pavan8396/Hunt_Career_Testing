import { expect, test as baseTest } from '@playwright/test';
import searchData  from '../data/searchData.json';
import { SearchPage } from '../pages/SearchPage.js';

const test = baseTest.extend({
  searchPage : async({page}, use) => {
    await use (new SearchPage(page));
  },
});

test.describe('Search Tests', () => {

  test.beforeEach(async({searchPage}) =>{
    await searchPage.navigate();
  })  

  test("TC-1: Search with a valid keyword", async ({ searchPage }) => {
    const searchTerm = searchData.validSearch.searchTerm
    await searchPage.search(searchTerm);
    await searchPage.searchTag(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("TC-2-Search with a invalid keyword", async ({ searchPage}) =>{
    const searchTerm = searchData.invalidSearch.searchTerm
    await searchPage.search(searchTerm);
    await searchPage.searchTag(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await expect(searchPage.noJobsFoundMessage).toBeVisible();
  })

  test("TC-3: Search with a special character", async ({ searchPage}) =>{
    const searchTerm = searchData.specialCharSearch.searchTerm
    await searchPage.search(searchTerm);
    await searchPage.searchTag(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  })

  test("TC-4: Search with filter", async ({ searchPage}) =>{
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
});