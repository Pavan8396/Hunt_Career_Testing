export class BasePage {
  constructor(page) {
    this.page = page;
  }

  async waitUntilVisible(locator) {
    await locator.waitFor({ state: 'visible' });
  }

  async waitUntilNotVisible(locator) {
    await locator.waitFor({ state: 'hidden' });
  }

  async clickElement(locator) {
    await locator.click();
  }

  async typeInElement(locator, text) {
    await locator.fill(text);
  }
}
