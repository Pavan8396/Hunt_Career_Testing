package com.huntcareer.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.SearchPage;
import com.huntcareer.qa.testdata.EmployerLoginData;

public class EmployerLogin extends Base {

    LandingPage landingPage;
    EmployerLoginPage employerLoginPage;
    SearchPage searchPage;

    public EmployerLogin() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
        driver.get(prop.getProperty("url"));
        landingPage = new LandingPage(driver);
        employerLoginPage = landingPage.clickEmployerLoginButton();
        searchPage = new SearchPage(driver);
    }

    @Test(dataProvider = "validUsers", dataProviderClass = EmployerLoginData.class)
    public void testLoginWithValidCredentials(String email, String password) {
        employerLoginPage.enterEmail(email);
        employerLoginPage.enterPassword(password);
        employerLoginPage.clickLoginButton();
        searchPage.verifyLoginSuccessMessage();
    }

    @Test(dataProvider = "invalidUsers", dataProviderClass = EmployerLoginData.class)
    public void testLoginWithInvalidCredentials(String email, String password) {
        employerLoginPage.enterEmail(email);
        employerLoginPage.enterPassword(password);
        employerLoginPage.clickLoginButton();
        employerLoginPage.verifyInvalidEmailAndPassword();
    }

    @Test(dataProvider = "invalidEmails", dataProviderClass = EmployerLoginData.class)
    public void testLoginWithInvalidEmail(String email, String password) {
        employerLoginPage.enterEmail(email);
        employerLoginPage.enterPassword(password);
        employerLoginPage.clickLoginButton();
        employerLoginPage.verifyPleaseEnterValidEmailAddress();
    }

    @Test(dataProvider = "invalidPasswords", dataProviderClass = EmployerLoginData.class)
    public void testLoginWithInvalidPassword(String email, String password) {
        employerLoginPage.enterEmail(email);
        employerLoginPage.enterPassword(password);
        employerLoginPage.clickLoginButton();
        employerLoginPage.verifyPasswordMustHaveAtLeast8CharactersLong();
    }

    @Test(dataProvider = "blankEmail", dataProviderClass = EmployerLoginData.class)
    public void testLoginWithBlankEmail(String email, String password) {
        employerLoginPage.enterEmail(email);
        employerLoginPage.enterPassword(password);
        employerLoginPage.clickLoginButton();
        employerLoginPage.verifyEmailIsRequired();
    }

    @Test(dataProvider = "blankPassword", dataProviderClass = EmployerLoginData.class)
    public void testLoginWithBlankPassword(String email, String password) {
        employerLoginPage.enterEmail(email);
        employerLoginPage.enterPassword(password);
        employerLoginPage.clickLoginButton();
        employerLoginPage.verifyPasswordIsRequired();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}