package com.huntcareer.qa.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.SavePage;
import com.huntcareer.qa.pages.SearchPage;
import com.huntcareer.qa.pages.ViewDetailsPage;

public class Search extends Base {
    SearchPage sp;
    LandingPage ldp;
    HomePage hp;
    LoginPage lp;
    ViewDetailsPage vdp;
    SavePage savePage;

    @BeforeMethod
    public void setup() {
        super.setup(prop.getProperty("browser"));
        ldp = new LandingPage(driver);
        hp = ldp.clickOnJobSeekerRole();
    }

    @Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_1_verifyValidSearch() {
        sp = new SearchPage(driver);
        sp.enterKeywordIntoSearchInputField(dataProp.getProperty("validSearchTerm"));
        sp.clickOnSearch();
        Assert.assertTrue(sp.getSearchTag(dataProp.getProperty("validSearchTerm")).isDisplayed());
    }

    @Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_2_verifyInvalidSearch() {
        sp = new SearchPage(driver);
        sp.enterKeywordIntoSearchInputField(dataProp.getProperty("invalidSearchTerm"));
        sp.clickOnSearch();
        Assert.assertTrue(sp.getSearchTag(dataProp.getProperty("invalidSearchTerm")).isDisplayed());
        Assert.assertTrue(sp.noJobsFoundMessage.isDisplayed());
    }

    @Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_3_verifySearchWithSpecialCharcters() {
        sp = new SearchPage(driver);
        sp.enterKeywordIntoSearchInputField(dataProp.getProperty("specialCharctersSearchTerm"));
        sp.clickOnSearch();
        Assert.assertTrue(sp.getSearchTag(dataProp.getProperty("specialCharctersSearchTerm")).isDisplayed());
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
        Assert.assertTrue(sp.getSearchTag(dataProp.getProperty("validSearchTerm")).isDisplayed());
        Assert.assertTrue(sp.getLocationTag(dataProp.getProperty("filterLocation")).isDisplayed());
        Assert.assertTrue(sp.getJobTypeTag(dataProp.getProperty("filterJobType")).isDisplayed());
    }

    @Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_5_Login_and_view_job_details() {
        lp = hp.clickOnLoginHomeLink();
        lp.enterEmail(dataProp.getProperty("validEmail"));
        lp.enterPassword(dataProp.getProperty("validPassword"));
        hp = lp.clickLoginButton();
        sp = new SearchPage(driver);
        sp.enterKeywordIntoSearchInputField(dataProp.getProperty("validSearchTerm"));
        sp.clickOnSearch();
        vdp = new ViewDetailsPage(driver);
        sp.clickElement(sp.getJobCardTitle(sp.driver.findElement(By.xpath("(//div[@class='p-4 border-t border-gray-200'])[1]"))));
        Assert.assertTrue(vdp.ApplyButton.isDisplayed());
    }
}