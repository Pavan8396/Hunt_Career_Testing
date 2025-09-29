package com.huntcareer.qa.testdata;

import org.testng.annotations.DataProvider;

public class EmployerLoginData {

    @DataProvider(name = "validUsers")
    public static Object[][] validUsers() {
        return new Object[][]{
                {"testemployer@gmail.com", "password"}
        };
    }

    @DataProvider(name = "invalidUsers")
    public static Object[][] invalidUsers() {
        return new Object[][]{
                {"invalidemployer@gmail.com", "wrongpassword"}
        };
    }

    @DataProvider(name = "invalidEmails")
    public static Object[][] invalidEmails() {
        return new Object[][]{
                {"testemail.com", "password"}
        };
    }

    @DataProvider(name = "invalidPasswords")
    public static Object[][] invalidPasswords() {
        return new Object[][]{
                {"testemployer@gmail.com", "pass"}
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
                {"testemployer@gmail.com", ""}
        };
    }
}