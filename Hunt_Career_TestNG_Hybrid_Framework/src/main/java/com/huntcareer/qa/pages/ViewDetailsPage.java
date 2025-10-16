package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewDetailsPage extends PageBase {

    @FindBy(xpath = "//button[contains(text(), 'Apply')]")
    public WebElement ApplyButton;

    public ViewDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}