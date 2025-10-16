package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.huntcareer.qa.base.Base;

public class PageBase extends Base {

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void clickElement(WebElement element) {
        waitForElementToAppear(element);
        element.click();
    }

    public void typeInElement(WebElement element, String text) {
        waitForElementToAppear(element);
        element.sendKeys(text);
    }
}