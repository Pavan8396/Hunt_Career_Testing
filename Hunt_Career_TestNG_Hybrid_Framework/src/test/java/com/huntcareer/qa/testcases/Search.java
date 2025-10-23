package com.huntcareer.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.SearchPage;

public class Search extends Base {
	SearchPage sp;
	LandingPage ldp;
	HomePage hp;

	public Search() {
		super();
	}

	@BeforeMethod
	public void setupSearch() {
		ldp = new LandingPage(driver);
		hp = ldp.clickOnJobSeekerRole();
		sp = new SearchPage(driver);
	}

	@Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSS_1_verifyValidSearch() {
		sp.enterKeywordIntoSearchInputField(dataProp.getProperty("validSearchTerm"));
		sp.clickOnSearch();
		sp.getSearchTag("validSearchTerm");
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSS_2_verifyInvalidSearch() {
		sp.enterKeywordIntoSearchInputField(dataProp.getProperty("invalidSearchTerm"));
		sp.clickOnSearch();
		sp.getSearchTag("invalidSearchTerm");
	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSS_3_verifySearchWithSpecialCharcters() {
		sp.enterKeywordIntoSearchInputField(dataProp.getProperty("specialCharctersSearchTerm"));
		sp.clickOnSearch();
		sp.getSearchTag("specialCharctersSearchTerm");
	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSS_4_verifySearchWithFilters() {
		sp.enterKeywordIntoSearchInputField(dataProp.getProperty("validSearchTerm"));
		sp.clickLocationButton();
		sp.selectLocationOption(dataProp.getProperty("filterLocation"));
		sp.clickLocationButton();
		sp.clickJobTypeButton();
		sp.selectJobTypeOption(dataProp.getProperty("filterJobType"));
		sp.getSearchTag("validSearchTerm");
		sp.getLocationTag("filterLocation");
		sp.getJobTypeTag("filterJobType");
	}
}
