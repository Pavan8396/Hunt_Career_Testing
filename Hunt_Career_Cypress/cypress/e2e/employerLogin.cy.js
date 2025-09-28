import LoginPage from "../pageObjects/LoginPage";
import loginData from "../fixtures/loginData.json"

describe("Eemployer Login Test", () =>{

    let eloginPage;

    beforeEach(() => {
        eloginPage = new LoginPage();
        eloginPage.employerNavigate();
    });

    it("TC-1-Enter with valid credcredentials", () =>{
        eloginPage.Employerlogin(loginData.employerValidUser.email, loginData.employerValidUser.password);
        eloginPage.waitUntilVisible(eloginPage.checkLoginSuccessMessage());
        eloginPage.waitUntilNotVisible(eloginPage.checkLoginSuccessMessage());
    })
})