package com.huntcareer.qa.testdata;

import java.util.HashMap;
import java.util.Map;

import com.huntcareer.qa.utils.Utilities;

public class RegisterData {
	public static Map<String, String> validUser() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("normal"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}

	public static Map<String, String> duplicateUser() {
		Map<String, String> data = new  HashMap<>();
		data.put("firstName", "Pavan");
		data.put("lastName", "U");
		data.put("email", "upavan@mailinator.com");
		data.put("password", "Test@1234");
		data.put("confirmPassword", "Test@1234");
		data.put("phoneNumber", "1234567890");
		return data;
	}

	public static Map<String, String> noFirstName() {
		Map<String, String>data = new  HashMap<>();
		//data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		return data;
	}

	public static Map<String, String> noLastName() {
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		return data;
	}

	public static Map<String, String> noEmail() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		//data.put("email", Utilities.getEmail("normal"));
		data.put("password", password);
		data.put("confirmPassword", password);
		return data;
	}

	public static Map<String, String> noPassword() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("normal"));
		//data.put("password", password);
		data.put("confirmPassword", password);
		return data;
	}

	public static Map<String, String> passwordWithLessCharacters() {
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("normal"));
		data.put("password", "Test");
		data.put("confirmPassword", "Test");
		return data;
	}

	public static Map<String, String> noConfirmPassword() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("normal"));
		data.put("password", password);
		//data.put("confirmPassword", password);
		return data;
	}

	public static Map<String, String> noPhoneNumber() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("normal"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}

	public static Map<String, String> invalidPhoneNumber() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("normal"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", "abcd123456");
		return data;
	}

	public static Map<String, String> emailWithoutAllDomain() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("withoutAllDomain"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}

	public static Map<String, String> emailwithoutTopLevelDomain() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("withoutTopLevelDomain"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}

	public static Map<String, String> emailwithdoubleAt() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("doubleAt"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}

	public static Map<String, String> emailWithdotBeforeAt() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("dotBeforeAt"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}

	public static Map<String, String> emailwithoutDomain() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("withoutDomain"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}

	public static Map<String, String> emailwithoutUserName() {
		String password = Utilities.generateRandomPassword();
		Map<String, String>data = new  HashMap<>();
		data.put("firstName", Utilities.getFirstName());
		data.put("lastName", Utilities.getLastName());
		data.put("email", Utilities.getEmail("withoutUserName"));
		data.put("password", password);
		data.put("confirmPassword", password);
		data.put("phoneNumber", Utilities.generateRandomPhoneNumber());
		return data;
	}
}
