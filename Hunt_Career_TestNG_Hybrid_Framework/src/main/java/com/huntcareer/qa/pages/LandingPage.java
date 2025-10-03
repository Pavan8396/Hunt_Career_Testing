package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//h2[contains(text(), 'I am a Job Seeker')]")
	private WebElement jobSeekerRole;

	@FindBy(xpath = "//h2[contains(text(), 'I am a Employer')]")
	private WebElement employerRole;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public HomePage clickOnJobSeekerRole() {
		jobSeekerRole.click();
		return new HomePage(driver);
	}
	
	public HomePage clickOnEmployerRole() {
		employerRole.click();
        return new HomePage(driver);
	}

    public LoginPage clickUserLoginLink() {
        HomePage homePage = clickOnJobSeekerRole();
        return homePage.clickOnLoginHomeLink();
    }

    public RegisterPage clickUserRegisterLink() {
        HomePage homePage = clickOnJobSeekerRole();
        return homePage.clickOnRegisterHomeLink();
    }

    public EmployerLoginPage clickEmployerLoginLink() {
        HomePage homePage = clickOnEmployerRole();
        return homePage.clickOnEmployerLoginHomeLink();
    }

    public EmployerRegisterPage clickEmployerRegisterLink() {
        HomePage homePage = clickOnEmployerRole();
        return homePage.clickOnEmployerRegisterHomeLink();
    }
}