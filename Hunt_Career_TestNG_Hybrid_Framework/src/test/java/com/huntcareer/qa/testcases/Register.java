package com.huntcareer.qa.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.RegisterPage;
import com.huntcareer.qa.testdata.RegisterData;

public class Register extends Base {
    RegisterPage rp;
    LoginPage lp;
    LandingPage ldp;
    HomePage hp;

    @BeforeMethod
    public void setup() {
        super.setup(prop.getProperty("browser"));
        ldp = new LandingPage(driver);
        hp = ldp.clickOnJobSeekerRole();
        rp = hp.clickOnRegisterHomeLink();
    }

    @Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_1_verifyRegisterWithValidData() {
        Map<String, String> user = RegisterData.validUser();
        rp.enterFirstName(user.get("firstName"));
        rp.enterLastName(user.get("lastName"));
        rp.clickFirstNextButton();
        rp.enterEmail(user.get("email"));
        rp.enterPassword(user.get("password"));
        rp.enterConfirmPassword(user.get("confirmPassword"));
        rp.clickSecondNextButton();
        rp.enterPhoneNumber(user.get("phoneNumber"));
        lp = rp.clickSignUpButton();
        Assert.assertTrue(lp.registerSuccessMessage.isDisplayed());
    }

    @Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_2_verifyDuplicateData() {
        Map<String, String> user = RegisterData.duplicateUser();
        rp.enterFirstName(user.get("firstName"));
        rp.enterLastName(user.get("lastName"));
        rp.clickFirstNextButton();
        rp.enterEmail(user.get("email"));
        rp.enterPassword(user.get("password"));
        rp.enterConfirmPassword(user.get("confirmPassword"));
        rp.clickSecondNextButton();
        rp.enterPhoneNumber(user.get("phoneNumber"));
        rp.clickSignUpButton();
        Assert.assertTrue(rp.duplicateErrorMessage.isDisplayed());
    }

    @Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_3_verifyNoFirstNameData() {
        Map<String, String> user = RegisterData.noFirstName();
        rp.enterLastName(user.get("lastName"));
        rp.clickFirstNextButton();
        Assert.assertTrue(rp.firstNameBlankMessage.isDisplayed());
    }
}