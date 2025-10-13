package com.huntcareer.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerPostJobPage;
import com.huntcareer.qa.pages.LandingPage;

public class EmployerJobPost extends Base {
	WebDriver driver;
	EmployerPostJobPage jp;
	LandingPage ldp;
	EmployerLoginPage elp;
	
	public EmployerJobPost() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
		ldp = new LandingPage(driver);
		ldp.clickOnEmployerRole();
		elp = new EmployerLoginPage(driver);
		elp.enterEmail();
		
	}
}
