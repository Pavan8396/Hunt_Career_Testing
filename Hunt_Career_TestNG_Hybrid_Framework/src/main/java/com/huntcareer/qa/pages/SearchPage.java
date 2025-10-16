package com.huntcareer.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends PageBase {

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
    public WebElement noJobsFoundMessage;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterKeywordIntoSearchInputField(String searchTerm) {
        typeInElement(SearchInputField, searchTerm);
    }

    public void clickOnSearch() {
        clickElement(SearchButton);
    }

    public void clickLocationButton() {
        clickElement(LocationButton);
    }

    public void enterLocationSearchInput(String locationText) {
        typeInElement(LocationSearchInput, locationText);
    }

    public void selectLocationOption(String location) {
        clickElement(driver.findElement(By.xpath("//label[contains(., '" + location + "')]/input")));
    }

    public void clickJobTypeButton() {
        clickElement(JobTypeButton);
    }

    public void enterJobTypeSearchInput(String jobTypeText) {
        typeInElement(JobTypeSearchInput, jobTypeText);
    }

    public void selectJobTypeOption(String jobType) {
        clickElement(driver.findElement(By.xpath("//label[contains(., '" + jobType + "')]/input")));
    }

    public void clickClearFilterOption() {
        clickElement(ClearAllFiltersButton);
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
}
