package com.huntcareer.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.SearchPage;
import com.huntcareer.qa.utils.JsonUtils;

public class UserLoginTest extends TestBase {
    LandingPage landingPage;
    LoginPage loginPage;
    SearchPage searchPage;

    @BeforeMethod
    public void setUp() {
        driver = initialization();
        landingPage = new LandingPage(driver);
        loginPage = landingPage.clickUserLoginLink();
    }

    @DataProvider(name = "validUsers")
    public Object[][] getValidUsers() {
        return JsonUtils.getTestData("src/test/resource/fixtures/userLoginData.json", "validUser", "email", "password");
    }

    @DataProvider(name = "invalidUsers")
    public Object[][] getInvalidUsers() {
        return JsonUtils.getTestData("src/test/resource/fixtures/userLoginData.json", "invalidUser", "email", "password");
    }

    @DataProvider(name = "invalidEmails")
    public Object[][] getInvalidEmails() {
        return JsonUtils.getTestData("src/test/resource/fixtures/userLoginData.json", "invalidEmails", "email", "password");
    }

    @DataProvider(name = "invalidPasswords")
    public Object[][] getInvalidPasswords() {
        return JsonUtils.getTestData("src/test/resource/fixtures/userLoginData.json", "invalidPassword", "email", "password");
    }

    @DataProvider(name = "blankEmail")
    public Object[][] getBlankEmail() {
        return JsonUtils.getTestData("src/test/resource/fixtures/userLoginData.json", "blankEmail", "email", "password");
    }

    @DataProvider(name = "blankPassword")
    public Object[][] getBlankPassword() {
        return JsonUtils.getTestData("src/test/resource/fixtures/userLoginData.json", "blankPassword", "email", "password");
    }

    @Test(dataProvider = "validUsers")
    public void testSuccessfulLogin(String email, String password) {
        searchPage = loginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(searchPage.getLoginSuccessMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidUsers")
    public void testInvalidCredentials(String email, String password) {
        loginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.getInvalidEmailOrPasswordMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidEmails")
    public void testInvalidEmailFormat(String email, String password) {
        loginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.getInvalidEmailAddressMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidPasswords")
    public void testPasswordTooShort(String email, String password) {
        loginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.getPasswordLengthErrorMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "blankEmail")
    public void testBlankEmail(String email, String password) {
        loginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.getEmptyEmailMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "blankPassword")
    public void testBlankPassword(String email, String password) {
        loginPage.login(email, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.getEmptyPasswordMessage().isDisplayed());
        softAssert.assertAll();
    }
}