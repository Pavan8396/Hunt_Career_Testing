package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    @FindBy(xpath = "//div[contains(text(),'Logged in successfully!')]")
    private WebElement loginSuccessMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public void enterKeywordIntoSearchInputField(String searchTerm) {
		SearchInputField.sendKeys(searchTerm);
	}
	
	public void clickOnSearch() {
		SearchButton.click();
	}
	
	public void clickLocationButton() {
		LocationButton.click();
	}
	
	public void enterLocationSearchInput(String locationText) {
		LocationSearchInput.sendKeys(locationText);
	}
	
	public void selectLocationOption(String location) {
		driver.findElement(By.xpath("//label[contains(., '" + location + "')]/input")).click();
	}
	
	public void clickJobTypeButton() {
		JobTypeButton.click();
	}
	
	public void enterJobTypeSearchInput(String jobTypeText) {
		JobTypeSearchInput.sendKeys(jobTypeText);
	}
	
	public void selectJobTypeOption(String jobType) {
		driver.findElement(By.xpath("//label[contains(., '" + jobType + "')]/input")).click();
	}
	
	public void clickClearFilterOption() {
		ClearAllFiltersButton.click();
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
	
	public WebElement getSearchTag(String searchTerm) {
        return driver.findElement(By.xpath("//span[contains(text(), '" + searchTerm + "')]"));
    }

    public WebElement getLocationTag(String location) {
        return driver.findElement(By.xpath("//span[contains(text(), '" + location + "')]"));
    }

    public WebElement getJobTypeTag(String jobType) {
        return driver.findElement(By.xpath("//span[contains(text(), '" + jobType + "')]"));
    }
    
    public WebElement getLoginSuccessMessage() {
        return loginSuccessMessage;
    }

    public WebElement getNoJobsFoundMessage() {
        return noJobsFoundMessage;
    }

    public void search(String searchTerm) {
        enterKeywordIntoSearchInputField(searchTerm);
        clickOnSearch();
    }

    public void search(String searchTerm, String location, String jobType) {
        enterKeywordIntoSearchInputField(searchTerm);
        if (location != null) {
            clickLocationButton();
            selectLocationOption(location);
            clickLocationButton();
        }
        if (jobType != null) {
            clickJobTypeButton();
            selectJobTypeOption(jobType);
            clickJobTypeButton();
        }
        clickOnSearch();
    }
}