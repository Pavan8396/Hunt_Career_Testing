import { BasePage } from "./BasePage";

export class SearchPage extends BasePage {
  constructor(page) {
    super(page);
    this.page = page;

    // Elements
    this.getSearchInputField = page.locator(
      "//input[@placeholder='Search jobs by title, company, or keyword...']",
    );
    this.getSearchButton = page.locator("//button[@aria-label='Search']");
    this.getLocationFilterButton = page.locator(
      "//button[@aria-label='Toggle location filter dropdown']",
    );
    this.getLocationSearchInput = page.locator(
      "//input[@placeholder='Search location...']",
    );
    this.getJobTypeFilterButton = page.locator(
      "//button[@aria-label='Toggle job type filter dropdown']",
    );
    this.getJobTypeSearchInput = page.locator(
      "//input[@placeholder='Search job type...']",
    );
    this.getClearAllFiltersButton = page.locator(
      "//button[@aria-label='Clear all filters']",
    );
    this.getLoginSuccessMessage = page.locator("//div[contains(text(), 'Logged in successfully!')]");
    this.getNoJobsFoundMessage = page.locator('p:has-text("No jobs found.")');
  }

  // Dynamic locators
  getSearchTag(searchTerm) {
    return this.page.locator(`//span[contains(., "🔍 ${searchTerm}")]`);
  }

  getLocationTag(location) {
    return this.page.locator(`//span[contains(., "📍 ${location}")]`);
  }

  getJobTypeTag(jobType) {
    return this.page.locator(`//span[contains(., "🧾 ${jobType}")]`);
  }

  getLocationOption(location) {
    return this.page.locator(`//label[contains(., "${location}")]//input`);
  }

  getJobTypeOption(jobType) {
    return this.page.locator(`//label[contains(., "${jobType}")]//input`);
  }

  getJobCardTitle(index) {
    return this.page
      .locator(
        "//h3[contains(@class, 'text-lg') and contains(@class, 'font-semibold')]",
      )
      .nth(index);
  }

  getJobCardCompanyLocation(jobCard) {
    return jobCard.locator("p.text-gray-600");
  }

  getJobCardDescription(jobCard) {
    return jobCard.locator("p.text-sm.text-gray-700.mt-3.line-clamp-3");
  }

  // Actions
  async enterSearchTerm(searchTerm) {
    await this.getSearchInputField.fill(searchTerm);
  }

  async clickSearchButton() {
    await this.getSearchButton.click();
  }

  async openLocationDropdown() {
    await this.getLocationFilterButton.click();
  }

  async searchLocation(location) {
    await this.getLocationSearchInput.fill(location);
  }

  async selectLocation(location) {
    await this.locationOption(location).click();
  }

  async closeLocationDropdown() {
    await this.getLocationFilterButton.click();
  }

  async openJobTypeDropdown() {
    await this.getJobTypeFilterButton.click();
  }

  async selectJobType(jobType) {
    await this.jobTypeOption(jobType).click();
  }

  async closeJobTypeDropdown() {
    await this.getJobTypeFilterButton.click();
  }

  async clickClearAllFilters() {
    await this.getClearAllFiltersButton.click();
  }

  // Actions
  async navigate() {
    await this.navigateToJobSeeker();
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