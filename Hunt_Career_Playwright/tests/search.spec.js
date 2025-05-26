import { expect, test as baseTest } from '@playwright/test';
import { search } from '../utils/commands.js';
import searchData  from '../data/searchData.json';
import { SearchPage } from '../pages/SearchPage.js';

const test = baseTest.extend({
  searchPage : async({page}, use) => {
    await use (new SearchPage(page));
  },
});

test.describe('Search Tests', () => {
  test("TC-1: Search with a valid keyword", async ({ page, searchPage }) => {
    const searchTerm = searchData.validSearch.searchTerm
    await search(page, searchTerm);
    await searchPage.searchTag(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
  });

  test("TC-2-Search with a invalid keyword", async ({ page, searchPage}) =>{
    const searchTerm = searchData.invalidSearch.searchTerm
    await search(page, searchTerm);
    await searchPage.searchTag(searchTerm);
    await expect(searchPage.searchTag(searchTerm)).toBeVisible();
    await expect(searchPage.noJobsFoundMessage).toBeVisible();
  })
});