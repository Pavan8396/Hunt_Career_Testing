import { test, expect } from "@playwright/test";
import { EmployerPostJobPage } from "../pages/EmployerPostJobPage.js";
import { EmployerLoginPage } from "../pages/EmployerLoginPage.js";
import employerLoginData from "../data/employerLoginData.json";
import { postAJobData } from "../data/postAJobData.js";

test.describe("Employer Post Job", () => {
  let employerPostJobPage;
  let employerLoginPage;

  test.beforeEach(async ({ page }) => {
    employerLoginPage = new EmployerLoginPage(page);
    employerPostJobPage = new EmployerPostJobPage(page);
    await employerLoginPage.navigate();
    await employerLoginPage.login(
      employerLoginData.validUsers[0].email,
      employerLoginData.validUsers[0].password
    );
  });

  test("should post a job successfully", async () => {
    const job = postAJobData.validJob();
    await employerPostJobPage.clickPostAJobNavigationButton();
    await employerPostJobPage.enterJobTitle(job.title);
    await employerPostJobPage.enterCompanyName(job.companyName);
    await employerPostJobPage.enterDescription(job.description);
    await employerPostJobPage.enterLocation(job.location);
    await employerPostJobPage.selectJobTypeOption(job.jobType);
    await employerPostJobPage.clickPostJobSubmitButton();
    await expect(employerPostJobPage.jobPostSuccessMessage).toBeVisible();
  });

  test("should show an error when job title is blank", async () => {
    const job = postAJobData.blankJob();
    await employerPostJobPage.clickPostAJobNavigationButton();
    await employerPostJobPage.enterJobTitle(job.title);
    await employerPostJobPage.enterCompanyName(job.companyName);
    await employerPostJobPage.enterDescription(job.description);
    await employerPostJobPage.enterLocation(job.location);
    await employerPostJobPage.selectJobTypeOption(job.jobType);
    await employerPostJobPage.clickPostJobSubmitButton();
    await expect(employerPostJobPage.jobTitleRequiredErrorMessage).toBeVisible();
  });
});