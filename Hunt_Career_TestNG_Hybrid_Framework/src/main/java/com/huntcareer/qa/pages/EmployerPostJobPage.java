package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.huntcareer.qa.utils.Utilities;

public class EmployerPostJobPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//span[contains(text(), 'Post a Job')]")
	private WebElement postAJobNavigationButton;
	
	@FindBy(xpath = "//input[@id='title']")
	private WebElement jobTitleField;
	
	@FindBy(xpath = "//input[@id='company']")
	private WebElement companyTitleField;
	
	@FindBy(xpath = "//textarea[@id='description']")
	private WebElement descriptionField;
	
	@FindBy(xpath = "//input[@id='candidate_required_location']")
	private WebElement locationField;
	
	@FindBy(xpath = "//button[contains(text(), 'Post Job')]")
	private WebElement clickPostJobSubmitButton;
	
	@FindBy(xpath = "//div[contains(text(), 'Job posted successfully!')]")
	private WebElement jobPostSuccessMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Job title is required')]")
	private WebElement jobTitleRequiredErrorMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Company name is required')]")
	private WebElement companyNameRequiredErrorMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Description is required')]")
	private WebElement descriptionRequiredErrorMessage;
	
	@FindBy(xpath = "//div[contains(text(), 'Location is required')]")
	private WebElement locationRequiredErrorMessage;
	
	public EmployerPostJobPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void clickPostAJobNavigationButton() {
		wait.until(ExpectedConditions.elementToBeClickable(postAJobNavigationButton)).click();
	}
	
	public void enterJobTitle(String title) {
		wait.until(ExpectedConditions.visibilityOf(jobTitleField)).sendKeys(title);
	}
	
	public void enterCompanyName(String companyName) {
		wait.until(ExpectedConditions.visibilityOf(companyTitleField)).sendKeys(companyName);
	}
	
	public void enterDescription(String description) {
		wait.until(ExpectedConditions.visibilityOf(descriptionField)).sendKeys(description);
	}
	
	public void enterLocation(String location) {
		wait.until(ExpectedConditions.visibilityOf(locationField)).sendKeys(location);
	}

	public void selectJobTypeOption() {
		 By jobTypeOption = By.xpath("//option[contains(text(),'" + Utilities.getRandomJobType() + "')]");
		    wait.until(ExpectedConditions.visibilityOfElementLocated(jobTypeOption)).click();
	}
	
	public void clickPostJobSubmitButton() {
		wait.until(ExpectedConditions.elementToBeClickable(clickPostJobSubmitButton)).click();
	}
	
	public void verifyJobPostSuccess() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(jobPostSuccessMessage)).getText(),
				"Job posted successfully!", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(jobPostSuccessMessage)),
				"Job posted successfully! should not be visible anymore");
	}
	
	public void verifyJobTitleRequiredErrorMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(jobTitleRequiredErrorMessage)).getText(),
				"Job title is required", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(jobTitleRequiredErrorMessage)),
				"Job title is required should not be visible anymore");
	}
	
	public void verifyCompanyNameRequiredErrorMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(companyNameRequiredErrorMessage)).getText(),
				"Company name is required", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(companyNameRequiredErrorMessage)),
				"Company name is required should not be visible anymore");
	}
	
	public void verifyDescriptionRequiredErrorMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(descriptionRequiredErrorMessage)).getText(),
				"Description is required", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(descriptionRequiredErrorMessage)),
				"Description is required should not be visible anymore");
	}
	
	public void verifyLocationRequiredErrorMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(locationRequiredErrorMessage)).getText(),
				"Location is required", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(locationRequiredErrorMessage)),
				"Location is required should not be visible anymore");
	}
}
