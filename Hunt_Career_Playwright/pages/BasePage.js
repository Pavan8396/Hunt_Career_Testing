export class BasePage {
  constructor(page) {
    this.page = page;
  }

  async waitUntilVisible(locator) {
    await locator.waitFor({ state: "visible" });
  }

  async waitUntilNotVisible(locator) {
    await locator.waitFor({ state: "hidden" });
  }

  async clickElement(locator) {
    await locator.click();
  }

  async typeInElement(locator, text) {
    await locator.fill(text);
  }

  async navigateToJobSeeker() {
    await this.page.goto("/");
    await this.page.locator("//h2[contains(text(), 'I am a Job Seeker')]").click();
  }

  async navigateToEmployer() {
    await this.page.goto("/");
    await this.page.locator("//h2[contains(text(), 'I am an Employer')]").click();
  }
}
