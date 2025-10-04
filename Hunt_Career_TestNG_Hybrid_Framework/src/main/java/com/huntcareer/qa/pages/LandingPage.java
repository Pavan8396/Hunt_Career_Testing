package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//h2[contains(text(), 'I am a Job Seeker')]")
	private WebElement jobSeekerRole;

	@FindBy(xpath = "//h2[contains(text(), 'I am an Employer')]")
	private WebElement employerRole;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public HomePage clickOnJobSeekerRole() {
		wait.until(ExpectedConditions.elementToBeClickable(jobSeekerRole)).click();
		return new HomePage(driver);
	}
	
	public EmployerRegisterPage clickOnEmployerRole() {
		wait.until(ExpectedConditions.elementToBeClickable(employerRole)).click();
		return new EmployerRegisterPage(driver);
	}
}
