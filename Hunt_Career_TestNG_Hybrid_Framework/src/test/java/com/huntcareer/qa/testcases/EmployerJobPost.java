package com.huntcareer.qa.testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerPostJobPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.testdata.PostAJob;

public class EmployerJobPost extends Base {
    EmployerPostJobPage jp;
    LandingPage ldp;
    EmployerLoginPage elp;

    @BeforeMethod
    public void setup() {
        super.setup(prop.getProperty("browser"));
        ldp = new LandingPage(driver);
        ldp.clickOnEmployerRole();
        elp = new EmployerLoginPage(driver);
        elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
        elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
        elp.clickLoginButton();
    }

    @Test(priority = 1)
    public void TC_1_Create_Job_Post_With_Valid_Data() {
        jp = new EmployerPostJobPage(driver);
        Map<String, String> job = PostAJob.validJob();
        jp.clickPostAJobNavigationButton();
        jp.enterJobTitle(job.get("title"));
        jp.enterCompanyName(job.get("companyName"));
        jp.enterDescription(job.get("description"));
        jp.enterLocation(job.get("location"));
        jp.selectJobTypeOption();
        jp.clickPostJobSubmitButton();
        Assert.assertTrue(jp.jobPostSuccessMessage.isDisplayed());
    }

    @Test(priority = 2)
    public void TC_2_Blank_Job_Post() {
        jp = new EmployerPostJobPage(driver);
        Map<String, String> job = PostAJob.blankJob();
        jp.clickPostAJobNavigationButton();
        jp.enterJobTitle(job.get("title"));
        jp.enterCompanyName(job.get("companyName"));
        jp.enterDescription(job.get("description"));
        jp.enterLocation(job.get("location"));
        jp.selectJobTypeOption();
        jp.clickPostJobSubmitButton();
        Assert.assertTrue(jp.jobTitleRequiredErrorMessage.isDisplayed());
    }
}
