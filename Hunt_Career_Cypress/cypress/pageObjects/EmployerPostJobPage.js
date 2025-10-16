import { BasePage } from "./BasePage";

export default class EmployerPostJobPage extends BasePage {
    getPostAJobNavigationButton() {
        return cy.xpath("//span[contains(text(), 'Post a Job')]");
    }

    getJobTitleField() {
        return cy.xpath("//input[@id='title']");
    }

    getCompanyTitleField() {
        return cy.xpath("//input[@id='company']");
    }

    getDescriptionField() {
        return cy.xpath("//textarea[@id='description']");
    }

    getLocationField() {
        return cy.xpath("//input[@id='candidate_required_location']");
    }

    getClickPostJobSubmitButton() {
        return cy.xpath("//button[contains(text(), 'Post Job')]");
    }

    getJobPostSuccessMessage() {
        return cy.xpath("//div[contains(text(), 'Job posted successfully!')]");
    }

    getJobTitleRequiredErrorMessage() {
        return cy.xpath("//div[contains(text(), 'Job title is required')]");
    }

    getCompanyNameRequiredErrorMessage() {
        return cy.xpath("//div[contains(text(), 'Company name is required')]");
    }

    getDescriptionRequiredErrorMessage() {
        return cy.xpath("//div[contains(text(), 'Description is required')]");
    }

    getLocationRequiredErrorMessage() {
        return cy.xpath("//div[contains(text(), 'Location is required')]");
    }

    clickPostAJobNavigationButton() {
        this.getPostAJobNavigationButton().clickSafely();
    }

    enterJobTitle(title) {
        this.typeInElement(this.getJobTitleField(), title);
    }

    enterCompanyName(companyName) {
        this.typeInElement(this.getCompanyTitleField(), companyName);
    }

    enterDescription(description) {
        this.typeInElement(this.getDescriptionField(), description);
    }

    enterLocation(location) {
        this.typeInElement(this.getLocationField(), location);
    }

    selectJobTypeOption(jobType) {
        cy.xpath(`//option[contains(text(),'${jobType}')]`).click();
    }

    clickPostJobSubmitButton() {
        this.getClickPostJobSubmitButton().clickSafely();
    }
}