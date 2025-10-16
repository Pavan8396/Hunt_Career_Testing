import { BasePage } from "./BasePage";

export default class SavePage extends BasePage {
    getSavedJobCard(jobTitle) {
        return cy.xpath(`//h3[contains(text(), "${jobTitle}")]`);
    }
}