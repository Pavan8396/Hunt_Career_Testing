import { BasePage } from "./BasePage";

export class EmployerPostJobPage extends BasePage {
  constructor(page) {
    super(page);
    this.postAJobNavigationButton = page.locator("//span[contains(text(), 'Post a Job')]");
    this.jobTitleField = page.locator("//input[@id='title']");
    this.companyTitleField = page.locator("//input[@id='company']");
    this.descriptionField = page.locator("//textarea[@id='description']");
    this.locationField = page.locator("//input[@id='candidate_required_location']");
    this.clickPostJobSubmitButton = page.locator("//button[contains(text(), 'Post Job')]");
    this.jobPostSuccessMessage = page.locator("//div[contains(text(), 'Job posted successfully!')]");
    this.jobTitleRequiredErrorMessage = page.locator("//div[contains(text(), 'Job title is required')]");
    this.companyNameRequiredErrorMessage = page.locator("//div[contains(text(), 'Company name is required')]");
    this.descriptionRequiredErrorMessage = page.locator("//div[contains(text(), 'Description is required')]");
    this.locationRequiredErrorMessage = page.locator("//div[contains(text(), 'Location is required')]");
  }

  async clickPostAJobNavigationButton() {
    await this.clickElement(this.postAJobNavigationButton);
  }

  async enterJobTitle(title) {
    await this.typeInElement(this.jobTitleField, title);
  }

  async enterCompanyName(companyName) {
    await this.typeInElement(this.companyTitleField, companyName);
  }

  async enterDescription(description) {
    await this.typeInElement(this.descriptionField, description);
  }

  async enterLocation(location) {
    await this.typeInElement(this.locationField, location);
  }

  async selectJobTypeOption(jobType) {
    const jobTypeOption = this.page.locator(`//option[contains(text(),'${jobType}')]`);
    await this.clickElement(jobTypeOption);
  }

  async clickPostJobSubmitButton() {
    await this.clickElementAndWaitForLoad(this.clickPostJobSubmitButton);
  }
}