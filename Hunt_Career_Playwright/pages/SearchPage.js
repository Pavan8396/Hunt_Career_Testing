import { BasePage } from "./BasePage"

class SearchPage extends BasePage {
  constructor(page) {
    super(page);
    this.page = page;

    // Elements
    this.searchInputField = page.locator("//input[@placeholder='Search jobs by title, company, or keyword...']");
    this.searchButton = page.locator("//button[@aria-label='Search']");
    this.locationButton = page.locator("//button[@aria-label='Toggle location filter dropdown']");
    this.locationSearchInput = page.locator("//input[@placeholder='Search location...']");
    this.jobTypeButton = page.locator("//button[@aria-label='Toggle job type filter dropdown']");
    this.jobTypeSearchInput = page.locator("//input[@placeholder='Search job type...']");
    this.clearAllFiltersButton = page.locator("//button[@aria-label='Clear all filters']");
    this.noJobsFoundMessage = page.locator('p:has-text("No jobs found.")');
  }

  // Dynamic locators
  searchTag(searchTerm) {
    return this.page.locator(`//span[contains(., "🔍 ${searchTerm}")]`);
  }

  locationTag(location) {
    return this.page.locator(`//div[contains(text(), "📍 ${location}")]`);
  }

  jobTypeTag(jobType) {
    return this.page.locator(`//div[contains(text(), "🧾 ${jobType}")]`);
  }

  locationOption(location) {
    return this.page.locator(`//label[contains(., "${location}")]//input`);
  }

  jobTypeOption(jobType) {
    return this.page.locator(`//label[contains(., "${jobType}")]//input`);
  }

  jobCardTitle(jobCard) {
    return jobCard.locator('h3.text-lg.font-semibold');
  }

  jobCardCompanyLocation(jobCard) {
    return jobCard.locator('p.text-gray-600');
  }

  jobCardDescription(jobCard) {
    return jobCard.locator('p.text-sm.text-gray-700.mt-3.line-clamp-3');
  }

  // Actions
  async enterSearchTerm(searchTerm) {
    await this.searchInputField.fill(searchTerm);
  }

  async clickSearchButton() {
    await this.searchButton.click();
  }

  async openLocationDropdown() {
    await this.locationButton.click();
  }

  async searchLocation(location) {
    await this.locationSearchInput.fill(location);
  }

  async selectLocation(location) {
    await this.locationOption(location).click();
  }

  async closeLocationDropdown() {
    await this.locationButton.click();
  }

  async openJobTypeDropdown() {
    await this.jobTypeButton.click();
  }

  async selectJobType(jobType) {
    await this.jobTypeOption(jobType).click();
  }

  async closeJobTypeDropdown() {
    await this.jobTypeButton.click();
  }

  async clickClearAllFilters() {
    await this.clearAllFiltersButton.click();
  }

  // Composite action
  async search(searchTerm, location = null, jobType = null) {
    await this.enterSearchTerm(searchTerm);
    await this.clickSearchButton();

    if (location) {
      await this.openLocationDropdown();
      await this.searchLocation(location);
      await this.selectLocation(location);
      await this.closeLocationDropdown();
    }

    if (jobType) {
      await this.openJobTypeDropdown();
      await this.selectJobType(jobType);
      await this.closeJobTypeDropdown();
    }
  }
}
module.exports = { SearchPage };