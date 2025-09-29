package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//div[contains(text(), 'Logged in successfully!')]")
	private WebElement successLoginMessage;
	
	@FindBy(xpath = "//a[contains(text(), 'Login')]")
	private WebElement loginHomeLink;
	
	@FindBy(xpath = "//a[contains(text(), 'Signup')]")
	private WebElement registerHomeLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void verifySuccessMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(successLoginMessage)).getText(),
				"Logged in successfully!", "Message Text Mismatch");
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(successLoginMessage)),
				"Success message should not be visible anymore");
	}
	
	public void clickOnLoginHomeLink() {
		loginHomeLink.click();
	}
	
	public void clickOnRegisterHomeLink() {
		registerHomeLink.click();
	}
}
