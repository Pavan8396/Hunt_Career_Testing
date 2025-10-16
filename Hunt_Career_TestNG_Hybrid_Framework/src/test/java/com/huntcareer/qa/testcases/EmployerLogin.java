package com.huntcareer.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;

public class EmployerLogin extends Base {
    EmployerLoginPage elp;
    HomePage hp;
    LandingPage ldp;

    @BeforeMethod
    public void setup() {
        super.setup(prop.getProperty("browser"));
        ldp = new LandingPage(driver);
        ldp.clickOnEmployerRole();
    }

    @Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_1_verifyWithValidData() {
        elp = new EmployerLoginPage(driver);
        elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
        elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
        hp = elp.clickLoginButton();
        Assert.assertTrue(hp.successLoginMessage.isDisplayed());
    }

    @Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_2_verifyWithInvalidUser() {
        elp = new EmployerLoginPage(driver);
        elp.enterEmail(dataProp.getProperty("EmployerinvalidUser"));
        elp.enterPassword(dataProp.getProperty("EmployerinvalidPassword"));
        elp.clickLoginButton();
        Assert.assertTrue(elp.invalidEmailAndPassword.isDisplayed());
    }

    @Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_3_verifyShortPassword() {
        elp = new EmployerLoginPage(driver);
        elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
        elp.enterPassword(dataProp.getProperty("EmployershortPassword"));
        elp.clickLoginButton();
        Assert.assertTrue(elp.passwordMustHaveATLeast8CharactersLongMessage.isDisplayed());
    }
}