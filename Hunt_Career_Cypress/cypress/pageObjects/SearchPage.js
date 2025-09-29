import { BasePage } from "./BasePage";

export default class SearchPage extends BasePage {
    getSearchInputField() {
        return cy.get("[data-cy=search-input]");
    }

    getSearchButton() {
        return cy.get("[data-cy=search-button]");
    }

    getLocationFilterButton() {
        return cy.get("[data-cy=location-filter-button]");
    }

    getLocationSearchInput() {
        return cy.get("[data-cy=location-search-input]");
    }

    getLocationOption(location) {
        return cy.get("[data-cy=location-option]").contains(location);
    }

    getJobTypeFilterButton() {
        return cy.get("[data-cy=job-type-filter-button]");
    }

    getJobTypeSearchInput() {
        return cy.get("[data-cy=job-type-search-input]");
    }

    getJobTypeOption(jobType) {
        return cy.get("[data-cy=job-type-option]").contains(jobType);
    }

    getClearAllFiltersButton() {
        return cy.get("[data-cy=clear-filters-button]");
    }

    getJobCardTitle(jobCard) {
        return jobCard.find("[data-cy=job-card-title]");
    }

    getJobCardCompanyLocation(jobCard) {
        return jobCard.find("[data-cy=job-card-company-location]");
    }

    getJobCardDescription(jobCard) {
        return jobCard.find("[data-cy=job-card-description]");
    }

    getNoJobsFoundMessage() {
        return cy.contains("No jobs found.");
    }

    getSearchTag(searchTerm) {
        return cy.contains(`🔍 ${searchTerm}`);
    }

    getLocationTag(location) {
        return cy.contains(`📍 ${location}`);
    }

    getJobTypeTag(jobType) {
        return cy.contains(`🧾 ${jobType}`);
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