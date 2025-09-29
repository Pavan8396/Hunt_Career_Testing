import { BasePage } from "./BasePage";

export default class SearchPage extends BasePage {
    getSearchInputField() {
        return cy.xpath("//input[@placeholder='Search jobs by title, company, or keyword...']");
    }

    getSearchButton() {
        return cy.xpath("//button[@aria-label='Search']");
    }

    getLocationFilterButton() {
        return cy.xpath("//button[@aria-label='Toggle location filter dropdown']");
    }

    getLocationSearchInput() {
        return cy.xpath("//input[@placeholder='Search location...']");
    }

    getLocationOption(location) {
        return cy.get('label').contains(location).find('input');
    }

    getJobTypeFilterButton() {
        return cy.xpath("//button[@aria-label='Toggle job type filter dropdown']");
    }

    getJobTypeSearchInput() {
        return cy.xpath("//input[@placeholder='Search job type...']");
    }

    getJobTypeOption(jobType) {
        return cy.get('label').contains(jobType).find('input');
    }

    getClearAllFiltersButton() {
        return cy.xpath("//button[@aria-label='Clear all filters']");
    }

    getJobCardTitle(jobCard) {
        return jobCard.find('h3.text-lg.font-semibold');
    }

    getJobCardCompanyLocation(jobCard) {
        return jobCard.find('p.text-gray-600');
    }

    getJobCardDescription(jobCard) {
        return jobCard.find('p.text-sm.text-gray-700.mt-3.line-clamp-3');
    }

    getLoginSuccessMessage() {
        return cy.xpath("//div[contains(text(), 'Logged in successfully!')]");
    }

    getNoJobsFoundMessage() {
        return cy.get('p').contains('No jobs found.');
    }

    getSearchTag(searchTerm) {
        return cy.xpath(`//span[contains(., "🔍 ${searchTerm}")]`);
    }

    getLocationTag(location) {
        return cy.xpath(`//span[contains(., "📍 ${location}")]`);
    }

    getJobTypeTag(jobType) {
        return cy.xpath(`//span[contains(., "🧾 ${jobType}")]`);
    }

    enterSearchTerm(searchTerm) {
        this.typeInElement(this.getSearchInputField(), searchTerm);
    }

    clickSearchButton() {
        this.clickElement(this.getSearchButton());
    }

    openLocationDropdown() {
        this.clickElement(this.getLocationFilterButton());
    }

    searchLocation(location) {
        this.typeInElement(this.getLocationSearchInput(), location);
    }

    selectLocation(location) {
        this.clickElement(this.getLocationOption(location));
    }

    closeLocationDropdown() {
        this.clickElement(this.getLocationFilterButton());
    }

    openJobTypeDropdown() {
        this.clickElement(this.getJobTypeFilterButton());
    }

    selectJobType(jobType) {
        this.clickElement(this.getJobTypeOption(jobType));
    }

    closeJobTypeDropdown() {
        this.clickElement(this.getJobTypeFilterButton());
    }

    clickClearAllFilters() {
        this.clickElement(this.getClearAllFiltersButton());
    }

    navigate() {
        cy.visit("/");
        this.navigateToJobSeeker();
    }

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
}