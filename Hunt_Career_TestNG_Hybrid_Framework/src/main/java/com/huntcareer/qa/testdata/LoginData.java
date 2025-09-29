package com.huntcareer.qa.testdata;

import org.testng.annotations.DataProvider;

public class LoginData {

    @DataProvider(name = "validUser")
    public static Object[][] validUser() {
        return new Object[][]{
                {"testuser@gmail.com", "password"}
        };
    }

    @DataProvider(name = "invalidUser")
    public static Object[][] invalidUser() {
        return new Object[][]{
                {"invaliduser@gmail.com", "wrongpassword"}
        };
    }

    @DataProvider(name = "invalidEmails")
    public static Object[][] invalidEmails() {
        return new Object[][]{
                {"testemail.com", "password"}
        };
    }

    @DataProvider(name = "invalidPassword")
    public static Object[][] invalidPassword() {
        return new Object[][]{
                {"testuser@gmail.com", "pass"}
        };
    }

    @DataProvider(name = "blankEmail")
    public static Object[][] blankEmail() {
        return new Object[][]{
                {"", "password"}
        };
    }

    @DataProvider(name = "blankPassword")
    public static Object[][] blankPassword() {
        return new Object[][]{
                {"testuser@gmail.com", ""}
        };
    }
}