package com.huntcareer.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SavePage extends PageBase {

    public SavePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSavedJobCard(String jobTitle) {
        return driver.findElement(By.xpath("//h3[contains(text(), '" + jobTitle + "')]"));
    }
}