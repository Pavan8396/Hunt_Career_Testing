import { BasePage } from "./basePage";

class SearchPage extends BasePage {

    SearchInputField() {
        return cy.xpath("//input[@placeholder='Search jobs by title, company, or keyword...']");
    }

    SearchButton() {
        return cy.xpath("//button[@aria-label='Search']");
    }

    LocationButton() {
        return cy.xpath("//button[@aria-label='Toggle location filter dropdown']")
    }

    LocationSearchInput() {
        return cy.xpath("//input[@placeholder='Search location...']")
    }

    LocationOption(location) {
        return cy.get('label').contains(location).find('input');
    }

    JobTypeButton() {
        return cy.xpath("//button[@aria-label='Toggle job type filter dropdown']");
    }

    JobTypeSearchInput() {
        return cy.xpath("//input[@placeholder='Search job type...']")
    }

    JobTypeOption(jobType) {
        return cy.get('label').contains(jobType).find('input');
    }

    ClearAllFiltersButton() {
        return cy.xpath("//button[@aria-label='Clear all filters']")
    }

    jobCardTitle(jobCard) {
        return jobCard.find('h3.text-lg.font-semibold');
    }

    jobCardCompanyLocation(jobCard) {
        return jobCard.find('p.text-gray-600');
    }

    jobCardDescription(jobCard) {
        return jobCard.find('p.text-sm.text-gray-700.mt-3.line-clamp-3');
    }

    NoJobsFoundMessage() {
        return cy.get('p').contains('No jobs found.');
    }

    SearchTag(searchTerm) {
        return cy.xpath(`//span[contains(., "🔍 ${searchTerm}")]`);
    }

    LocationTag(location) {
        return cy.xpath(`//span[contains(., "📍 ${location}")]`);
    }

    JobTypeTag(jobType) {
        return cy.xpath(`//span[contains(., "🧾 ${jobType}")]`);
    }

    // Messages
    checkNoJobsFoundMessage() {
        return this.NoJobsFoundMessage();
    }

    // Actions
    enterSearchTerm(searchTerm) {
        this.typeInElement(this.SearchInputField(), searchTerm);
    }

    clickSearchButton() {
        this.clickElement(this.SearchButton());
    }

    openLocationDropdown() {
        this.clickElement(this.LocationButton());
    }

    searchLocation(location) {
        this.typeInElement(this.LocationSearchInput(), location);
    }

    selectLocation(location) {
        this.clickElement(this.LocationOption(location));
    }

    closeLocationDropdown() {
        this.clickElement(this.LocationButton());
    }

    openJobTypeDropdown() {
        this.clickElement(this.JobTypeButton());
    }

    selectJobType(jobType) {
        this.clickElement(this.JobTypeOption(jobType));
    }

    closeJobTypeDropdown() {
        this.clickElement(this.JobTypeButton());
    }

    clickClearAllFilters() {
        this.clickElement(this.ClearAllFiltersButton());
    }

    // Main search action
    search(searchTerm, location = null, jobType = null) {
        this.enterSearchTerm(searchTerm);
        this.clickSearchButton();

        if (location) {
            this.openLocationDropdown();
            this.searchLocation(location);
            this.selectLocation(location);
            this.closeLocationDropdown();
        }

        if (jobType) {
            this.openJobTypeDropdown();
            this.selectJobType(jobType);
            this.closeJobTypeDropdown();
        }
    }

    // Validation methods (same pattern as LoginPage)
    validationMessage(element) {
        this.waitUntilVisible(element);
    }

    validationMessageNotExist(element) {
        this.waitUntilNotVisible(element);
    }
}

export const searchPage = new SearchPage();