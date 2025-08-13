import { BasePage } from "./BasePage";

export class HomePage extends BasePage {
  constructor(page) {
    super(page);
    this.viewDetailsLink = page.locator('//span[contains(text(), "View Details ")]');
    this.saveConfirmationPopup = page.locator('//button[contains(text(), "Confirm")]');
    this.openUserMenu = page.locator('//button[@aria-label = "Open user menu"]');
    this.clickOnSavedJobs = page.locator('//a[contains(text(), "Saved Jobs")]');
    this.successMessage = page.locator("//div[contains(text(), 'Logged in successfully!')]");
  }
    savedMessage(jobTitle) {
      return this.page.locator(`//div[contains(text(), 'Job "${jobTitle}" saved')]`);
    }
}