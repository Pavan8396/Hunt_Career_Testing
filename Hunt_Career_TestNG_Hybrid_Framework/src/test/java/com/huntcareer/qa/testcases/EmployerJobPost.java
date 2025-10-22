package com.huntcareer.qa.testcases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerPostJobPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.testdata.PostAJob;

public class EmployerJobPost extends Base {
	WebDriver driver;
	EmployerPostJobPage jp;
	LandingPage ldp;
	EmployerLoginPage elp;

	public EmployerJobPost() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		ldp = new LandingPage(driver);
		ldp.clickOnEmployerRole();
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_1_Create_Job_Post_With_Valid_Data() {
		jp = new EmployerPostJobPage(driver);
		Map<String, String> job = PostAJob.validJob();
		jp.clickPostAJobNavigationButton();
		jp.enterJobTitle(job.get("title"));
		jp.enterCompanyName(job.get("companyName"));
		jp.enterDescription(job.get("description"));
		jp.enterLocation(job.get("location"));
		jp.selectJobTypeOption();
		jp.clickPostJobSubmitButton();
		jp.verifyJobPostSuccess();
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_2_Blank_Job_Post() {
		jp = new EmployerPostJobPage(driver);
		Map<String, String> job = PostAJob.blankJob();
		jp.clickPostAJobNavigationButton();
		jp.enterJobTitle(job.get("title"));
		jp.enterCompanyName(job.get("companyName"));
		jp.enterDescription(job.get("description"));
		jp.enterLocation(job.get("location"));
		jp.selectJobTypeOption();
		jp.clickPostJobSubmitButton();
		jp.verifyJobTitleRequiredErrorMessage();
	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_3_No_Title_Job_Post() {
		jp = new EmployerPostJobPage(driver);
		Map<String, String> job = PostAJob.noTitle();
		jp.clickPostAJobNavigationButton();
		jp.enterJobTitle(job.get("title"));
		jp.enterCompanyName(job.get("companyName"));
		jp.enterDescription(job.get("description"));
		jp.enterLocation(job.get("location"));
		jp.selectJobTypeOption();
		jp.clickPostJobSubmitButton();
		jp.verifyJobTitleRequiredErrorMessage();
	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_4_No_Company_Name_Job_Post() {
		jp = new EmployerPostJobPage(driver);
		Map<String, String> job = PostAJob.noCompanyName();
		jp.clickPostAJobNavigationButton();
		jp.enterJobTitle(job.get("title"));
		jp.enterCompanyName(job.get("companyName"));
		jp.enterDescription(job.get("description"));
		jp.enterLocation(job.get("location"));
		jp.selectJobTypeOption();
		jp.clickPostJobSubmitButton();
		jp.verifyCompanyNameRequiredErrorMessage();
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_5_No_Description_Job_Post() {
		jp = new EmployerPostJobPage(driver);
		Map<String, String> job = PostAJob.noDescription();
		jp.clickPostAJobNavigationButton();
		jp.enterJobTitle(job.get("title"));
		jp.enterCompanyName(job.get("companyName"));
		jp.enterDescription(job.get("description"));
		jp.enterLocation(job.get("location"));
		jp.selectJobTypeOption();
		jp.clickPostJobSubmitButton();
		jp.verifyDescriptionRequiredErrorMessage();
	}

	@Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_6_No_Job_Location_Job_Post() {
		jp = new EmployerPostJobPage(driver);
		Map<String, String> job = PostAJob.noLocation();
		jp.clickPostAJobNavigationButton();
		jp.enterJobTitle(job.get("title"));
		jp.enterCompanyName(job.get("companyName"));
		jp.enterDescription(job.get("description"));
		jp.enterLocation(job.get("location"));
		jp.selectJobTypeOption();
		jp.clickPostJobSubmitButton();
		jp.verifyLocationRequiredErrorMessage();
	}

	@Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_7_Duplicate_Job_Post() {
		jp = new EmployerPostJobPage(driver);
		for (int i = 1; i <= 2; i++) {
			Map<String, String> job = PostAJob.duplicateJobPost();
			jp.clickPostAJobNavigationButton();
			jp.enterJobTitle(job.get("title"));
			jp.enterCompanyName(job.get("companyName"));
			jp.enterDescription(job.get("description"));
			jp.enterLocation(job.get("location"));
			jp.selectJobTypeOption();
			jp.clickPostJobSubmitButton();
			jp.verifyJobExistsErrorMessage();
		}
	}
}