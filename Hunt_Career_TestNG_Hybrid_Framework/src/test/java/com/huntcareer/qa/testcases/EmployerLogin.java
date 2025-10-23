package com.huntcareer.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.utils.Utilities;

public class EmployerLogin extends Base {
	EmployerLoginPage elp;
	HomePage hp;
	LandingPage ldp;

	public EmployerLogin() {
		super();
	}

	@BeforeMethod
	public void setupEmployerLogin() {
		ldp = new LandingPage(driver);
		ldp.clickOnEmployerRole();
		elp = new EmployerLoginPage(driver);
	}

	@DataProvider(name = "testdata")
	public Object[][] fromExcelverifyWithValidData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(enabled = false, priority = 1, dataProvider = "testdata", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)

	public void TCEL_1_verifyWithValidDataUsingExcel(String email, String password) {
		elp.enterEmail(email);
		elp.enterPassword(password);
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage();

	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_3_verifyWithValidData() {
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage();

	}

	@Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_4_verifyEmailWithDotInbetweenUserName() {
		elp.enterEmail(dataProp.getProperty("EmployeremailWithDotInbetweenUserName"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage();
	}

	@Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_5_verifyEmailWithSpaceBeforeUserName() {
		elp.enterEmail(dataProp.getProperty("EmployeremailWithSpaceBeforeUserName"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage();
	}

	@Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_6_verifyEmailWithSpaceAfterUserName() {
		elp.enterEmail(dataProp.getProperty("EmployeremailWithSpaceAfterUserName"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		hp = elp.clickLoginButton();
		hp.verifySuccessMessage();
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_2_verifyWithInvalidUser() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidUser"));
		elp.enterPassword(dataProp.getProperty("EmployerinvalidPassword"));
		elp.clickLoginButton();
		elp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_7_verifyShortPassword() {
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.enterPassword(dataProp.getProperty("EmployershortPassword"));
		elp.clickLoginButton();
		elp.verifyPasswordMustHaveAtLeast8CharactersLong();
	}

	@Test(priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_8_verifyinvalidEmailAndValidPassword() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidUser"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 9, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_9_verifyValidEmailAndInvalidPassword() {
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.enterPassword(dataProp.getProperty("EmployerinvalidPassword"));
		elp.clickLoginButton();
		elp.verifyInvalidEmailAndPassword();
	}

	@Test(priority = 10, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_10_verifyNoEmailAndNoPassword() {
		elp.clickLoginButton();
		elp.verifyEmailIsRequired();
	}

	@Test(priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_11_verifyNoEmail() {
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyEmailIsRequired();
	}

	@Test(priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_12_verifyNoPassword() {
		elp.enterEmail(dataProp.getProperty("EmployervalidEmail"));
		elp.clickLoginButton();
		elp.verifyPasswordIsRequired();
	}

	@Test(priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_13_verifyinvalidEmaielplain() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(plain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_14_verifyinvalidEmailNoLocalpart() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noLocalPart)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_15_verifyinvalidEmailOnlyEmailpart() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(onlyEmailPart)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 16, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_16_verifyinvalidEmailDoubleAt() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(doubleAt)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 17, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_17_verifyinvalidEmailNoTopDoamin() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noTopDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 18, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_18_verifyinvalidEmailTailingDot() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(trailingDot)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 19, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_19_verifyinvalidEmailShortTLD() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(shortTLD)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 20, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_20_verifyinvalidEmailDomainStartsHyphen() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(domainStartsHyphen)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 21, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_21_verifyinvalidEmaildoubleDotDomain() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(doubleDotDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 22, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_22_verifyinvalidEmailNoAtSymbol() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noAtSymbol)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 23, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_23_verifyinvalidEmailIllegalChars() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(illegalChars)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 24, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_24_verifyinvalidEmailDotAfterName() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(dotAfterName)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 25, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_25_verifyinvalidEmailTwoConsicutiveDots() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(twoConsicutiveDots)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 26, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_26_verifyinvalidEmailDotAtStart() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(dotAtStart)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 27, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_27_verifyinvalidEmailQuoted() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(quoted)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 28, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_28_verifyinvalidEmailUnclosedQuote() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(unclosedQuote)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 29, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_29_verifyinvalidEmailLongEmail() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(longEmail)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 30, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_30_verifyinvalidEmailLongDomain() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(longDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 31, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_31_verifyinvalidEmailLongTLD() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(longTLD)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 32, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_32_verifyinvalidEmailMissingSecondLevelDomain() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(missingSecondLevelDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 33, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_33_verifyinvalidEmailCommaInEmail() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(commaInEmail)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 34, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_34_verifyinvalidEmailConsicutiveDoubleAt() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(consicutiveDoubleAt)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 35, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_35_verifyinvalidEmailUnderscoreTLD() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(underscoreTLD)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}

	@Test(priority = 36, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TCEL_36_verifyinvalidEmailNoDomain() {
		elp.enterEmail(dataProp.getProperty("EmployerinvalidEmail(noDomain)"));
		elp.enterPassword(dataProp.getProperty("EmployervalidPassword"));
		elp.clickLoginButton();
		elp.verifyPleaseEnterValidEmailAddress();
	}
}
