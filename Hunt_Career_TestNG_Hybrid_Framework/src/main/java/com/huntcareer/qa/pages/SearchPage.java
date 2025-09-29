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

public class SearchPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//input[@placeholder='Search jobs by title, company, or keyword...']")
	private WebElement searchInputField;

	@FindBy(xpath = "//button[@aria-label='Search']")
	private WebElement searchButton;

	@FindBy(xpath = "//button[@aria-label='Toggle location filter dropdown']")
	private WebElement locationButton;

	@FindBy(xpath = "//input[@placeholder='Search location...']")
	private WebElement locationSearchInput;

	@FindBy(xpath = "//button[@aria-label='Toggle job type filter dropdown']")
	private WebElement jobTypeButton;

	@FindBy(xpath = "//input[@placeholder='Search job type...']")
	private WebElement jobTypeSearchInput;

	@FindBy(xpath = "//button[@aria-label='Clear all filters']")
	private WebElement clearAllFiltersButton;
	
	@FindBy(xpath = "//p[contains(text(),'No jobs found.')]")
	private WebElement noJobsFoundMessage;

	@FindBy(xpath = "//div[contains(@class, 'animate-in')]//p[contains(text(), 'Logged in successfully')]")
	public WebElement loginSuccessMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void search(String keyword) {
		searchInputField.sendKeys(keyword);
		searchButton.click();
	}

	public void search(String keyword, String location, String jobType) {
		searchInputField.sendKeys(keyword);
		if (location != null && !location.isEmpty()) {
			locationButton.click();
			selectLocationOption(location);
			locationButton.click();
		}
		if (jobType != null && !jobType.isEmpty()) {
			jobTypeButton.click();
			selectJobTypeOption(jobType);
		}
		searchButton.click();
	}
	
	public void selectLocationOption(String location) {
		driver.findElement(By.xpath("//label[contains(., '" + location + "')]/input")).click();
	}
	
	public void selectJobTypeOption(String jobType) {
		driver.findElement(By.xpath("//label[contains(., '" + jobType + "')]/input")).click();
	}

	public void verifySearchTag(String searchTerm) {
        WebElement tag = driver.findElement(By.xpath("//span[contains(text(), '" + searchTerm + "')]"));
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(tag)).isDisplayed());
    }

    public void verifyLocationTag(String location) {
		WebElement tag = driver.findElement(By.xpath("//span[contains(text(), '" + location + "')]"));
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(tag)).isDisplayed());
    }

    public void verifyJobTypeTag(String jobType) {
		WebElement tag = driver.findElement(By.xpath("//span[contains(text(), '" + jobType + "')]"));
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(tag)).isDisplayed());
    }

	public void verifyNoJobsFoundMessage() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(noJobsFoundMessage)).isDisplayed());
	}

	public void verifyLoginSuccessMessage() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(loginSuccessMessage)).isDisplayed());
	}
}