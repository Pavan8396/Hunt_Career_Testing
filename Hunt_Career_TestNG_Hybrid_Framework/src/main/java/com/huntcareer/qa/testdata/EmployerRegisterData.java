package com.huntcareer.qa.testdata;

import org.testng.annotations.DataProvider;

public class EmployerRegisterData {

    @DataProvider(name = "validUsers")
    public static Object[][] validUsers() {
        return new Object[][]{
                {"Test Employer", "newemployer@gmail.com", "password", "password"}
        };
    }

    @DataProvider(name = "duplicateUser")
    public static Object[][] duplicateUser() {
        return new Object[][]{
                {"Test Employer", "testemployer@gmail.com", "password", "password"}
        };
    }

    @DataProvider(name = "invalidCases")
    public static Object[][] invalidCases() {
        return new Object[][]{
                {"", "test@gmail.com", "password", "password", "nameRequiredMessage"},
                {"Test Employer", "", "password", "password", "emailRequiredMessage"},
                {"Test Employer", "test@gmail.com", "", "password", "passwordRequiredMessage"},
                {"Test Employer", "test@gmail.com", "password", "", "confirmPasswordRequiredMessage"},
                {"Test Employer", "test@gmail.com", "password", "wrongpassword", "passwordMismatchMessage"}
        };
    }
}