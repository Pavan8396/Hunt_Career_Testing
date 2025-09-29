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

	public void TC_1_verifyWithValidDataUsingExcel(String email, String password) {
		lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(password);
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();

		/*Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Logged in successfully!')]"))).isDisplayed());

		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))).getText(), "Logged in successfully!", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))),"Success message should not be visible anymore");*/
	}

	@Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_3_verifyWithValidData() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();

		/*Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),
		'Logged in successfully!')]"))).isDisplayed());
		Assert.assertEquals(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]")))
				.getText(), "Logged in successfully!", "Message Text Mismatch");
		Assert.assertTrue(
				wait.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))),
				"Success message should not be visible anymore");*/
	}

	@Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_2_verifyWithInvalidData() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail"));
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
	}

	@Test (priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_4_verifyShortPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("shortPassword"));
		lp.clickLoginButton();
		lp.verifyPasswordMustHaveAtLeast8CharactersLong();
	}

	@Test (priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_5_verifyinvalidEmailAndValidPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
	}

	@Test (priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_6_verifyValidEmailAndInvalidPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
	}

	@Test (priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_7_verifyNoEmailAndNoPassword() {
		lp = new LoginPage(driver);
		lp.clickLoginButton();
		lp.verifyEmailIsRequired();
	}

	@Test (priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_8_verifyNoEmail() {
		lp = new LoginPage(driver);
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyEmailIsRequired();
	}

	@Test (priority = 9, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_9_verifyNoPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.clickLoginButton();
		lp.verifyPasswordIsRequired();
	}

	@Test (priority = 10, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_10_verifyEmailWithoutTopDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithoutTopDomain"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test (priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_11_verifyEmailWithoutDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithoutDomain"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test (priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_12_verifyEmailWithoutUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithoutUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test (priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_13_verifyEmailWithDoubleAtTheRate() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithDoubleAtTheRate"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
	}

	@Test (priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_14_verifyEmailWithDotAfterUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithDotAfterUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
	}

	@Test (priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
	public void TC_15_verifyEmailWithDotInbetweenUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithDotInbetweenUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
	}
}
