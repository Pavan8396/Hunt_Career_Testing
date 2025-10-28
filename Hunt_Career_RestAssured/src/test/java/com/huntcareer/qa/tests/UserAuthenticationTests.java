package com.huntcareer.qa.tests;

import com.huntcareer.qa.payloads.Payloads;
import com.huntcareer.qa.util.FakerUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserAuthenticationTests extends TestBase {

    static String userEmail = FakerUtil.generateUserEmail();
    static String userFirstName = FakerUtil.generateUserFirstName();
    static String userLastName = FakerUtil.generateUserLastName();
    static String userPhoneNumber = "1234567890";
    static String userPassword = "password123";
    static String authToken_user;
    static String userId;

    @Test(priority = 1)
    public void registerUserSuccess() {
        String payload = Payloads.getRegisterUserPayload(userFirstName, userLastName, userEmail, userPassword, userPhoneNumber);

        given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/auth/register").
                then().
                statusCode(201).
                body("message", equalTo("User registered successfully"));
    }

    @Test(priority = 2)
    public void loginUserSuccess() {
        String payload = Payloads.getLoginPayload(userEmail, userPassword);

        Response response = given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/auth/login").
                then().
                statusCode(200).
                body("token", notNullValue()).
                body("user._id", notNullValue()).
                body("user.name", equalTo(userFirstName + " " + userLastName)).
                body("user.email", equalTo(userEmail)).
                extract().
                response();

        authToken_user = response.path("token");
        userId = response.path("user._id");
    }

    @Test(priority = 3)
    public void registerUserDuplicateEmail() {
        String payload = Payloads.getRegisterUserPayload(userFirstName, userLastName, userEmail, userPassword, userPhoneNumber);

        given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/auth/register").
                then().
                statusCode(400).
                body("message", equalTo("User already exists"));
    }
}
