import { BasePage } from "./BasePage";

export class HomePage extends BasePage {
    constructor(page) {
        super(page);
        this.viewDetailsLink = page.locator('//span[contains(text(), "View Details ")]');
    }
}