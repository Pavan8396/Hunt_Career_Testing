package com.huntcareer.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.SearchPage;
import com.huntcareer.qa.testdata.LoginData;

public class Login extends TestBase {

    LoginPage loginPage;
    SearchPage searchPage;

    public Login() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
    }

    @Test(dataProvider = "validUser", dataProviderClass = LoginData.class)
    public void testLoginWithValidCredentials(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        searchPage.verifyLoginSuccessMessage();
    }

    @Test(dataProvider = "invalidUser", dataProviderClass = LoginData.class)
    public void testLoginWithInvalidCredentials(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.verifyInvalidEmailAndPassword();
    }

    @Test(dataProvider = "invalidEmails", dataProviderClass = LoginData.class)
    public void testLoginWithInvalidEmail(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.verifyPleaseEnterValidEmailAddress();
    }

    @Test(dataProvider = "invalidPassword", dataProviderClass = LoginData.class)
    public void testLoginWithInvalidPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.verifyPasswordMustHaveAtLeast8CharactersLong();
    }

    @Test(dataProvider = "blankEmail", dataProviderClass = LoginData.class)
    public void testLoginWithBlankEmail(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.verifyEmailIsRequired();
    }

    @Test(dataProvider = "blankPassword", dataProviderClass = LoginData.class)
    public void testLoginWithBlankPassword(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.verifyPasswordIsRequired();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}