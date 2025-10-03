package com.huntcareer.qa.testcases;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerRegisterPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.testdata.EmployerRegisterData;

public class EmployerRegisterTest extends TestBase {
    LandingPage landingPage;
    EmployerRegisterPage employerRegisterPage;
    EmployerLoginPage employerLoginPage;

    @BeforeMethod
    public void setUp() {
        driver = initialization();
        landingPage = new LandingPage(driver);
        employerRegisterPage = landingPage.clickEmployerRegisterLink();
    }

    @DataProvider(name = "invalidEmailTypes")
    public Object[][] invalidEmailTypes() {
        return new Object[][] {
            {"plainEmail"},
            {"noLocalPart"},
            {"localPartOnly"},
            {"twoConsecutiveAt"},
            {"withoutTopLevelDomain"},
            {"tailingWithDot"},
            {"topLevelDomainTooShort"},
            {"domainStartsWithHyphen"},
            {"doubleDotInDomain"},
            {"noAtTheRate"},
            {"illegalCharacters"},
            {"dotAfterName"},
            {"twoConsecutiveDots"},
            {"dotAtBeginning"},
            {"withoutDomain"},
            {"withoutUserName"}
        };
    }

    @Test(priority = 1)
    public void testSuccessfulRegistration() {
        Map<String, String> user = EmployerRegisterData.validUser();
        employerLoginPage = employerRegisterPage.register(user.get("name"), user.get("email"), user.get("password"), user.get("confirmPassword"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(employerLoginPage.getSuccessCreationMessage().isDisplayed());
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testDuplicateRegistration() {
        Map<String, String> user = EmployerRegisterData.duplicateUser();
        employerRegisterPage.register(user.get("name"), user.get("email"), user.get("password"), user.get("confirmPassword"));
        employerRegisterPage.verifyUserAlreadyExistsMessage();
    }

    @Test(priority = 3)
    public void testNoName() {
        Map<String, String> user = EmployerRegisterData.noName();
        employerRegisterPage.enterEmail(user.get("email"));
        employerRegisterPage.enterPassword(user.get("password"));
        employerRegisterPage.clickRegisterButton();
        employerRegisterPage.verifyBlankNameMessage();
    }

    @Test(priority = 4)
    public void testNoEmail() {
        Map<String, String> user = EmployerRegisterData.noEmail();
        employerRegisterPage.enterName(user.get("name"));
        employerRegisterPage.enterPassword(user.get("password"));
        employerRegisterPage.clickRegisterButton();
        employerRegisterPage.verifyBlankEmailMessage();
    }

    @Test(priority = 5)
    public void testNoPassword() {
        Map<String, String> user = EmployerRegisterData.noPassword();
        employerRegisterPage.enterName(user.get("name"));
        employerRegisterPage.enterEmail(user.get("email"));
        employerRegisterPage.clickRegisterButton();
        employerRegisterPage.verifyPasswordMustBeAtLeast8CharctersLongMessage();
    }

    @Test(priority = 6, dataProvider = "invalidEmailTypes")
    public void testInvalidEmail(String emailType) {
        Map<String, String> user = EmployerRegisterData.invalidEmail(emailType);
        employerRegisterPage.register(user.get("name"), user.get("email"), user.get("password"), user.get("confirmPassword"));
        employerRegisterPage.verifyInvalidEmailAddressMessage();
    }

    @Test(priority = 7)
    public void testShortPassword() {
        Map<String, String> user = EmployerRegisterData.shortPassword();
        employerRegisterPage.register(user.get("name"), user.get("email"), user.get("password"), user.get("confirmPassword"));
        employerRegisterPage.verifyPasswordMustBeAtLeast8CharctersLongMessage();
    }

    @Test(priority = 8)
    public void testPasswordMismatch() {
        Map<String, String> user = EmployerRegisterData.passwordMismatch();
        employerRegisterPage.register(user.get("name"), user.get("email"), user.get("password"), user.get("confirmPassword"));
        employerRegisterPage.verifyPasswordMismatchMessage();
    }

    @Test(priority = 9)
    public void testNoConfirmPassword() {
        Map<String, String> user = EmployerRegisterData.noConfirmPassword();
        employerRegisterPage.enterName(user.get("name"));
        employerRegisterPage.enterEmail(user.get("email"));
        employerRegisterPage.enterPassword(user.get("password"));
        employerRegisterPage.clickRegisterButton();
        employerRegisterPage.verifyPasswordMismatchMessage();
    }
}