package com.huntcareer.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.SearchPage;
import com.huntcareer.qa.utils.JsonUtils;

public class Search extends TestBase {
    SearchPage searchPage;
    LandingPage landingPage;

    @BeforeMethod
    public void setup() {
        driver = initialization();
        searchPage = new SearchPage(driver);
    }

    @DataProvider(name = "validSearch")
    public Object[][] getValidSearch() {
        return JsonUtils.getTestData("src/test/resource/fixtures/searchData.json", "validSearch", "searchTerm");
    }

    @DataProvider(name = "invalidSearch")
    public Object[][] getInvalidSearch() {
        return JsonUtils.getTestData("src/test/resource/fixtures/searchData.json", "invalidSearch", "searchTerm");
    }

    @DataProvider(name = "specialCharSearch")
    public Object[][] getSpecialCharSearch() {
        return JsonUtils.getTestData("src/test/resource/fixtures/searchData.json", "specialCharSearch", "searchTerm");
    }

    @DataProvider(name = "searchWithFilters")
    public Object[][] getSearchWithFilters() {
        return JsonUtils.getTestData("src/test/resource/fixtures/searchData.json", "searchWithFilters", "searchTerm", "location", "jobType");
    }

    @Test(dataProvider = "validSearch")
    public void testValidKeywordSearch(String searchTerm) {
        searchPage.search(searchTerm);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.getSearchTag(searchTerm).isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidSearch")
    public void testInvalidKeywordSearch(String searchTerm) {
        searchPage.search(searchTerm);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.getSearchTag(searchTerm).isDisplayed());
        softAssert.assertTrue(searchPage.getNoJobsFoundMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "specialCharSearch")
    public void testSpecialCharacterSearch(String searchTerm) {
        searchPage.search(searchTerm);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.getSearchTag(searchTerm).isDisplayed());
        softAssert.assertTrue(searchPage.getNoJobsFoundMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "searchWithFilters")
    public void testSearchWithFilters(String searchTerm, String location, String jobType) {
        searchPage.search(searchTerm, location, jobType);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.getSearchTag(searchTerm).isDisplayed());
        softAssert.assertTrue(searchPage.getLocationTag(location).isDisplayed());
        softAssert.assertTrue(searchPage.getJobTypeTag(jobType).isDisplayed());
        softAssert.assertAll();
    }
}