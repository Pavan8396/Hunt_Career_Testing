package com.huntcareer.qa.testcases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.pages.RegisterPage;
import com.huntcareer.qa.testdata.RegisterData;

public class Register extends Base {
	WebDriver driver;
	RegisterPage rp;
	LoginPage lp;
	LandingPage ldp;
	HomePage hp;
	
	public Register() {
		super();
	}

	@BeforeMethod
	public void setup() {
		// loadPropertiesFile();
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		// driver.get(loadPropertiesFile().getProperty("url")+"/signup");
		driver.get(prop.getProperty("url"));
		ldp = new LandingPage(driver);
		hp = ldp.clickOnJobSeekerRole();
		hp.clickOnRegisterHomeLink();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();	
		}
	}

	@Test(priority = 1,  retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_1_verifyRegisterWithValidData() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_2_verifyDuplicateData() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_3_verifyNoFirstNameData() {

		rp = new RegisterPage(driver);
		Map<String, String> user = RegisterData.noFirstName();
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.verifyBlankFirstNameMessage();
	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_4_verifyNoLastNameData() {

		rp = new RegisterPage(driver);
		Map<String, String> user = RegisterData.noLastName();
		rp.enterFirstName(user.get("firstName"));
		rp.clickFirstNextButton();
		rp.verifyBlankLastNameMessage();
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_5_verifyNoEmailData() {

		rp = new RegisterPage(driver);
		Map<String, String> user = RegisterData.noEmail();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterPassword(user.get("password"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyBlankEmailMessage();
	}

	@Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_6_verifyNoPasswordData() {

		rp = new RegisterPage(driver);
		Map<String, String> user = RegisterData.noPassword();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterConfirmPassword(user.get("confirmPassword"));
		rp.clickSecondNextButton();
		rp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
	}

	@Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_7_verifyPasswordWithLessCharacters() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_8_verifyRegisterWithoutConfirmPasswordData() {

		rp = new RegisterPage(driver);
		Map<String, String> user = RegisterData.noConfirmPassword();
		rp.enterFirstName(user.get("firstName"));
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.enterEmail(user.get("email"));
		rp.enterPassword(user.get("password"));
		rp.clickSecondNextButton();
		rp.verifyPasswordDoNotMatchMessage();
	}

	@Test(priority = 9, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_9_verifyNoPhoneNumberData() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 10, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_10_verifyInvalidPhoneNumberData() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_11_verifyplainEmail() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_12_verifynoLocalPart() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_13_verifyEmailonlyLocalPart() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_14_verifydoubleAt() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_15_verifyEmailwithoutTopLevelDomain() {

		rp = new RegisterPage(driver);
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
	
	@Test(priority = 16, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_16_verifytailingWithDot() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 17, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_17_verifytopLevelDomainTooShort() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 18, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_18_verifydomainStartsWithHyphen() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 19, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_19_verifydoubleDotInDomain() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 20, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_20_verifynoAtTheRate() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 21, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_21_verifyillegalChars() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 22, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_22_verifytwoConsicutiveDots() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 23, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_23_verifydotAtBeginning() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 24, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_24_verifywithQuotation() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 25, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_25_verifyunclosedQuotation() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 26, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_26_verifyveryLengthyLocal() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 27, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_27_verifyveryLengthyDomain() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 28, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_28_verifyveryLengthyTLD() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 29, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_29_verifymissingSecondLevelDomain() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 30, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_30_verifywithComma() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 31, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_31_verifydoubleConsicutiveAt() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 32, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_32_verifyunderscoreInTLD() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 33, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_33_verifydotBetweenName() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 34, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_34_verifyspaceBeforeName() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 35, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_35_verifyspaceAfterName() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 36, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_36_verifydotBeforeAt() {

		rp = new RegisterPage(driver);
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

	@Test(priority = 37, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class, groups = "Registration")
	public void TC_37_verifywithoutDomain() {

		rp = new RegisterPage(driver);
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
