package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//input[@placeholder='Search jobs by title, company, or keyword...']")
	private WebElement SearchInputField;

	@FindBy(xpath = "//button[@aria-label='Search']")
	private WebElement SearchButton;

	@FindBy(xpath = "//button[@aria-label='Toggle location filter dropdown']")
	private WebElement LocationButton;

	@FindBy(xpath = "//input[@placeholder='Search location...']")
	private WebElement LocationSearchInput;

	@FindBy(xpath = "//button[@aria-label='Toggle job type filter dropdown']")
	private WebElement JobTypeButton;

	@FindBy(xpath = "//input[@placeholder='Search job type...']")
	private WebElement JobTypeSearchInput;

	@FindBy(xpath = "//button[@aria-label='Clear all filters']")
	private WebElement ClearAllFiltersButton;
	
	@FindBy(xpath = "//p[contains(text(),'No jobs found.')]")
	private WebElement noJobsFoundMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void enterKeywordIntoSearchInputField(String searchTerm) {
		wait.until(ExpectedConditions.visibilityOf(SearchInputField)).sendKeys(searchTerm);
	}
	
	public void clickOnSearch() {
		wait.until(ExpectedConditions.elementToBeClickable(SearchButton)).click();
	}
	
	public void clickLocationButton() {
		wait.until(ExpectedConditions.elementToBeClickable(LocationButton)).click();
	}
	
	public void enterLocationSearchInput(String locationText) {
		wait.until(ExpectedConditions.visibilityOf(LocationSearchInput)).sendKeys(locationText);
	}
	
	public void selectLocationOption(String location) {
		driver.findElement(By.xpath("//label[contains(., '" + location + "')]/input")).click();
	}
	
	public void clickJobTypeButton() {
		wait.until(ExpectedConditions.elementToBeClickable(JobTypeButton)).click();
	}
	
	public void enterJobTypeSearchInput(String jobTypeText) {
		wait.until(ExpectedConditions.visibilityOf(JobTypeSearchInput)).sendKeys(jobTypeText);
	}
	
	public void selectJobTypeOption(String jobType) {
		driver.findElement(By.xpath("//label[contains(., '" + jobType + "')]/input")).click();
	}
	
	public void clickClearFilterOption() {
		wait.until(ExpectedConditions.elementToBeClickable(ClearAllFiltersButton)).click();
	}

	public WebElement getJobCardTitle(WebElement jobcard) {
		return jobcard.findElement(By.cssSelector("h3.text-lg.font-semibold"));
	}

	public WebElement jobCardCompanyLocation(WebElement jobcard) {
		return jobcard.findElement(By.cssSelector("p.text-gray-600"));
	}

	public WebElement jobCardDescription(WebElement jobcard) {
		return jobcard.findElement(By.cssSelector("p.text-sm.text-gray-700.mt-3.line-clamp-3"));
	}
	
	public void getSearchTag(String searchTerm) {
        driver.findElement(By.xpath("//span[contains(text(), " + searchTerm + ")]")).isDisplayed();
    }

    public void getLocationTag(String location) {
        driver.findElement(By.xpath("//span[contains(text(), " + location + ")]")).isDisplayed();
    }

    public void getJobTypeTag(String jobType) {
        driver.findElement(By.xpath("//span[contains(text(), " + jobType + ")]")).isDisplayed();
    }
    
    
}
