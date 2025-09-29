package com.huntcareer.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.testdata.EmployerLoginData;

public class EmployerLogin extends TestBase {

    LandingPage landingPage;
    EmployerLoginPage employerLoginPage;

    public EmployerLogin() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        landingPage = new LandingPage();
        employerLoginPage = landingPage.clickEmployerLoginButton();
    }

    @Test(dataProvider = "validUsers", dataProviderClass = EmployerLoginData.class)
    public void testLoginWithValidCredentials(String email, String password) {
        employerLoginPage.enterEmail(email);
        employerLoginPage.enterPassword(password);
        employerLoginPage.clickLoginButton();
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