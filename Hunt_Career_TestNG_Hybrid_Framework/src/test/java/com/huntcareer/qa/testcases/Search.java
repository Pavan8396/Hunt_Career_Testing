package com.huntcareer.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.SearchPage;

public class Search extends Base {
	WebDriver driver;
	SearchPage sp;
	LandingPage ldp;
	HomePage hp;
	
	public Search() {
		super();
	}

	@BeforeMethod
	public void setup() {
		// loadPropertiesFile();
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		// driver.get(loadPropertiesFile().getProperty("url")+"/signup");
		driver.get(prop.getProperty("url"));
		ldp = new LandingPage(driver);
		hp = ldp.clickOnJobSeekerRole();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_1_verifyValidSearch() {

		sp = new SearchPage(driver);
		sp.enterKeywordIntoSearchInputField(dataProp.getProperty("validSearchTerm"));
		sp.clickOnSearch();
		sp.getSearchTag("validSearchTerm");
	}
	
	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_2_verifyInvalidSearch() {
		sp = new SearchPage(driver);
		sp.enterKeywordIntoSearchInputField(dataProp.getProperty("invalidSearchTerm"));
		sp.clickOnSearch();
		sp.getSearchTag("invalidSearchTerm");
	}
	
	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_3_verifySearchWithSpecialCharcters() {
		sp = new SearchPage(driver);
		sp.enterKeywordIntoSearchInputField(dataProp.getProperty("specialCharctersSearchTerm"));
		sp.clickOnSearch();
		sp.getSearchTag("specialCharctersSearchTerm");
	}
	
	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_4_verifySearchWithFilters() {
		sp = new SearchPage(driver);
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