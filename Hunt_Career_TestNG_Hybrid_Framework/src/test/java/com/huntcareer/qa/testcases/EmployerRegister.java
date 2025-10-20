package com.huntcareer.qa.testcases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.huntcareer.qa.base.Base;
import com.huntcareer.qa.pages.EmployerLoginPage;
import com.huntcareer.qa.pages.EmployerRegisterPage;
import com.huntcareer.qa.pages.LandingPage;
import com.huntcareer.qa.pages.LoginPage;
import com.huntcareer.qa.testdata.EmployerRegisterData;

public class EmployerRegister extends Base {
    WebDriver driver;
    EmployerRegisterPage erp;
    EmployerLoginPage elp;
    LandingPage ldp;
    LoginPage lp;

    public EmployerRegister() {
        super();
    }

    @BeforeMethod
    public void setup() {
        // loadPropertiesFile();
        driver = inicializeBrowserAndOpenApplication(prop.getProperty("browser"));
        // driver.get(loadPropertiesFile().getProperty("url")+"/signup");
        driver.get(prop.getProperty("url"));
        ldp = new LandingPage(driver);
        erp = ldp.clickOnEmployerRole();
        erp.clickSignupLink();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_1_verifyRegisterWithValidData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.validUser();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 2, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_2_verifyDuplicateData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.duplicateUser();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyUserAlreadyExistsMessage();
    }

    @Test(priority = 3, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_3_verifyNoFirstNameData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noName();
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyBlankCompanyNameMessage();
    }

    @Test(priority = 5, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_5_verifyNoEmailData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noEmail();
        erp.enterCompanyName(user.get("name"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyBlankEmailMessage();
    }

    @Test(priority = 6, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_6_verifyNoPasswordData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noPassword();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
    }

    @Test(priority = 7, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_7_verifyPasswordWithLessCharacters() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.passwordWithLessCharacters();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
    }

    @Test(priority = 8, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_8_verifyRegisterWithoutConfirmPasswordData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noConfirmPassword();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.clickSignUpButton();
        erp.verifyPasswordDoNotMatchMessage();
    }

    @Test(priority = 11, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_11_verifyplainEmail() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.plainEmail();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 12, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_12_verifynoLocalPart() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noLocalPart();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 13, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_13_verifyEmailonlyLocalPart() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.onlyLocalPart();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 14, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_14_verifydoubleAt() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.doubleAt();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 15, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_15_verifyEmailwithoutTopLevelDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.withoutTopLevelDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 16, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_16_verifytailingWithDot() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.tailingWithDot();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 17, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_17_verifytopLevelDomainTooShort() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.topLevelDomainTooShort();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 18, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_18_verifydomainStartsWithHyphen() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.domainStartsWithHyphen();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 19, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_19_verifydoubleDotInDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.doubleDotInDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 20, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_20_verifynoAtTheRate() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noAtTheRate();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 21, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_21_verifyillegalChars() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.illegalChars();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 22, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_22_verifytwoConsicutiveDots() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.twoConsicutiveDots();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 23, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_23_verifydotAtBeginning() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.dotAtBeginning();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 24, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_24_verifywithQuotation() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.withQuotation();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 25, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_25_verifyunclosedQuotation() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.unclosedQuotation();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 26, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_26_verifyveryLengthyLocal() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.veryLengthyLocal();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 27, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_27_verifyveryLengthyDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.veryLengthyDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 28, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_28_verifyveryLengthyTLD() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.veryLengthyTLD();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 29, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_29_verifymissingSecondLevelDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.missingSecondLevelDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 30, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_30_verifywithComma() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.withComma();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 31, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_31_verifydoubleConsicutiveAt() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.doubleConsicutiveAt();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 32, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_32_verifyunderscoreInTLD() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.underscoreInTLD();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 33, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_33_verifydotBetweenName() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.dotBetweenName();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 34, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_34_verifyspaceBeforeName() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.spaceBeforeName();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 35, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_35_verifyspaceAfterName() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.spaceAfterName();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 36, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_36_verifydotBeforeAt() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.dotBeforeAt();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 37, groups = "Functional", retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_37_verifywithoutDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.withoutDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }
}