package com.huntcareer.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EmployerRegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    // Page Factory - OR:
    @FindBy(name = "name")
    WebElement name;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(name = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement registerButton;

    @FindBy(xpath = "//div[contains(text(),'User already exists')]")
    private WebElement duplicateErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Name is required.')]")
    private WebElement nameBlankMessage;

    @FindBy(xpath = "//div[contains(text(),'Email is required.')]")
    private WebElement emailBlankMessage;

    @FindBy(xpath = "//div[contains(text(),'Password must be at least 8 characters long.')]")
	private WebElement passwordMustHaveRequiredNumberCharactersMessage;

    // Initializing the Page Objects:
    public EmployerRegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions:
    public String validateRegisterPageTitle() {
        return driver.getTitle();
    }

    public EmployerLoginPage register(String nm, String em, String pwd, String confirmPwd) {
        name.sendKeys(nm);
        email.sendKeys(em);
        password.sendKeys(pwd);
        confirmPassword.sendKeys(confirmPwd);
        registerButton.click();
        return new EmployerLoginPage(driver);
    }

    public void enterName(String nameText) {
        name.sendKeys(nameText);
    }

    public void enterEmail(String emailText) {
        email.sendKeys(emailText);
    }

    public void enterPassword(String passwordText) {
        password.sendKeys(passwordText);
    }

    public EmployerLoginPage clickRegisterButton() {
        registerButton.click();
        return new EmployerLoginPage(driver);
    }

    public void verifyUserAlreadyExistsMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(duplicateErrorMessage)).getText(),
				"User already exists", "Message Text Mismatch");
	}

    public void verifyBlankNameMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(nameBlankMessage)).getText(),
				"Name is required.", "Message Text Mismatch");
	}

    public void verifyBlankEmailMessage() {
		Assert.assertEquals(wait.until(ExpectedConditions.visibilityOf(emailBlankMessage)).getText(),
				"Email is required.", "Message Text Mismatch");
	}

    public void verifyPasswordMustBeAtLeast8CharctersLongMessage() {
		Assert.assertEquals(
				wait.until(ExpectedConditions.visibilityOf(passwordMustHaveRequiredNumberCharactersMessage)).getText(),
				"Password must be at least 8 characters long.", "Message Text Mismatch");
	}
}