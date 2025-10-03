package com.huntcareer.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;
import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerRegisterPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.utils.JsonUtils;

public class EmployerRegisterTest extends TestBase {
    LandingPage landingPage;
    EmployerRegisterPage employerRegisterPage;
    EmployerLoginPage employerLoginPage;
    Faker faker;

    @BeforeMethod
    public void setUp() {
        driver = initialization();
        landingPage = new LandingPage(driver);
        faker = new Faker();
        employerRegisterPage = landingPage.clickEmployerRegisterLink();
    }

    @DataProvider(name = "duplicateUser")
    public Object[][] getDuplicateUser() {
        return JsonUtils.getTestData("src/test/resource/fixtures/employerRegisterData.json", "duplicateUser", "name", "email", "password", "confirmPassword");
    }

    @Test
    public void testSuccessfulRegistration() {
        String password = faker.internet().password(8, 16);
        employerLoginPage = employerRegisterPage.register(faker.company().name(), faker.internet().emailAddress(), password, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerLoginPage.getSuccessCreationMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "duplicateUser")
    public void testDuplicateRegistration(String name, String email, String password, String confirmPassword) {
        employerLoginPage = employerRegisterPage.register(name, email, password, confirmPassword);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerRegisterPage.getDuplicateErrorMessage().isDisplayed());
        softAssert.assertAll();
    }
}