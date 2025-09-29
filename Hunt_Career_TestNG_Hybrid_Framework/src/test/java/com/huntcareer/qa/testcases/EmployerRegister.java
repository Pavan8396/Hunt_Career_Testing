package com.huntcareer.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerRegisterPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.testdata.EmployerRegisterData;

public class EmployerRegister extends Base {

    LandingPage landingPage;
    EmployerRegisterPage employerRegisterPage;
    EmployerLoginPage employerLoginPage;

    public EmployerRegister() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
        driver.get(prop.getProperty("url"));
        landingPage = new LandingPage(driver);
        employerRegisterPage = landingPage.clickEmployerRegisterButton();
        employerLoginPage = new EmployerLoginPage(driver);
    }

    @Test(dataProvider = "validUsers", dataProviderClass = EmployerRegisterData.class)
    public void testRegisterWithValidCredentials(String name, String email, String password, String confirmPassword) {
        employerRegisterPage.register(name, email, password, confirmPassword);
        employerLoginPage.verifySuccessCreationMessage();
    }

    @Test(dataProvider = "duplicateUser", dataProviderClass = EmployerRegisterData.class)
    public void testRegisterWithDuplicateCredentials(String name, String email, String password, String confirmPassword) {
        employerRegisterPage.register(name, email, password, confirmPassword);
        employerRegisterPage.verifyDuplicateErrorMessage();
    }

    @Test(dataProvider = "invalidCases", dataProviderClass = EmployerRegisterData.class)
    public void testRegisterWithInvalidData(String name, String email, String password, String confirmPassword, String expectedError) {
        employerRegisterPage.register(name, email, password, confirmPassword);
        switch (expectedError) {
            case "nameRequiredMessage":
                employerRegisterPage.verifyNameRequiredMessage();
                break;
            case "emailRequiredMessage":
                employerRegisterPage.verifyEmailRequiredMessage();
                break;
            case "passwordRequiredMessage":
                employerRegisterPage.verifyPasswordRequiredMessage();
                break;
            case "confirmPasswordRequiredMessage":
                employerRegisterPage.verifyConfirmPasswordRequiredMessage();
                break;
            case "passwordMismatchMessage":
                employerRegisterPage.verifyPasswordMismatchMessage();
                break;
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}