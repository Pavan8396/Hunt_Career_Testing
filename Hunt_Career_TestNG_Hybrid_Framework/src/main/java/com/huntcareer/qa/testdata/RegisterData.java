package com.huntcareer.qa.testdata;

import org.testng.annotations.DataProvider;

public class RegisterData {

    @DataProvider(name = "validUsers")
    public static Object[][] validUsers() {
        return new Object[][]{
                {"Test", "User", "testuser@example.com", "password123", "password123", "1234567890"}
        };
    }

    @DataProvider(name = "duplicateUser")
    public static Object[][] duplicateUser() {
        return new Object[][]{
                {"Pavan", "U", "upavan@mailinator.com", "Test@1234", "Test@1234", "1234567890"}
        };
    }

    @DataProvider(name = "invalidCases")
    public static Object[][] invalidCases() {
        return new Object[][]{
                {"", "User", "test@example.com", "password123", "password123", "1234567890", "firstNameRequiredMessage"},
                {"Test", "", "test@example.com", "password123", "password123", "1234567890", "lastNameRequiredMessage"},
                {"Test", "User", "", "password123", "password123", "1234567890", "emailRequiredMessage"},
                {"Test", "User", "test", "password123", "password123", "1234567890", "invalidEmailMessage"},
                {"Test", "User", "test@example.com", "", "password123", "1234567890", "passwordRequiredMessage"},
                {"Test", "User", "test@example.com", "123", "123", "1234567890", "passwordLengthMessage"},
                {"Test", "User", "test@example.com", "password123", "password456", "1234567890", "passwordMismatchMessage"},
                {"Test", "User", "test@example.com", "password123", "password123", "", "phoneNumberRequiredMessage"}
        };
    }
}