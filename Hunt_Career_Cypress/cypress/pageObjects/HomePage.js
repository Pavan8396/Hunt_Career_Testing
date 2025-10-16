import { BasePage } from "./BasePage";

export default class HomePage extends BasePage {
    getViewDetailsLink() {
        return cy.xpath('//span[contains(text(), "View Details ")]');
    }

    getSaveButton() {
        return cy.xpath('//button[contains(text(), "Save")]');
    }

    getSaveConfirmationPopup() {
        return cy.xpath('//button[contains(text(), "Confirm")]');
    }

    getOpenUserMenu() {
        return cy.xpath('//button[@aria-label = "Open user menu"]');
    }

    getClickOnSavedJobs() {
        return cy.xpath('//span[contains(text(), "Saved Jobs")]');
    }

    getSavedMessage(jobTitle) {
        return cy.xpath(`//div[contains(text(), 'Job "${jobTitle}" saved')]`);
    }

    goToSavedJobs() {
        this.getOpenUserMenu().clickSafely();
        this.getClickOnSavedJobs().clickSafely();
    }
}