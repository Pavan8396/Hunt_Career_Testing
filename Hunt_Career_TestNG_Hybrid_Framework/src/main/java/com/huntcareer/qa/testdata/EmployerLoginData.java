package com.huntcareer.qa.testdata;

import java.util.HashMap;
import java.util.Map;

public class EmployerLoginData {
    public static Map<String, String> validUser() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "exigent@mailinator.com");
        data.put("password", "Test@1234");
        return data;
    }

    public static Map<String, String> invalidUser() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "morae@mailinator.com");
        data.put("password", "Test@1234");
        return data;
    }

    public static Map<String, String> invalidEmail() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "apple");
        data.put("password", "Test@12345");
        return data;
    }

    public static Map<String, String> shortPassword() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "exigent@mailinator.com");
        data.put("password", "Test");
        return data;
    }

    public static Map<String, String> blankEmail() {
        Map<String, String> data = new HashMap<>();
        data.put("password", "Test@1234");
        return data;
    }

    public static Map<String, String> blankPassword() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "exigent@mailinator.com");
        return data;
    }
}