package com.huntcareer.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.huntcareer.qa.utils.Utilities;

public class EmployerPostJobPage extends PageBase {

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
    public WebElement jobPostSuccessMessage;

    @FindBy(xpath = "//div[contains(text(), 'Job title is required')]")
    public WebElement jobTitleRequiredErrorMessage;

    @FindBy(xpath = "//div[contains(text(), 'Company name is required')]")
    public WebElement companyNameRequiredErrorMessage;

    @FindBy(xpath = "//div[contains(text(), 'Description is required')]")
    public WebElement descriptionRequiredErrorMessage;

    @FindBy(xpath = "//div[contains(text(), 'Location is required')]")
    public WebElement locationRequiredErrorMessage;

    public EmployerPostJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickPostAJobNavigationButton() {
        clickElement(postAJobNavigationButton);
    }

    public void enterJobTitle(String title) {
        typeInElement(jobTitleField, title);
    }

    public void enterCompanyName(String companyName) {
        typeInElement(companyTitleField, companyName);
    }

    public void enterDescription(String description) {
        typeInElement(descriptionField, description);
    }

    public void enterLocation(String location) {
        typeInElement(locationField, location);
    }

    public void selectJobTypeOption() {
        By jobTypeOption = By.xpath("//option[contains(text(),'" + Utilities.getRandomJobType() + "')]");
        clickElement(driver.findElement(jobTypeOption));
    }

    public void clickPostJobSubmitButton() {
        clickElement(clickPostJobSubmitButton);
    }
}
