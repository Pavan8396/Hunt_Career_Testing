package com.huntcareer.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.SearchPage;
import com.huntcareer.qa.testdata.SearchData;

public class Search extends TestBase {

    SearchPage searchPage;

    public Search() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        searchPage = new SearchPage(driver);
    }

    @Test(dataProvider = "validSearch", dataProviderClass = SearchData.class)
    public void testValidKeywordSearch(String searchTerm) {
        searchPage.search(searchTerm);
        searchPage.verifySearchTag(searchTerm);
    }

    @Test(dataProvider = "invalidSearch", dataProviderClass = SearchData.class)
    public void testInvalidKeywordSearch(String searchTerm) {
        searchPage.search(searchTerm);
        searchPage.verifySearchTag(searchTerm);
        searchPage.verifyNoJobsFoundMessage();
    }

    @Test(dataProvider = "specialCharSearch", dataProviderClass = SearchData.class)
    public void testSpecialCharacterSearch(String searchTerm) {
        searchPage.search(searchTerm);
        searchPage.verifySearchTag(searchTerm);
        searchPage.verifyNoJobsFoundMessage();
    }

    @Test(dataProvider = "searchWithFilters", dataProviderClass = SearchData.class)
    public void testSearchWithFilters(String searchTerm, String location, String jobType) {
        searchPage.search(searchTerm, location, jobType);
        searchPage.verifySearchTag(searchTerm);
        searchPage.verifyLocationTag(location);
        searchPage.verifyJobTypeTag(jobType);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}