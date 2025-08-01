import { BasePage } from "./BasePage";

export class HomePage extends BasePage {
  constructor(page) {
    super(page);
    this.viewDetailsLink = page.getByRole("link", { name: "View Details" });
  }
}
