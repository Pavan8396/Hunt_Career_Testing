package com.huntcareer.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.utils.Utilities;

public class Login extends Base {
	public WebDriver driver;
	LoginPage lp;
	HomePage hp;
	LandingPage ldp;

	public Login() {
		super();
	}

	@BeforeMethod
	public void setup() {
		// loadPropertiesFile();
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		ldp = new LandingPage(driver);
		hp = ldp.clickOnJobSeekerRole();
		hp.clickOnLoginHomeLink();
		// driver.get(loadPropertiesFile().getProperty("url")+"/login");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "testdata")
	public Object[][] fromExcelverifyWithValidData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 1, dataProvider = "testdata", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)

	public void TCJSL_1_verifyWithValidDataUsingExcel(String email, String password) {
		lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(password);
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();

		/*
		 * Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By
		 * .xpath("//div[contains(text(),'Logged in successfully!')]"))).isDisplayed());
		 * 
		 * Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(
		 * By.xpath("//div[contains(text(), 'Logged in successfully!')]"))).getText(),
		 * "Logged in successfully!", "Message Text Mismatch");
		 * Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(
		 * By.xpath("//div[contains(text(), 'Logged in successfully!')]")))
		 * ,"Success message should not be visible anymore");
		 */
	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_3_verifyWithValidData() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();

		/*
		 * Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By
		 * .xpath("//div[contains(text(), 'Logged in
		 * successfully!')]"))).isDisplayed()); Assert.assertEquals(wait
		 * .until(ExpectedConditions .visibilityOfElementLocated(By.
		 * xpath("//div[contains(text(), 'Logged in successfully!')]"))) .getText(),
		 * "Logged in successfully!", "Message Text Mismatch"); Assert.assertTrue(
		 * wait.until(ExpectedConditions .invisibilityOfElementLocated(By.
		 * xpath("//div[contains(text(), 'Logged in successfully!')]"))),
		 * "Success message should not be visible anymore");
		 */
	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_4_verifyEmailWithDotInbetweenUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithDotInbetweenUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_5_verifyEmailWithSpaceBeforeUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithSpaceBeforeUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
	}

	@Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_6_verifyEmailWithSpaceAfterUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithSpaceAfterUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_2_verifyWithInvalidUser() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidUser"));
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_7_verifyShortPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("shortPassword"));
		lp.clickLoginButton();
		lp.verifyPasswordMustHaveAtLeast8CharactersLong();
	}

	@Test(priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_8_verifyinvalidEmailAndValidPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidUser"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 9, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_9_verifyValidEmailAndInvalidPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 10, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_10_verifyNoEmailAndNoPassword() {
		lp = new LoginPage(driver);
		lp.clickLoginButton();
		lp.verifyEmailIsRequired();
	}

	@Test(priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_11_verifyNoEmail() {
		lp = new LoginPage(driver);
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyEmailIsRequired();
	}

	@Test(priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_12_verifyNoPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.clickLoginButton();
		lp.verifyPasswordIsRequired();
	}

	@Test(priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_13_verifyinvalidEmailplain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(plain)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_14_verifyinvalidEmailNoLocalPart() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(noLocalPart)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_15_verifyinvalidEmailOnlyEmailPart() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(onlyEmailPart)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 16, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_16_verifyinvalidEmailDoubleAt() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(doubleAt)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 17, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_17_verifyinvalidEmailNoTopDoamin() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(noTopDomain)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 18, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_18_verifyinvalidEmailTailingDot() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(trailingDot)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 19, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_19_verifyinvalidEmailShortTLD() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(shortTLD)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 20, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_20_verifyinvalidEmailDomainStartsHyphen() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(domainStartsHyphen)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 21, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_21_verifyinvalidEmaildoubleDotDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(doubleDotDomain)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 22, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_22_verifyinvalidEmailNoAtSymbol() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(noAtSymbol)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 23, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_23_verifyinvalidEmailIllegalChars() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(illegalChars)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 24, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_24_verifyinvalidEmailDotAfterName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(dotAfterName)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 25, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_25_verifyinvalidEmailTwoConsicutiveDots() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(twoConsicutiveDots)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 26, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_26_verifyinvalidEmailDotAtStart() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(dotAtStart)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 27, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_27_verifyinvalidEmailQuoted() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(quoted)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 28, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_28_verifyinvalidEmailUnclosedQuote() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(unclosedQuote)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 29, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_29_verifyinvalidEmailLongEmail() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(longEmail)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 30, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_30_verifyinvalidEmailLongDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(longDomain)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 31, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_31_verifyinvalidEmailLongTLD() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(longTLD)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 32, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_32_verifyinvalidEmailMissingSecondLevelDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(missingSecondLevelDomain)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 33, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_33_verifyinvalidEmailCommaInEmail() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(commaInEmail)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 34, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_34_verifyinvalidEmailConsicutiveDoubleAt() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(consicutiveDoubleAt)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 35, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_35_verifyinvalidEmailUnderscoreTLD() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(underscoreTLD)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 36, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCJSL_36_verifyinvalidEmailNoDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail(noDomain)"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}
}
