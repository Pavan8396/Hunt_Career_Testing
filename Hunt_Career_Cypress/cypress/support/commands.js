// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

import 'cypress-xpath';
import {loginPage} from '../pageObjects/LoginPage'
import {registerPage} from '../pageObjects/RegisterPage'
import {searchPage} from '../pageObjects/SearchPage';

//Login 
Cypress.Commands.add('login', (email, password) =>{
    cy.visit('/login');
    loginPage.login(email, password)
});


//Signup
Cypress.Commands.add('registerFull', (firstName, lastName, email, password, confirmPassword, phoneNumber) =>{
    cy.visit('/signup');
    registerPage.register(firstName, lastName, email, password, confirmPassword, phoneNumber)
});

Cypress.Commands.add('registerFirstPage', (firstName, lastName) =>{
    cy.visit('/signup');
    registerPage.registerFirstPage(firstName, lastName)
});

Cypress.Commands.add('registerSecondPage', (firstName, lastName, email, password, confirmPassword) =>{
    cy.visit('/signup');
    registerPage.registerSecondPage(firstName, lastName, email, password, confirmPassword)
});

// Search
Cypress.Commands.add('search', (searchTerm, location, jobType) => {
    cy.visit('/');
    searchPage.search(searchTerm, location, jobType);
});