package com.huntcareer.qa.testcases;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.TestBase;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.RegisterPage;
import com.huntcareer.qa.testdata.RegisterData;

public class UserRegisterTest extends TestBase {
	RegisterPage registerPage;
	LoginPage loginPage;
	LandingPage landingPage;

	@BeforeMethod
	public void setup() {
		driver = initialization();
		landingPage = new LandingPage(driver);
		registerPage = landingPage.clickUserRegisterLink();
	}

	@Test(priority = 1)
	public void testRegisterWithValidData() {
		Map<String, String> user = RegisterData.validUser();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.enterPhoneNumber(user.get("phoneNumber"));
		loginPage = registerPage.clickSignUpButton();
		loginPage.verifyRegisterSuccess();
	}

	@Test(priority = 2)
	public void testDuplicateData() {
		Map<String, String> user = RegisterData.duplicateUser();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.enterPhoneNumber(user.get("phoneNumber"));
		registerPage.clickSignUpButton();
		registerPage.verifyUserAlreadyExistsMessage();
	}

	@Test(priority = 3)
	public void testNoFirstNameData() {
		Map<String, String> user = RegisterData.noFirstName();
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.verifyBlankFirstNameMessage();
	}

	@Test(priority = 4)
	public void testNoLastNameData() {
		Map<String, String> user = RegisterData.noLastName();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.clickFirstNextButton();
		registerPage.verifyBlankLastNameMessage();
	}

	@Test(priority = 5)
	public void testNoEmailData() {
		Map<String, String> user = RegisterData.noEmail();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyBlankEmailMessage();
	}

	@Test(priority = 6)
	public void testNoPasswordData() {
		Map<String, String> user = RegisterData.noPassword();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyPasswordMustBeAtLeast8CharctersLongMessage();
	}
}