package com.huntcareer.qa.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApplicationsTests extends TestBase {

    static String applicationId;

    @Test(priority = 1, dependsOnMethods = {"com.huntcareer.qa.tests.UserAuthenticationTests.loginUserSuccess", "com.huntcareer.qa.tests.JobsTests.createJob"})
    public void applyForJob() {
        given().
                auth().oauth2(UserAuthenticationTests.authToken_user).
                when().
                post("/api/applications/apply/" + JobsTests.jobId).
                then().
                statusCode(201);
    }

    @Test(priority = 2, dependsOnMethods = {"applyForJob"})
    public void getApplicationForJob() {
        applicationId = given().
                auth().oauth2(UserAuthenticationTests.authToken_user).
                when().
                get("/api/jobs/" + JobsTests.jobId + "/application").
                then().
                statusCode(200).
                extract().
                path("_id");
    }

    @Test(priority = 3, dependsOnMethods = {"com.huntcareer.qa.tests.EmployerAuthenticationTests.loginEmployerSuccess", "com.huntcareer.qa.tests.JobsTests.createJob"})
    public void getApplicationsForJobByEmployer() {
        given().
                auth().oauth2(EmployerAuthenticationTests.authToken_employer).
                when().
                get("/api/applications/job/" + JobsTests.jobId).
                then().
                statusCode(200);
    }

    @Test(priority = 4, dependsOnMethods = {"getApplicationForJob"})
    public void shortlistCandidate() {
        given().
                auth().oauth2(EmployerAuthenticationTests.authToken_employer).
                when().
                post("/api/applications/shortlist/" + applicationId).
                then().
                statusCode(200);
    }
}
