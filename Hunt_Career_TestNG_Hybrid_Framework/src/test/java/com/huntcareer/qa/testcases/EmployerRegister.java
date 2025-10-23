package com.huntcareer.qa.testcases;

import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerRegisterPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.testdata.EmployerRegisterData;

public class EmployerRegister extends Base {
	EmployerRegisterPage erp;
	EmployerLoginPage elp;
	LandingPage ldp;
	LoginPage lp;

	public EmployerRegister() {
		super();
	}

	@BeforeMethod
	public void setupEmployerRegister() {
		ldp = new LandingPage(driver);
		erp = ldp.clickOnEmployerRole();
		erp.clickSignupLink();
		erp = new EmployerRegisterPage(driver);
	}

	@Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_1_verifyRegisterWithValidData() {

		Map<String, String> user = EmployerRegisterData.validUser();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		elp = erp.clickSignUpButton();
		elp.verifyRegisterSuccess();
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_2_verifyDuplicateData() {

		Map<String, String> user = EmployerRegisterData.duplicateUser();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyUserAlreadyExistsMessage();
	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_3_verifyNoFirstNameData() {

		Map<String, String> user = EmployerRegisterData.noName();
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyBlankCompanyNameMessage();
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_5_verifyNoEmailData() {

		Map<String, String> user = EmployerRegisterData.noEmail();
		erp.enterCompanyName(user.get("name"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyBlankEmailMessage();
	}

	@Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_6_verifyNoPasswordData() {

		Map<String, String> user = EmployerRegisterData.noPassword();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
	}

	@Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_7_verifyPasswordWithLessCharacters() {

		Map<String, String> user = EmployerRegisterData.passwordWithLessCharacters();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
	}

	@Test(priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_8_verifyRegisterWithoutConfirmPasswordData() {

		Map<String, String> user = EmployerRegisterData.noConfirmPassword();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.clickSignUpButton();
		erp.verifyPasswordDoNotMatchMessage();
	}

	@Test(priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_11_verifyplainEmail() {

		Map<String, String> user = EmployerRegisterData.plainEmail();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_12_verifynoLocalPart() {

		Map<String, String> user = EmployerRegisterData.noLocalPart();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_13_verifyEmailonlyLocalPart() {

		Map<String, String> user = EmployerRegisterData.onlyLocalPart();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_14_verifydoubleAt() {

		Map<String, String> user = EmployerRegisterData.doubleAt();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_15_verifyEmailwithoutTopLevelDomain() {

		Map<String, String> user = EmployerRegisterData.withoutTopLevelDomain();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 16, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_16_verifytailingWithDot() {

		Map<String, String> user = EmployerRegisterData.tailingWithDot();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 17, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_17_verifytopLevelDomainTooShort() {

		Map<String, String> user = EmployerRegisterData.topLevelDomainTooShort();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 18, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_18_verifydomainStartsWithHyphen() {

		Map<String, String> user = EmployerRegisterData.domainStartsWithHyphen();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 19, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_19_verifydoubleDotInDomain() {

		Map<String, String> user = EmployerRegisterData.doubleDotInDomain();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 20, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_20_verifynoAtTheRate() {

		Map<String, String> user = EmployerRegisterData.noAtTheRate();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 21, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_21_verifyillegalChars() {

		Map<String, String> user = EmployerRegisterData.illegalChars();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 22, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_22_verifytwoConsicutiveDots() {

		Map<String, String> user = EmployerRegisterData.twoConsicutiveDots();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 23, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_23_verifydotAtBeginning() {

		Map<String, String> user = EmployerRegisterData.dotAtBeginning();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 24, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_24_verifywithQuotation() {

		Map<String, String> user = EmployerRegisterData.withQuotation();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 25, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_25_verifyunclosedQuotation() {

		Map<String, String> user = EmployerRegisterData.unclosedQuotation();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 26, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_26_verifyveryLengthyLocal() {

		Map<String, String> user = EmployerRegisterData.veryLengthyLocal();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 27, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_27_verifyveryLengthyDomain() {

		Map<String, String> user = EmployerRegisterData.veryLengthyDomain();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 28, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_28_verifyveryLengthyTLD() {

		Map<String, String> user = EmployerRegisterData.veryLengthyTLD();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 29, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_29_verifymissingSecondLevelDomain() {

		Map<String, String> user = EmployerRegisterData.missingSecondLevelDomain();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 30, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_30_verifywithComma() {

		Map<String, String> user = EmployerRegisterData.withComma();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 31, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_31_verifydoubleConsicutiveAt() {

		Map<String, String> user = EmployerRegisterData.doubleConsicutiveAt();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 32, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_32_verifyunderscoreInTLD() {

		Map<String, String> user = EmployerRegisterData.underscoreInTLD();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 33, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_33_verifydotBetweenName() {

		Map<String, String> user = EmployerRegisterData.dotBetweenName();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		elp = erp.clickSignUpButton();
		elp.verifyRegisterSuccess();
	}

	@Test(priority = 34, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_34_verifyspaceBeforeName() {

		Map<String, String> user = EmployerRegisterData.spaceBeforeName();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		elp = erp.clickSignUpButton();
		elp.verifyRegisterSuccess();
	}

	@Test(priority = 35, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_35_verifyspaceAfterName() {

		Map<String, String> user = EmployerRegisterData.spaceAfterName();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		elp = erp.clickSignUpButton();
		elp.verifyRegisterSuccess();
	}

	@Test(priority = 36, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_36_verifydotBeforeAt() {

		Map<String, String> user = EmployerRegisterData.dotBeforeAt();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 37, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCER_37_verifywithoutDomain() {

		Map<String, String> user = EmployerRegisterData.withoutDomain();
		erp.enterCompanyName(user.get("name"));
		erp.enterEmail(user.get("email"));
		erp.enterPassword(user.get("password"));
		erp.enterConfirmPassword(user.get("confirmPassword"));
		erp.clickSignUpButton();
		erp.verifyInvalidEmailAndPassword();
	}
}
