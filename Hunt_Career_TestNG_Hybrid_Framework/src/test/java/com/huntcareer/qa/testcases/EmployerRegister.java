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
    public WebDriver driver;
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

    @Test(priority = 1, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_1_verifyRegisterWithValidData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.validUser();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 2, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_2_verifyDuplicateData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.duplicateUser();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyUserAlreadyExistsMessage();
    }

    @Test(priority = 3, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_3_verifyNoFirstNameData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noName();
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyBlankCompanyNameMessage();
    }

    @Test(priority = 4, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TC_4_verifyNoEmailData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noEmail();
        erp.enterCompanyName(user.get("name"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyBlankEmailMessage();
    }

    @Test(priority = 5, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_5_verifyNoPasswordData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noPassword();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
    }

    @Test(priority = 6, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_6_verifyPasswordWithLessCharacters() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.passwordWithLessCharacters();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyPasswordMustBeAtLeast8CharctersLongMessage();
    }

    @Test(priority = 7, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_7_verifyRegisterWithoutConfirmPasswordData() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noConfirmPassword();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.clickSignUpButton();
        erp.verifyPasswordDoNotMatchMessage();
    }

    @Test(priority = 8, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_8_verifyplainEmail() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.plainEmail();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 9, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_9_verifynoLocalPart() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noLocalPart();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 10, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_10_verifyEmailonlyLocalPart() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.onlyLocalPart();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 11, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_11_verifydoubleAt() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.doubleAt();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 12, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_12_verifyEmailwithoutTopLevelDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.withoutTopLevelDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 13, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_13_verifytailingWithDot() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.tailingWithDot();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 14, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_14_verifytopLevelDomainTooShort() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.topLevelDomainTooShort();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 15, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_15_verifydomainStartsWithHyphen() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.domainStartsWithHyphen();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 16, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_16_verifydoubleDotInDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.doubleDotInDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 17, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_17_verifynoAtTheRate() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.noAtTheRate();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 18, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_18_verifyillegalChars() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.illegalChars();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 19, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_19_verifytwoConsicutiveDots() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.twoConsicutiveDots();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 20, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_20_verifydotAtBeginning() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.dotAtBeginning();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 21, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_21_verifywithQuotation() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.withQuotation();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 22, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_22_verifyunclosedQuotation() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.unclosedQuotation();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 23, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_23_verifyveryLengthyLocal() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.veryLengthyLocal();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 24, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_24_verifyveryLengthyDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.veryLengthyDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 25, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_25_verifyveryLengthyTLD() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.veryLengthyTLD();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 26, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_26_verifymissingSecondLevelDomain() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.missingSecondLevelDomain();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 27, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_27_verifywithComma() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.withComma();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 28, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_28_verifydoubleConsicutiveAt() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.doubleConsicutiveAt();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 29, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_29_verifyunderscoreInTLD() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.underscoreInTLD();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 30, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_30_verifydotBetweenName() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.dotBetweenName();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 31, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_31_verifyspaceBeforeName() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.spaceBeforeName();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 32, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_32_verifyspaceAfterName() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.spaceAfterName();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        elp = erp.clickSignUpButton();
        elp.verifyRegisterSuccess();
    }

    @Test(priority = 33, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_33_verifydotBeforeAt() {

        erp = new EmployerRegisterPage(driver);
        Map<String, String> user = EmployerRegisterData.dotBeforeAt();
        erp.enterCompanyName(user.get("name"));
        erp.enterEmail(user.get("email"));
        erp.enterPassword(user.get("password"));
        erp.enterConfirmPassword(user.get("confirmPassword"));
        erp.clickSignUpButton();
        erp.verifyInvalidEmailAndPassword();
    }

    @Test(priority = 34, retryAnalyzer = com.huntcareer.qa.listeners.RetryAnalyzer.class)
    public void TCER_34_verifywithoutDomain() {

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