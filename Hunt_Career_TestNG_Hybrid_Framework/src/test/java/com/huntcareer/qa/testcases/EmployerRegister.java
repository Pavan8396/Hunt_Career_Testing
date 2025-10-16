package com.huntcareer.qa.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerRegisterPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.testdata.EmployerRegisterData;

public class EmployerRegister extends Base {
    EmployerRegisterPage erp;
    EmployerLoginPage elp;
    LandingPage ldp;

    @BeforeMethod
    public void setup() {
        super.setup(prop.getProperty("browser"));
        ldp = new LandingPage(driver);
        erp = ldp.clickOnEmployerRole();
        erp.clickSignupLink();
    }

    @Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_1_verifyRegisterWithValidData() {
        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.validUser();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        Assert.assertTrue(elp.registerSuccessMessage.isDisplayed());
    }

    @Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_2_verifyDuplicateData() {
        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.duplicateUser();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        Assert.assertTrue(erp.duplicateErrorMessage.isDisplayed());
    }

    @Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_3_verifyNoFirstNameData() {
        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noName();
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        Assert.assertTrue(erp.companyNameBlankMessage.isDisplayed());
    }
}