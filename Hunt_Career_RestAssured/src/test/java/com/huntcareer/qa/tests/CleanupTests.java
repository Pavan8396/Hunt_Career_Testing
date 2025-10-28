package com.huntcareer.qa.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CleanupTests extends TestBase {

    @Test(dependsOnMethods = {
            "com.huntcareer.qa.tests.JobsTests.createJob",
            "com.huntcareer.qa.tests.EmployerAuthenticationTests.loginEmployerSuccess"
    })
    public void deleteJob() {
        given().
                auth().oauth2(EmployerAuthenticationTests.authToken_employer).
                when().
                delete("/api/jobs/" + JobsTests.jobId).
                then().
                statusCode(200).
                body("message", equalTo("Selected jobs have been removed successfully"));
    }
}
