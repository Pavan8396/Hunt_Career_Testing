import { BasePage } from "./BasePage";

export class ViewDetailsPage extends BasePage {
  constructor(page) {
    super(page);
    this.ApplyButton = page.locator(
      '//button[contains(text(), "Apply")]',
    );
  }
}