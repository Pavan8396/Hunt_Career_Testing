package com.huntcareer.qa.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ChatTests extends TestBase {

    @Test(priority = 1, dependsOnMethods = {"com.huntcareer.qa.tests.ApplicationsTests.getApplicationForJob"})
    public void getChatHistory() {
        given().
                auth().oauth2(UserAuthenticationTests.authToken_user).
                when().
                get("/api/chat/" + ApplicationsTests.applicationId).
                then().
                statusCode(200);
    }

    @Test(priority = 2, dependsOnMethods = {"getChatHistory"})
    public void deleteChatHistory() {
        given().
                auth().oauth2(UserAuthenticationTests.authToken_user).
                when().
                delete("/api/chat/" + ApplicationsTests.applicationId).
                then().
                statusCode(200).
                body("message", equalTo("Chat history deleted successfully"));
    }
}
