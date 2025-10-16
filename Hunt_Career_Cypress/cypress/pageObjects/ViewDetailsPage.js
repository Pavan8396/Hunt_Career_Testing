import { BasePage } from "./BasePage";

export default class ViewDetailsPage extends BasePage {
    getApplyButton() {
        return cy.xpath('//button[contains(text(), "Apply")]');
    }
}