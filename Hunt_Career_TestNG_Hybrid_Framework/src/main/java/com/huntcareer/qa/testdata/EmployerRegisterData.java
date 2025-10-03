package com.huntcareer.qa.testdata;

import java.util.HashMap;
import java.util.Map;

import com.huntcareer.qa.utils.Utilities;

public class EmployerRegisterData {
    public static Map<String, String> validUser() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("normal"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> duplicateUser() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "Exigent Services Pvt Ltd");
        data.put("email", "exigent@mailinator.com");
        data.put("password", "Test@1234");
        data.put("confirmPassword", "Test@1234");
        return data;
    }

    public static Map<String, String> noName() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("email", Utilities.getEmail("normal"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> noEmail() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> noPassword() {
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("normal"));
        data.put("confirmPassword", "Test@1234");
        return data;
    }
}