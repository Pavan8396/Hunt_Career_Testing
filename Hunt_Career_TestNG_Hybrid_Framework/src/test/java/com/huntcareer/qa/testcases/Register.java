package com.huntcareer.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.RegisterPage;
import com.huntcareer.qa.testdata.RegisterData;

public class Register extends TestBase {

    RegisterPage registerPage;
    LoginPage loginPage;

    public Register() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "validUsers", dataProviderClass = RegisterData.class)
    public void testRegisterWithValidCredentials(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber) {
        registerPage.register(firstName, lastName, email, password, confirmPassword, phoneNumber);
        loginPage.verifyRegisterSuccess();
    }

    @Test(dataProvider = "duplicateUser", dataProviderClass = RegisterData.class)
    public void testRegisterWithDuplicateCredentials(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber) {
        registerPage.register(firstName, lastName, email, password, confirmPassword, phoneNumber);
        registerPage.verifyDuplicateErrorMessage();
    }

    @Test(dataProvider = "invalidCases", dataProviderClass = RegisterData.class)
    public void testRegisterWithInvalidData(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber, String expectedError) {
        registerPage.register(firstName, lastName, email, password, confirmPassword, phoneNumber);
        switch (expectedError) {
            case "firstNameRequiredMessage":
                registerPage.verifyFirstNameRequiredMessage();
                break;
            case "lastNameRequiredMessage":
                registerPage.verifyLastNameRequiredMessage();
                break;
            case "emailRequiredMessage":
                registerPage.verifyEmailRequiredMessage();
                break;
            case "invalidEmailMessage":
                registerPage.verifyInvalidEmailMessage();
                break;
            case "passwordRequiredMessage":
                registerPage.verifyPasswordRequiredMessage();
                break;
            case "passwordLengthMessage":
                registerPage.verifyPasswordLengthMessage();
                break;
            case "passwordMismatchMessage":
                registerPage.verifyPasswordMismatchMessage();
                break;
            case "phoneNumberRequiredMessage":
                registerPage.verifyPhoneNumberRequiredMessage();
                break;
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}