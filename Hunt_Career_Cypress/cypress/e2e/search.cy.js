import SearchPage from "../pageObjects/SearchPage";
import searchData from "../fixtures/searchData.json";

describe("Search Test", () => {

    let searchPage;

    beforeEach(() => {
        searchPage = new SearchPage();
    });

    it("TC-1-Search with a valid keyword", () => {
        const searchTerm = searchData.validSearch.searchTerm;
        cy.visit('/');
        searchPage.search(searchTerm);
        searchPage.SearchTag(searchTerm).should('be.visible')
    })

    it("TC-2-Search with a invalid keyword", () => {
        const searchTerm = searchData.invalidSearch.searchTerm;
        cy.visit('/');
        searchPage.search(searchTerm);
        searchPage.SearchTag(searchTerm).should('be.visible')
        searchPage.NoJobsFoundMessage().should('be.visible');
    })

    it("TC-3-Search with Special Characters", () =>{
        const searchTerm = searchData.specialCharSearch.searchTerm;
        cy.visit('/');
        searchPage.search(searchTerm);
        searchPage.SearchTag(searchTerm).should('be.visible')
        searchPage.NoJobsFoundMessage().should('be.visible');
    })

    it("TC-4-Seach with location and JobType", () =>{
        const searchTerm = searchData.searchWithFilters.searchTerm
        const locationTerm = searchData.searchWithFilters.location
        const jobTypeTerm = searchData.searchWithFilters.jobType
        cy.visit('/');
        searchPage.search(searchTerm, locationTerm, jobTypeTerm);
        searchPage.SearchTag(searchTerm).should('be.visible');
        searchPage.LocationTag(locationTerm).should('be.visible');
        searchPage.JobTypeTag(jobTypeTerm).should('be.visible');
    })
})