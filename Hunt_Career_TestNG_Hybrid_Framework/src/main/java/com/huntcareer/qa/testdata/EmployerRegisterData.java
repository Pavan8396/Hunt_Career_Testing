package com.huntcareer.qa.testdata;

import java.util.HashMap;
import java.util.Map;

import com.huntcareer.qa.utils.Utilities;

public class EmployerRegisterData {
    public static Map<String, String> validUser() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("default"));
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
        data.put("email", Utilities.getEmail("default"));
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
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("default"));
        // data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> passwordWithLessCharacters() {
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("default"));
        data.put("password", "Test");
        data.put("confirmPassword", "Test");
        return data;
    }

    public static Map<String, String> noConfirmPassword() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("default"));
        data.put("password", password);
        // data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> plainEmail() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("plainEmail"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> noLocalPart() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("noLocalPart"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> onlyLocalPart() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("localPartOnly"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> doubleAt() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("doubleAt"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> withoutTopLevelDomain() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("withoutTopLevelDomain"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> tailingWithDot() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("tailingWithDot"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> topLevelDomainTooShort() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("topLevelDomainTooShort"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> domainStartsWithHyphen() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("domainStartsWithHyphen"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> doubleDotInDomain() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("doubleDotInDomain"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> noAtTheRate() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("noAtTheRate"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> illegalChars() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("illegalChars"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> twoConsicutiveDots() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("twoConsicutiveDots"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> dotAtBeginning() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("dotAtBeginning"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> withQuotation() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("withQuotation"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> unclosedQuotation() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("unclosedQuotation"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> veryLengthyLocal() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("veryLengthyLocal"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> veryLengthyDomain() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("veryLengthyDomain"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> veryLengthyTLD() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("veryLenghtyTLD"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> missingSecondLevelDomain() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("missingSecondLevelDomain"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> withComma() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("withComma"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> doubleConsicutiveAt() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("doubleConsicutiveAt"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> underscoreInTLD() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("underscoreInTLD"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> dotBetweenName() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("dotBetweenName"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> spaceBeforeName() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("spaceBeforeName"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> spaceAfterName() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("spaceAfterName"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> dotBeforeAt() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("dotBeforeAt"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }

    public static Map<String, String> withoutDomain() {
        String password = Utilities.generateRandomPassword();
        Map<String, String> data = new HashMap<>();
        data.put("name", Utilities.getCompanyName());
        data.put("email", Utilities.getEmail("withoutDomain"));
        data.put("password", password);
        data.put("confirmPassword", password);
        return data;
    }
}