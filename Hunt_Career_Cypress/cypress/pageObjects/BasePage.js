export class BasePage {
    waitUntilVisible(element){
        element.should('be.visible', {timeout: 10000});
    }
    waitUntilNotVisible(element){
        element.should('not.be.visible', {timeout: 10000})
    }
    clickElement(element){
        element.should('be.visible').click();
    }
    typeInElement(element, text){
        element.should('be.visible').type(text)
    }
    navigateToJobSeeker(){
        return cy.xpath("//h2[contains(text(), 'I am a Job Seeker')]").click();
    }
    navigateToEmployer(){
        return cy.xpath("//h2[contains(text(), 'I am a Employer')]").click();
    }
}