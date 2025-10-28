package com.huntcareer.qa.tests;

import com.huntcareer.qa.payloads.Payloads;
import com.huntcareer.qa.util.FakerUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class EmployerAuthenticationTests extends TestBase {

    static String employerEmail = FakerUtil.generateEmployerEmail();
    static String companyName = FakerUtil.generateCompanyName();
    static String employerPassword = "password123";
    static String authToken_employer;
    static String employerId;

    @Test(priority = 1)
    public void registerEmployerSuccess() {
        String payload = Payloads.getRegisterEmployerPayload(companyName, employerEmail, employerPassword);

        given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/employers/register").
                then().
                statusCode(201).
                body("message", equalTo("Employer registered successfully"));
    }

    @Test(priority = 2)
    public void loginEmployerSuccess() {
        String payload = Payloads.getLoginPayload(employerEmail, employerPassword);

        Response response = given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/employers/login").
                then().
                statusCode(200).
                body("token", notNullValue()).
                body("user._id", notNullValue()).
                body("user.name", equalTo(companyName)).
                body("user.email", equalTo(employerEmail)).
                extract().
                response();

        authToken_employer = response.path("token");
        employerId = response.path("user._id");
    }

    @Test(priority = 3)
    public void registerEmployerDuplicateEmail() {
        String payload = Payloads.getRegisterEmployerPayload(companyName, employerEmail, employerPassword);

        given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/employers/register").
                then().
                statusCode(400).
                body("message", equalTo("Employer already exists"));
    }
}
