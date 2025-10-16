import EmployerPostJobPage from '../pageObjects/EmployerPostJobPage';
import EmployerLoginPage from '../pageObjects/EmployerLoginPage';
import * as employerLoginData from '../fixtures/employerLoginData.json';
import * as postAJobData from '../fixtures/postAJobData.json';

describe('Employer Post Job', () => {
  const employerPostJobPage = new EmployerPostJobPage();
  const employerLoginPage = new EmployerLoginPage();

  beforeEach(() => {
    cy.visit('/');
    employerLoginPage.navigateToEmployer();
    employerLoginPage.login(
      employerLoginData.validUsers[0].email,
      employerLoginData.validUsers[0].password
    );
  });

  it('should post a job successfully', () => {
    const job = postAJobData.validJob;
    employerPostJobPage.clickPostAJobNavigationButton();
    employerPostJobPage.enterJobTitle(job.title);
    employerPostJobPage.enterCompanyName(job.companyName);
    employerPostJobPage.enterDescription(job.description);
    employerPostJobPage.enterLocation(job.location);
    employerPostJobPojPage.selectJobTypeOption(job.jobType);
    employerPostJobPage.clickPostJobSubmitButton();
    employerPostJobPage.getJobPostSuccessMessage().should('be.visible');
  });

  it('should show an error when job title is blank', () => {
    const job = postAJobData.blankJob;
    employerPostJobPage.clickPostAJobNavigationButton();
    employerPostJobPage.enterJobTitle(job.title);
    employerPostJobPage.enterCompanyName(job.companyName);
    employerPostJobPage.enterDescription(job.description);
    employerPostJobPage.enterLocation(job.location);
    employerPostJobPage.selectJobTypeOption(job.jobType);
    employerPostJobPage.clickPostJobSubmitButton();
    employerPostJobPage.getJobTitleRequiredErrorMessage().should('be.visible');
  });
});