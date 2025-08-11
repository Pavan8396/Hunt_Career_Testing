import { BasePage } from "./BasePage";

export class HomePage extends BasePage {
  constructor(page) {
    super(page);
    this.viewDetailsLink = page.locator('//span[contains(text(), "View Details ")]');
    this.saveButton = page.locator('//button[contains(text(), "Save")]');
    this.saveConfirmationPopup = page.locator('//button[contains(text(), "Confirm")]');
  }
}
