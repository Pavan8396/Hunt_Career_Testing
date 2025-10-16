export class BasePage {
    waitUntilVisible(element){
        element.should('be.visible', {timeout: 10000});
    }
    waitUntilNotVisible(element){
        element.should('not.be.visible', {timeout: 10000})
    }
    clickElement(element){
        element.clickSafely();
    }

    typeInElement(element, text){ 
        element.typeTextSafely(text);
    }

    navigateTo(path) {
        cy.visit(path);
    }

    navigateToJobSeeker(){
        return cy.xpath("//h2[contains(text(), 'I am a Job Seeker')]").clickSafely();
    }
    navigateToEmployer(){
        return cy.xpath("//h2[contains(text(), 'I am an Employer')]").clickSafely();
    }
}