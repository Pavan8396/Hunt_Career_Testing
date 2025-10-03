package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployerRegisterPage {
    WebDriver driver;

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

    // Initializing the Page Objects:
    public EmployerRegisterPage(WebDriver driver) {
        this.driver = driver;
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

    public WebElement getDuplicateErrorMessage() {
        return duplicateErrorMessage;
    }
}