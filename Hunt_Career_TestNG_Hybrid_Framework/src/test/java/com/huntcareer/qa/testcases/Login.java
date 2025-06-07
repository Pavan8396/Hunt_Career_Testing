package com.huntcareer.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.HomePage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.utils.Utilities;

public class Login extends Base {
	public WebDriver driver;
	LoginPage lp;
	HomePage hp;

	public Login() {
		super();
	}

	@BeforeMethod
	public void setup() {
		// loadPropertiesFile();
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.get(prop.getProperty("url") + "/login");
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

	@Test(priority = 1, dataProvider = "testdata")
	public void verifyWithValidDataUsingExcel(String email, String password) {
		lp = new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(password);
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
		hp.verifySuccessMessageIsGone();
		/*Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Logged in successfully!')]"))).isDisplayed());
		
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))).getText(), "Logged in successfully!", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(), 'Logged in successfully!')]"))),"Success message should not be visible anymore");*/
	}

	@Test(priority = 3)
	public void verifyWithValidData() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
		hp.verifySuccessMessageIsGone();
		
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

	@Test(priority = 2)
	public void verifyWithInvalidData() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail"));
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
		lp.verifyInvalidEmailAndPasswordIsGone();
	}
	
	@Test (priority = 4)
	public void verifyShortPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("shortPassword"));
		lp.clickLoginButton();
		lp.verifyPasswordMustHaveAtLeast8CharactersLong();
		lp.verifyPasswordMustHaveAtLeast8CharactersLongIsGone();
	}
	
	@Test (priority = 5)
	public void verifyinvalidEmailAndValidPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("invalidEmail"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
		lp.verifyInvalidEmailAndPasswordIsGone();
	}
	
	@Test (priority = 6)
	public void verifyValidEmailAndInvalidPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.enterPassword(dataProp.getProperty("invalidPassword"));
		lp.clickLoginButton();
		lp.verifyInvalidEmailAndPassword();
		lp.verifyInvalidEmailAndPasswordIsGone();
	}
	
	@Test (priority = 7)
	public void verifyNoEmailAndNoPassword() {
		lp = new LoginPage(driver);
		lp.clickLoginButton();
		lp.verifyEmailIsRequired();
		lp.verifyEmailIsRequiredIsGone();
	}
	
	@Test (priority = 8)
	public void verifyNoEmail() {
		lp = new LoginPage(driver);
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyEmailIsRequired();
		lp.verifyEmailIsRequiredIsGone();
	}
	
	@Test (priority = 9)
	public void verifyNoPassword() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("validEmail"));
		lp.clickLoginButton();
		lp.verifyPasswordIsRequired();
		lp.verifyPasswordIsRequiredIsGone();
	}
	
	@Test (priority = 10)
	public void verifyEmailWithoutTopDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithoutTopDomain"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
		lp.verifyPleaseEnterValidEmailAddressIsGone();
	}
	
	@Test (priority = 11)
	public void verifyEmailWithoutDomain() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithoutDomain"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
		lp.verifyPleaseEnterValidEmailAddressIsGone();
	}
	
	@Test (priority = 12)
	public void verifyEmailWithoutUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithoutUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
		lp.verifyPleaseEnterValidEmailAddressIsGone();
	}
	
	@Test (priority = 13)
	public void verifyEmailWithDoubleAtTheRate() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithDoubleAtTheRate"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		lp.clickLoginButton();
		lp.verifyPleaseEnterValidEmailAddress();
		lp.verifyPleaseEnterValidEmailAddressIsGone();
	}
	
	@Test (priority = 14)
	public void verifyEmailWithDotAfterUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithDotAfterUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
		hp.verifySuccessMessageIsGone();
	}
	
	@Test (priority = 15)
	public void verifyEmailWithDotInbetweenUserName() {
		lp = new LoginPage(driver);
		lp.enterEmail(dataProp.getProperty("emailWithDotInbetweenUserName"));
		lp.enterPassword(dataProp.getProperty("validPassword"));
		hp = lp.clickLoginButton();
		hp.verifySuccessMessage();
		hp.verifySuccessMessageIsGone();
	}
}
