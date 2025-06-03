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
	WebDriver driver;
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
}
