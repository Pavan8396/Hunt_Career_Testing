package com.huntcareer.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.SearchPage;
import com.huntcareer.qa.utils.JsonUtils;

public class EmployerLoginTest extends TestBase {
    LandingPage landingPage;
    EmployerLoginPage employerLoginPage;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        driver = initialization();
        landingPage = new LandingPage(driver);
        employerLoginPage = landingPage.clickEmployerLoginLink();
    }

    @DataProvider(name = "validUsers")
    public Object[][] getValidUsers() {
        return JsonUtils.getTestData("src/test/resource/fixtures/employerLoginData.json", "validUsers", "description", "email", "password");
    }

    @DataProvider(name = "invalidUsers")
    public Object[][] getInvalidUsers() {
        return JsonUtils.getTestData("src/test/resource/fixtures/employerLoginData.json", "invalidUsers", "description", "email", "password");
    }

    @DataProvider(name = "invalidEmails")
    public Object[][] getInvalidEmails() {
        return JsonUtils.getTestData("src/test/resource/fixtures/employerLoginData.json", "invalidEmails", "description", "email", "password");
    }

    @DataProvider(name = "invalidPasswords")
    public Object[][] getInvalidPasswords() {
        return JsonUtils.getTestData("src/test/resource/fixtures/employerLoginData.json", "invalidPasswords", "description", "email", "password");
    }

    @DataProvider(name = "blankEmail")
    public Object[][] getBlankEmail() {
        return JsonUtils.getTestData("src/test/resource/fixtures/employerLoginData.json", "blankEmail", "description", "email", "password");
    }

    @DataProvider(name = "blankPassword")
    public Object[][] getBlankPassword() {
        return JsonUtils.getTestData("src/test/resource/fixtures/employerLoginData.json", "blankPassword", "description", "email", "password");
    }

    @Test(dataProvider = "validUsers")
    public void testSuccessfulLogin(String description, String email, String password) {
        searchPage = employerLoginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.getLoginSuccessMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidUsers")
    public void testInvalidCredentials(String description, String email, String password) {
        employerLoginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerLoginPage.getInvalidEmailOrPasswordMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidEmails")
    public void testInvalidEmailFormat(String description, String email, String password) {
        employerLoginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerLoginPage.getInvalidEmailAddressMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidPasswords")
    public void testPasswordTooShort(String description, String email, String password) {
        employerLoginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerLoginPage.getPasswordLengthErrorMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "blankEmail")
    public void testBlankEmail(String description, String email, String password) {
        employerLoginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerLoginPage.getEmptyEmailMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "blankPassword")
    public void testBlankPassword(String description, String email, String password) {
        employerLoginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerLoginPage.getEmptyPasswordMessage().isDisplayed());
        softAssert.assertAll();
    }
}