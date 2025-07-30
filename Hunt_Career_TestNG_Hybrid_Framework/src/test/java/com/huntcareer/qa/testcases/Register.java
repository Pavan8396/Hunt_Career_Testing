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
		//ldp = new LandingPage(driver);
		ldp = new LandingPage(getDriver());
		hp = ldp.clickOnJobSeekerRole();
		hp.clickOnRegisterHomeLink();
	}

	@AfterMethod
	public void tearDown() {
		// if (driver != null) {
		// 	driver.quit();	
		// }
		if (getDriver() != null) {
			getDriver().quit();
			unload();
		}
	}

	@Test(priority = 1,  retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_1_verifyRegisterWithValidData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_2_verifyDuplicateData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_3_verifyNoFirstNameData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.noFirstName();
		rp.enterLastName(user.get("lastName"));
		rp.clickFirstNextButton();
		rp.verifyBlankFirstNameMessage();
	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_4_verifyNoLastNameData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.noLastName();
		rp.enterFirstName(user.get("firstName"));
		rp.clickFirstNextButton();
		rp.verifyBlankLastNameMessage();
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_5_verifyNoEmailData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_6_verifyNoPasswordData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_7_verifyPasswordWithLessCharacters() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_8_verifyRegisterWithoutConfirmPasswordData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_9_verifyNoPhoneNumberData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_10_verifyInvalidPhoneNumberData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
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
	public void TC_11_verifyEmailWithoutAllDomainData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.emailWithoutAllDomain();
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
	public void TC_12_verifyWithoutTopLevelDomainData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.emailwithoutTopLevelDomain();
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
	public void TC_13_verifyEmailWithDoubleAtData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.emailwithdoubleAt();
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
	public void TC_14_verifyDotBeforeAtTheRateData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.emailWithdotBeforeAt();
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

	@Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_15_verifyEmailWithoutDomainData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.emailwithoutDomain();
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
	public void TC_16_verifyWithoutUserNameData() {

		//rp = new RegisterPage(driver);
		rp = new RegisterPage(getDriver());
		Map<String, String> user = RegisterData.emailwithoutUserName();
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
