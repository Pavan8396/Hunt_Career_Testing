package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends PageBase {

    @FindBy(xpath = "//h2[contains(text(), 'I am a Job Seeker')]")
    private WebElement jobSeekerRole;

    @FindBy(xpath = "//h2[contains(text(), 'I am an Employer')]")
    private WebElement employerRole;

    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage clickOnJobSeekerRole() {
        clickElement(jobSeekerRole);
        return new HomePage(driver);
    }

    public EmployerRegisterPage clickOnEmployerRole() {
        clickElement(employerRole);
        return new EmployerRegisterPage(driver);
    }
}
