import { BasePage } from "./BasePage"

export class SearchPage extends BasePage {
  constructor(page) {
    super(page);
    this.page = page;

    // Elements
    this.searchInputField = page.getByPlaceholder('Search jobs by title, company, or keyword...');
    this.searchButton = page.getByRole('button', { name: 'Search' });
    this.locationButton = page.getByRole('button', { name: 'Toggle location filter dropdown' });
    this.locationSearchInput = page.getByPlaceholder('Search location...');
    this.jobTypeButton = page.getByRole('button', { name: 'Toggle job type filter dropdown' });
    this.jobTypeSearchInput = page.getByPlaceholder('Search job type...');
    this.clearAllFiltersButton = page.getByRole('button', { name: 'Clear all filters' });
    this.noJobsFoundMessage = page.getByText('No jobs found.');
  }

  // Dynamic locators
  searchTag(searchTerm) {
    return this.page.getByText(`🔍 ${searchTerm}`);
  }

  locationTag(location) {
    return this.page.getByText(`📍 ${location}`);
  }

  jobTypeTag(jobType) {
    return this.page.getByText(`🧾 ${jobType}`);
  }

  locationOption(location) {
    return this.page.getByLabel(location);
  }

  jobTypeOption(jobType) {
    return this.page.getByLabel(jobType);
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