package com.huntcareer.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.utils.Utilities;

public class EmployerLogin extends Base {
	public WebDriver driver;
	EmployerLoginPage elp;
	HomePage hp;
	LandingPage ldp;

	public EmployerLogin() {
		super();
	}

	@BeforeMethod
	public void setup() {
		// loadPropertiesFile();
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		ldp = new LandingPage(driver);
		ldp.clickOnEmployerRole();
		// driver.gaet(loadPropertiesFile().getProperty("url")+"/login");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "testdata")
	public Object[][] fromExcelverifyWithValidData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login", prop.getProperty("excelFilePath"));
		return data;
	}

	@Test(enabled = false, priority = 1, dataProvider = "testdata", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)

	public void TC_1_verifyWithValidDataUsingExcel(String email, String password) {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(email);
		elp.enterPassword(password);
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage(dataProp.getProperty("LoginSuccessMessage"));
	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_3_verifyWithValidData() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage(dataProp.getProperty("LoginSuccessMessage"));
	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_4_verifyEmailWithDotInbetweenUserName() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployeremailWithDotInbetweenUserName"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage(dataProp.getProperty("LoginSuccessMessage"));
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_5_verifyEmailWithSpaceBeforeUserName() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployeremailWithSpaceBeforeUserName"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage(dataProp.getProperty("LoginSuccessMessage"));
	}

	@Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_6_verifyEmailWithSpaceAfterUserName() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployeremailWithSpaceAfterUserName"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage(dataProp.getProperty("LoginSuccessMessage"));
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_2_verifyWithInvalidUser() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidUser"));
		elp.enterPassword(dataProp.getProperty("EmployerinvalidPassword"));
		elp.clickLoginButton();
		elp.verifyInvalidEmailAndPassword(dataProp.getProperty("InvalidEmailOrPasswordMessage"));
	}

	@Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_7_verifyShortPassword() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.enterPassword(dataProp.getProperty("EmployershortPassword"));
		elp.clickLoginButton();
		elp.verifyPasswordMustHaveAtLeast8CharactersLong(dataProp.getProperty("PasswordMustHaveAtLeast8CharactersLongMessage"));
	}

	@Test(priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_8_verifyinvalidEmailAndValidPassword() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidUser"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyInvalidEmailAndPassword(dataProp.getProperty("InvalidEmailOrPasswordMessage"));
	}

	@Test(priority = 9, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_9_verifyValidEmailAndInvalidPassword() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.enterPassword(dataProp.getProperty("EmployerinvalidPassword"));
		elp.clickLoginButton();
		elp.verifyInvalidEmailAndPassword(dataProp.getProperty("InvalidEmailOrPasswordMessage"));
	}

	@Test(priority = 10, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_10_verifyNoEmailAndNoPassword() {
		elp = new EmployerLoginPage(driver);
		elp.clickLoginButton();
		elp.verifyEmailIsRequired(dataProp.getProperty("EmailIsRequiredMessage"));
	}

	@Test(priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_11_verifyNoEmail() {
		elp = new EmployerLoginPage(driver);
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyEmailIsRequired(dataProp.getProperty("EmailIsRequiredMessage"));
	}

	@Test(priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_12_verifyNoPassword() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.clickLoginButton();
		elp.verifyPasswordIsRequired(dataProp.getProperty("PasswordIsRequiredMessage"));
	}

	@Test(priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_13_verifyinvalidEmaielplain() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(plain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_14_verifyinvalidEmailNoLocalpart() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noLocalPart)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_15_verifyinvalidEmailOnlyEmailpart() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(onlyEmailPart)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 16, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_16_verifyinvalidEmailDoubleAt() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(doubleAt)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 17, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_17_verifyinvalidEmailNoTopDoamin() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noTopDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 18, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_18_verifyinvalidEmailTailingDot() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(trailingDot)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 19, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_19_verifyinvalidEmailShortTLD() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(shortTLD)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 20, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_20_verifyinvalidEmailDomainStartsHyphen() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(domainStartsHyphen)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 21, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_21_verifyinvalidEmaildoubleDotDomain() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(doubleDotDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 22, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_22_verifyinvalidEmailNoAtSymbol() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noAtSymbol)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 23, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_23_verifyinvalidEmailIllegalChars() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(illegalChars)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 24, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_24_verifyinvalidEmailDotAfterName() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(dotAfterName)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 25, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_25_verifyinvalidEmailTwoConsicutiveDots() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(twoConsicutiveDots)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 26, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_26_verifyinvalidEmailDotAtStart() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(dotAtStart)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 27, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_27_verifyinvalidEmailQuoted() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(quoted)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 28, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_28_verifyinvalidEmailUnclosedQuote() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(unclosedQuote)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 29, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_29_verifyinvalidEmailLongEmail() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(longEmail)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 30, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_30_verifyinvalidEmailLongDomain() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(longDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 31, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_31_verifyinvalidEmailLongTLD() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(longTLD)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 32, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_32_verifyinvalidEmailMissingSecondLevelDomain() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(missingSecondLevelDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 33, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_33_verifyinvalidEmailCommaInEmail() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(commaInEmail)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 34, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_34_verifyinvalidEmailConsicutiveDoubleAt() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(consicutiveDoubleAt)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 35, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_35_verifyinvalidEmailUnderscoreTLD() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(underscoreTLD)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}

	@Test(priority = 36, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_36_verifyinvalidEmailNoDomain() {
		elp = new EmployerLoginPage(driver);
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress(dataProp.getProperty("PleaseEnterAValidEmailAddressMessage"));
	}
}