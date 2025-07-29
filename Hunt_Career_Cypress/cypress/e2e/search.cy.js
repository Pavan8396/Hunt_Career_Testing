import SearchPage from "../pageObjects/SearchPage";
import searchData from "../fixtures/searchData.json";

describe("Search Test", () => {

    let searchPage;

    beforeEach(() => {
        searchPage = new SearchPage();
        searchPage.navigation();
    });

    it("TC-1-Search with a valid keyword", () => {
        searchPage.search(searchData.validSearch.searchTerm);
        searchPage.SearchTag(searchData.validSearch.searchTerm).should('be.visible')
    })

    it("TC-2-Search with a invalid keyword", () => {
        searchPage.search(searchData.invalidSearch.searchTerm);
        searchPage.SearchTag(searchData.invalidSearch.searchTerm).should('be.visible')
        searchPage.NoJobsFoundMessage().should('be.visible');
    })

    it("TC-3-Search with Special Characters", () =>{
        searchPage.search(searchData.specialCharSearch.searchTerm);
        searchPage.SearchTag(searchData.specialCharSearch.searchTerm).should('be.visible')
        searchPage.NoJobsFoundMessage().should('be.visible');
    })

    it("TC-4-Seach with location and JobType", () =>{
        searchPage.search(searchData.searchWithFilters.searchTerm, searchData.searchWithFilters.location, searchData.searchWithFilters.jobType);
        searchPage.SearchTag(searchData.searchWithFilters.searchTerm).should('be.visible');
        searchPage.LocationTag(searchData.searchWithFilters.location).should('be.visible');
        searchPage.JobTypeTag(searchData.searchWithFilters.jobType).should('be.visible');
    })
})