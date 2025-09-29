import SearchPage from "../pageObjects/SearchPage";
import { searchData } from "../fixtures/searchData";

describe("Job Search", () => {
    let searchPage;

    beforeEach(() => {
        searchPage = new SearchPage();
        searchPage.navigate();
    });

    it("should return results for a valid keyword search", () => {
        const { searchTerm } = searchData.validSearch;
        searchPage.search(searchTerm);
        searchPage.getSearchTag(searchTerm).should("be.visible");
    });

    it("should show a 'no jobs found' message for an invalid keyword", () => {
        const { searchTerm } = searchData.invalidSearch;
        searchPage.search(searchTerm);
        searchPage.getSearchTag(searchTerm).should("be.visible");
        searchPage.getNoJobsFoundMessage().should("be.visible");
    });

    it("should show a 'no jobs found' message for a search with special characters", () => {
        const { searchTerm } = searchData.specialCharSearch;
        searchPage.search(searchTerm);
        searchPage.getSearchTag(searchTerm).should("be.visible");
        searchPage.getNoJobsFoundMessage().should("be.visible");
    });

    it("should display correct tags when searching with location and job type filters", () => {
        const { searchTerm, location, jobType } = searchData.searchWithFilters;
        searchPage.search(searchTerm, location, jobType);
        searchPage.getSearchTag(searchTerm).should("be.visible");
        searchPage.getLocationTag(location).should("be.visible");
        searchPage.getJobTypeTag(jobType).should("be.visible");
    });
});