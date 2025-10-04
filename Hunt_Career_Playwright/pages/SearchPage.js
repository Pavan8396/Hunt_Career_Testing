import { BasePage } from "./BasePage";

export class SearchPage extends BasePage {
  constructor(page) {
    super(page);
    this.page = page;

    // Elements
    this.searchInput = this.page.locator(
      "//input[@placeholder='Search jobs by title, company, or keyword...']",
    );
    this.searchButton = this.page.locator("//button[@aria-label='Search']");
    this.locationButton = this.page.locator(
      "//button[@aria-label='Toggle location filter dropdown']",
    );
    this.locationSearchInput = this.page.locator(
      "//input[@placeholder='Search location...']",
    );
    this.jobTypeButton = this.page.locator(
      "//button[@aria-label='Toggle job type filter dropdown']",
    );
    this.jobTypeSearchInput = this.page.locator(
      "//input[@placeholder='Search job type...']",
    );
    this.clearAllFiltersButton = this.page.locator(
      "//button[@aria-label='Clear all filters']",
    );
    this.loginSuccessMessage = this.page.locator(
      "//div[contains(text(), 'Logged in successfully!')]",
    );
    this.noJobsFoundMessage = this.page.locator(
      'p:has-text("No jobs found.")',
    );
  }

  // Dynamic locators
  searchTag(searchTerm) {
    return this.page.locator(`//span[contains(., "🔍 ${searchTerm}")]`);
  }

  locationTag(location) {
    return this.page.locator(`//span[contains(., "📍 ${location}")]`);
  }

  jobTypeTag(jobType) {
    return this.page.locator(`//span[contains(., "🧾 ${jobType}")]`);
  }

  locationOption(location) {
    return this.page.locator(`label:has-text("${location}")`);
  }

  jobTypeOption(jobType) {
    return this.page.locator(`label:has-text("${jobType}")`);
  }

  jobCardTitle(index) {
    return this.page
      .locator(
        "//h3[contains(@class, 'text-lg') and contains(@class, 'font-semibold')]",
      )
      .nth(index);
  }

  // Actions
  async navigateTo() {
    await this.navigateToJobSeeker();
  }

  async search(searchTerm, location = null, jobType = null) {
      await this.typeInElement(this.searchInput, searchTerm);
      await this.clickElement(this.searchButton);

    if (location) {
      await this.clickElement(this.locationButton);
      await this.typeInElement(this.locationSearchInput, location);
      await this.clickElement(this.locationOption(location));
      await this.clickElement(this.locationButton);
    }

    if (jobType) {
      await this.clickElement(this.jobTypeButton);
      await this.clickElement(this.jobTypeOption(jobType));
      await this.clickElement(this.jobTypeButton);
    }
  }
}