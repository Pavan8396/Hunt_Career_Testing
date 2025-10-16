package com.huntcareer.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends PageBase {

    @FindBy(xpath = "//div[contains(text(), 'Logged in successfully!')]")
    public WebElement successLoginMessage;

    @FindBy(xpath = "//a[contains(text(), 'Login')]")
    private WebElement loginHomeLink;

    @FindBy(xpath = "//a[contains(text(), 'Signup')]")
    private WebElement registerHomeLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickOnLoginHomeLink() {
        clickElement(loginHomeLink);
        return new LoginPage(driver);
    }

    public RegisterPage clickOnRegisterHomeLink() {
        clickElement(registerHomeLink);
        return new RegisterPage(driver);
    }
}
