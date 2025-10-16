import SearchPage from '../pageObjects/SearchPage';
import LoginPage from '../pageObjects/LoginPage';
import HomePage from '../pageObjects/HomePage';
import ViewDetailsPage from '../pageObjects/ViewDetailsPage';
import SavePage from '../pageObjects/SavePage';
import * as searchData from '../fixtures/searchData.json';
import * as loginData from '../fixtures/loginData.json';

describe('Search Tests', () => {
  const searchPage = new SearchPage();
  const loginPage = new LoginPage();
  const homePage = new HomePage();
  const viewDetailsPage = new ViewDetailsPage();
  const savePage = new SavePage();

  beforeEach(() => {
    cy.visit('/');
    searchPage.navigateToJobSeeker();
  });

  it('should return results for a valid keyword search', () => {
    const { searchTerm, location, jobType } = searchData.validSearch;
    searchPage.search(searchTerm, location, jobType);
    searchPage.getSearchTag(searchTerm).should('be.visible');
  });

  it("should show a 'no jobs found' message for an invalid keyword", () => {
    const { searchTerm, location, jobType } = searchData.invalidSearch;
    searchPage.search(searchTerm, location, jobType);
    searchPage.getSearchTag(searchTerm).should('be.visible');
    searchPage.getNoJobsFoundMessage().should('be.visible');
  });

  it("should show a 'no jobs found' message for a search with special characters", () => {
    const { searchTerm, location, jobType } = searchData.specialCharSearch;
    searchPage.search(searchTerm, location, jobType);
    searchPage.getSearchTag(searchTerm).should('be.visible');
  });

  it('should display correct tags when searching with location and job type filters', () => {
    const { searchTerm, location, jobType } = searchData.searchWithFilters;
    searchPage.search(searchTerm, location, jobType);
    searchPage.getSearchTag(searchTerm).should('be.visible');
    searchPage.getLocationTag(location).should('be.visible');
    searchPage.getJobTypeTag(jobType).should('be.visible');
  });

  it('should allow a logged-in user to view job details from search results', () => {
    loginPage.getLoginHomeLink().click();
    loginPage.login(loginData.validUser.email, loginData.validUser.password);
    const { searchTerm, location, jobType } = searchData.validSearch;
    searchPage.search(searchTerm, location, jobType);
    homePage.getViewDetailsLink().first().click();
    viewDetailsPage.getApplyButton().should('be.visible');
  });

  it('should allow a logged-in user to save a job and see it in the saved jobs page', () => {
    loginPage.getLoginHomeLink().click();
    loginPage.login(loginData.validUser.email, loginData.validUser.password);
    const { searchTerm, location, jobType } = searchData.validSearch;
    searchPage.search(searchTerm, location, jobType);
    const jobIndex = 1;
    searchPage.getJobCardTitle().eq(jobIndex).invoke('text').then((jobTitle) => {
      homePage.getSaveButton().eq(jobIndex).click();
      homePage.getSaveConfirmationPopup().click();
      homePage.getSavedMessage(jobTitle).should('be.visible');
      homePage.getSavedMessage(jobTitle).should('not.exist');
      homePage.goToSavedJobs();
      savePage.getSavedJobCard(jobTitle).should('be.visible');
    });
  });
});