import { test, expect } from "@playwright/test";
import { SearchPage } from "../pages/SearchPage";
import { searchData } from "../data/searchData";

test.describe("Job Search", () => {
    let searchPage;

    test.beforeEach(async ({ page }) => {
        searchPage = new SearchPage(page);
        await searchPage.navigate();
    });

    test("should return results for a valid keyword search", async () => {
        const { searchTerm } = searchData.validSearch;
        await searchPage.search(searchTerm);
        await expect(searchPage.getSearchTag(searchTerm)).toBeVisible();
    });

    test("should show a 'no jobs found' message for an invalid keyword", async () => {
        const { searchTerm } = searchData.invalidSearch;
        await searchPage.search(searchTerm);
        await expect(searchPage.getSearchTag(searchTerm)).toBeVisible();
        await expect(searchPage.noJobsFoundMessage).toBeVisible();
    });

    test("should show a 'no jobs found' message for a search with special characters", async () => {
        const { searchTerm } = searchData.specialCharSearch;
        await searchPage.search(searchTerm);
        await expect(searchPage.getSearchTag(searchTerm)).toBeVisible();
        await expect(searchPage.noJobsFoundMessage).toBeVisible();
    });

    test("should display correct tags when searching with location and job type filters", async () => {
        const { searchTerm, location, jobType } = searchData.searchWithFilters;
        await searchPage.search(searchTerm, location, jobType);
        await expect(searchPage.getSearchTag(searchTerm)).toBeVisible();
        await expect(searchPage.getLocationTag(location)).toBeVisible();
        await expect(searchPage.getJobTypeTag(jobType)).toBeVisible();
    });
});