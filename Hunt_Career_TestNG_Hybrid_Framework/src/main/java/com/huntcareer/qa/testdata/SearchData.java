package com.huntcareer.qa.testdata;

import org.testng.annotations.DataProvider;

public class SearchData {

    @DataProvider(name = "validSearch")
    public static Object[][] validSearch() {
        return new Object[][]{
                {"Software Engineer"}
        };
    }

    @DataProvider(name = "invalidSearch")
    public static Object[][] invalidSearch() {
        return new Object[][]{
                {"asdfghjkl"}
        };
    }

    @DataProvider(name = "specialCharSearch")
    public static Object[][] specialCharSearch() {
        return new Object[][]{
                {"!@#$%^&*"}
        };
    }

    @DataProvider(name = "searchWithFilters")
    public static Object[][] searchWithFilters() {
        return new Object[][]{
                {"Manager", "London", "Full-time"}
        };
    }
}