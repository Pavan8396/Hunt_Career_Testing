import { BasePage } from "./BasePage";

export class SearchPage extends BasePage {
  constructor(page) {
    super(page);
    this.page = page;

    // Elements
    this.searchInputField = page.locator(
      "//input[@placeholder='Search jobs by title, company, or keyword...']",
    );
    this.searchButton = page.locator("//button[@aria-label='Search']");
    this.locationButton = page.locator(
      "//button[@aria-label='Toggle location filter dropdown']",
    );
    this.jobTypeButton = page.locator(
      "//button[@aria-label='Toggle job type filter dropdown']",
    );
    this.noJobsFoundMessage = page.locator('//p[contains(text(),"No jobs found.")]');
    this.loginSuccessMessage = page.locator("//div[contains(@class, 'animate-in')]//p[contains(text(), 'Logged in successfully')]");
  }

  // Dynamic locators
  getSearchTag(searchTerm) {
    return this.page.locator(`//span[contains(text(),'${searchTerm}')]`);
  }

  getLocationTag(location) {
    return this.page.locator(`//span[contains(text(),'${location}')]`);
  }

  getJobTypeTag(jobType) {
    return this.page.locator(`//span[contains(text(),'${jobType}')]`);
  }

  // Actions
  async navigate() {
    await this.navigateToJobSeeker();
  }

  // Composite action
  async search(searchTerm, location = null, jobType = null) {
    await this.typeInElement(this.searchInputField, searchTerm);

    if (location) {
      await this.clickElement(this.locationButton);
      const locationOption = this.page.locator(`//label[contains(., "${location}")]//input`);
      await this.clickElement(locationOption);
      await this.clickElement(this.locationButton); // Close dropdown
    }

    if (jobType) {
      await this.clickElement(this.jobTypeButton);
      const jobTypeOption = this.page.locator(`//label[contains(., "${jobType}")]//input`);
      await this.clickElement(jobTypeOption);
    }

    await this.clickElement(this.searchButton);
  }
}