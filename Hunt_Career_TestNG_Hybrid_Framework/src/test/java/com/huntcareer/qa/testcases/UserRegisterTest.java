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

    @Test(priority = 7)
	public void testPasswordWithLessCharacters() {
		Map<String, String> user = RegisterData.passwordWithLessCharacters();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyPasswordMustBeAtLeast8CharctersLongMessage();
	}

	@Test(priority = 8)
	public void testRegisterWithoutConfirmPasswordData() {
		Map<String, String> user = RegisterData.noConfirmPassword();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.clickSecondNextButton();
		registerPage.verifyPasswordDoNotMatchMessage();
	}

	@Test(priority = 9)
	public void testNoPhoneNumberData() {
		Map<String, String> user = RegisterData.noPhoneNumber();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.clickSignUpButton();
		registerPage.verifyPhoneNumberIsRequiredMessage();
	}

	@Test(priority = 10)
	public void testInvalidPhoneNumberData() {
		Map<String, String> user = RegisterData.invalidPhoneNumber();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.enterPhoneNumber(user.get("phoneNumber"));
		registerPage.clickSignUpButton();
		registerPage.verifyInvalidPhoneNumberMessage();
	}

	@Test(priority = 11)
	public void testEmailWithoutAllDomainData() {
		Map<String, String> user = RegisterData.emailWithoutAllDomain();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 12)
	public void testWithoutTopLevelDomainData() {
		Map<String, String> user = RegisterData.emailwithoutTopLevelDomain();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 13)
	public void testEmailWithDoubleAtData() {
		Map<String, String> user = RegisterData.emailwithdoubleAt();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 14)
	public void testDotBeforeAtTheRateData() {
		Map<String, String> user = RegisterData.emailWithdotBeforeAt();
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

	@Test(priority = 15)
	public void testEmailWithoutDomainData() {
		Map<String, String> user = RegisterData.emailwithoutDomain();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 16)
	public void testWithoutUserNameData() {
		Map<String, String> user = RegisterData.emailwithoutUserName();
		registerPage.enterFirstName(user.get("firstName"));
		registerPage.enterLastName(user.get("lastName"));
		registerPage.clickFirstNextButton();
		registerPage.enterEmail(user.get("email"));
		registerPage.enterPassword(user.get("password"));
		registerPage.enterConfirmPassword(user.get("confirmPassword"));
		registerPage.clickSecondNextButton();
		registerPage.verifyInvalidEmailAndPassword();
	}
}