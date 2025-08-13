import { BasePage } from "./BasePage";

export class SavePage extends BasePage {
  constructor(page) {
    super(page);
    this.page = page;
  }
    savedJobCard(jobTitle) {
      return this.page.locator(`//h3[contains(text(), '${jobTitle}')]`);
    }
}
