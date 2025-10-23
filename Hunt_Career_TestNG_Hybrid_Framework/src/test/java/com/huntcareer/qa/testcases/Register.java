package com.huntcareer.qa.testcases;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.RegisterPage;
import com.huntcareer.qa.testdata.RegisterData;

public class Register extends Base {
	RegisterPage rp;
	LoginPage lp;
	LandingPage ldp;
	HomePage hp;

	public Register() {
		super();
	}

	@BeforeMethod
	public void setupRegister() {
		ldp = new LandingPage(driver);
		hp = ldp.clickOnJobSeekerRole();
		hp.clickOnRegisterHomeLink();
		rp = new RegisterPage(driver);
	}

	@Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_1_verifyRegisterWithValidData() {
		Map<String, String> user = RegisterData.validUser();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.enterPhoneNumber(user.get("phoneNumber"));
		lp = rp.clickSignUpButton();
		lp.verifyRegisterSuccess();
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_2_verifyDuplicateData() {

		Map<String, String> user = RegisterData.duplicateUser();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.enterPhoneNumber(user.get("phoneNumber"));
		rp.clickSignUpButton();
		rp.verifyUserAlreadyExistsMessage();
	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_3_verifyNoFirstNameData() {

		Map<String, String> user = RegisterData.noFirstName();
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.verifyBlankFirstNameMessage();
	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_4_verifyNoLastNameData() {

		Map<String, String> user = RegisterData.noLastName();
		rp.enterFirstName(user.get("firstName"));
		rp.clickFirstNextButton();
		rp.verifyBlankLastNameMessage();
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_5_verifyNoEmailData() {

		Map<String, String> user = RegisterData.noEmail();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyBlankEmailMessage();
	}

	@Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_6_verifyNoPasswordData() {

		Map<String, String> user = RegisterData.noPassword();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
	}

	@Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_7_verifyPasswordWithLessCharacters() {

		Map<String, String> user = RegisterData.passwordWithLessCharacters();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
	}

	@Test(priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_8_verifyRegisterWithoutConfirmPasswordData() {

		Map<String, String> user = RegisterData.noConfirmPassword();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.clickSecondNextButton();
		rp.verifyPasswordDoNotMatchMessage();
	}

	@Test(priority = 9, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_9_verifyNoPhoneNumberData() {

		Map<String, String> user = RegisterData.noPhoneNumber();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.clickSignUpButton();
		rp.verifyPhoneNumberIsRequiredMessage();
	}

	@Test(priority = 10, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_10_verifyInvalidPhoneNumberData() {

		Map<String, String> user = RegisterData.invalidPhoneNumber();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.enterPhoneNumber(user.get("phoneNumber"));
		rp.clickSignUpButton();
		rp.verifyInvalidPhoneNumberMessage();
	}

	@Test(priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_11_verifyplainEmail() {

		Map<String, String> user = RegisterData.plainEmail();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_12_verifynoLocalPart() {

		Map<String, String> user = RegisterData.noLocalPart();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_13_verifyEmailonlyLocalPart() {

		Map<String, String> user = RegisterData.onlyLocalPart();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_14_verifydoubleAt() {

		Map<String, String> user = RegisterData.doubleAt();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_15_verifyEmailwithoutTopLevelDomain() {

		Map<String, String> user = RegisterData.withoutTopLevelDomain();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 16, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_16_verifytailingWithDot() {

		Map<String, String> user = RegisterData.tailingWithDot();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 17, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_17_verifytopLevelDomainTooShort() {

		Map<String, String> user = RegisterData.topLevelDomainTooShort();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 18, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_18_verifydomainStartsWithHyphen() {

		Map<String, String> user = RegisterData.domainStartsWithHyphen();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 19, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_19_verifydoubleDotInDomain() {

		Map<String, String> user = RegisterData.doubleDotInDomain();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 20, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_20_verifynoAtTheRate() {

		Map<String, String> user = RegisterData.noAtTheRate();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 21, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_21_verifyillegalChars() {

		Map<String, String> user = RegisterData.illegalChars();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 22, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_22_verifytwoConsicutiveDots() {

		Map<String, String> user = RegisterData.twoConsicutiveDots();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 23, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_23_verifydotAtBeginning() {

		Map<String, String> user = RegisterData.dotAtBeginning();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 24, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_24_verifywithQuotation() {

		Map<String, String> user = RegisterData.withQuotation();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 25, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_25_verifyunclosedQuotation() {

		Map<String, String> user = RegisterData.unclosedQuotation();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 26, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_26_verifyveryLengthyLocal() {

		Map<String, String> user = RegisterData.veryLengthyLocal();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 27, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_27_verifyveryLengthyDomain() {

		Map<String, String> user = RegisterData.veryLengthyDomain();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 28, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_28_verifyveryLengthyTLD() {

		Map<String, String> user = RegisterData.veryLengthyTLD();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 29, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_29_verifymissingSecondLevelDomain() {

		Map<String, String> user = RegisterData.missingSecondLevelDomain();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 30, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_30_verifywithComma() {

		Map<String, String> user = RegisterData.withComma();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 31, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_31_verifydoubleConsicutiveAt() {

		Map<String, String> user = RegisterData.doubleConsicutiveAt();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 32, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_32_verifyunderscoreInTLD() {

		Map<String, String> user = RegisterData.underscoreInTLD();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 33, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_33_verifydotBetweenName() {

		Map<String, String> user = RegisterData.dotBetweenName();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.enterPhoneNumber(user.get("phoneNumber"));
		lp = rp.clickSignUpButton();
		lp.verifyRegisterSuccess();
	}

	@Test(priority = 34, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_34_verifyspaceBeforeName() {

		Map<String, String> user = RegisterData.spaceBeforeName();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.enterPhoneNumber(user.get("phoneNumber"));
		lp = rp.clickSignUpButton();
		lp.verifyRegisterSuccess();
	}

	@Test(priority = 35, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_35_verifyspaceAfterName() {

		Map<String, String> user = RegisterData.spaceAfterName();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.enterPhoneNumber(user.get("phoneNumber"));
		lp = rp.clickSignUpButton();
		lp.verifyRegisterSuccess();
	}

	@Test(priority = 36, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_36_verifydotBeforeAt() {

		Map<String, String> user = RegisterData.dotBeforeAt();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 37, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSR_37_verifywithoutDomain() {

		Map<String, String> user = RegisterData.withoutDomain();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyInvalidEmailAndPassword();
	}
}
