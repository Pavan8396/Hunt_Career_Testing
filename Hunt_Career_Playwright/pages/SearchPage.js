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
    await this.searchInput.fill(searchTerm);
    await this.searchButton.click();

    if (location) {
      await this.locationButton.click();
      await this.locationSearchInput.fill(location);
      await this.locationOption(location).click();
      await this.locationButton.click();
    }

    if (jobType) {
      await this.jobTypeButton.click();
      await this.jobTypeOption(jobType).click();
      await this.jobTypeButton.click();
    }
  }
}