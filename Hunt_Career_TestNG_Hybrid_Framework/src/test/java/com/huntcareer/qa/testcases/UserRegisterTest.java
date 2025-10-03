package com.huntcareer.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;
import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.RegisterPage;
import com.huntcareer.qa.utils.JsonUtils;

public class UserRegisterTest extends TestBase {
    LandingPage landingPage;
    RegisterPage registerPage;
    LoginPage loginPage;
    Faker faker;

    @BeforeMethod
    public void setUp() {
        driver = initialization();
        landingPage = new LandingPage(driver);
        faker = new Faker();
        registerPage = landingPage.clickUserRegisterLink();
    }

    @DataProvider(name = "validUsers")
    public Object[][] getValidUsers() {
        return JsonUtils.getTestData("src/test/resource/fixtures/registerData.json", "validUsers", "firstName", "lastName", "email", "password", "confirmPassword", "phoneNumber");
    }

    @DataProvider(name = "duplicateUser")
    public Object[][] getDuplicateUser() {
        return JsonUtils.getTestData("src/test/resource/fixtures/registerData.json", "duplicateUser", "firstName", "lastName", "email", "password", "confirmPassword", "phoneNumber");
    }

    @Test(dataProvider = "validUsers")
    public void testSuccessfulRegistration(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber) {
        loginPage = registerPage.register(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), password, confirmPassword, faker.phoneNumber().cellPhone());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.getSuccessCreationMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(dataProvider = "duplicateUser")
    public void testDuplicateRegistration(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber) {
        registerPage.register(firstName, lastName, email, password, confirmPassword, phoneNumber);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.getDuplicateErrorMessage().isDisplayed());
        softAssert.assertAll();
    }
}