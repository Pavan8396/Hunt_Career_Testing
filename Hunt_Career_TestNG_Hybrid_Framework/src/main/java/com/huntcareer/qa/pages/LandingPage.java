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

	@FindBy(xpath = "//a[normalize-space()='Login as Employer']")
	private WebElement employerLoginButton;

	@FindBy(xpath = "//a[normalize-space()='Register as Employer']")
	private WebElement employerRegisterButton;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public HomePage clickOnJobSeekerRole() {
		jobSeekerRole.click();
		return new HomePage(driver);
	}
	
	public void clickOnEmployerRole() {
		employerRole.click();
	}

	public EmployerLoginPage clickEmployerLoginButton() {
		employerLoginButton.click();
		return new EmployerLoginPage(driver);
	}

	public EmployerRegisterPage clickEmployerRegisterButton() {
		employerRegisterButton.click();
		return new EmployerRegisterPage(driver);
	}
}
