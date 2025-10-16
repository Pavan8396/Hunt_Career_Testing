package com.huntcareer.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;

public class Login extends Base {
    LoginPage lp;
    HomePage hp;
    LandingPage ldp;

    @BeforeMethod
    public void setup() {
        super.setup(prop.getProperty("browser"));
        ldp = new LandingPage(driver);
        hp = ldp.clickOnJobSeekerRole();
        lp = hp.clickOnLoginHomeLink();
    }

    @Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_1_verifyWithValidData() {
        lp.enterEmail(dataProp.getProperty("validEmail"));
        lp.enterPassword(dataProp.getProperty("validPassword"));
        hp = lp.clickLoginButton();
        Assert.assertTrue(hp.successLoginMessage.isDisplayed());
    }

    @Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_2_verifyWithInvalidUser() {
        lp.enterEmail(dataProp.getProperty("invalidUser"));
        lp.enterPassword(dataProp.getProperty("invalidPassword"));
        lp.clickLoginButton();
        Assert.assertTrue(lp.invalidEmailAndPassword.isDisplayed());
    }

    @Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_3_verifyShortPassword() {
        lp.enterEmail(dataProp.getProperty("validEmail"));
        lp.enterPassword(dataProp.getProperty("shortPassword"));
        lp.clickLoginButton();
        Assert.assertTrue(lp.passwordMustHaveATLeast8CharactersLongMessage.isDisplayed());
    }
}
