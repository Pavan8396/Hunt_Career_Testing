package com.huntcareer.qa.tests;

import com.huntcareer.qa.payloads.Payloads;
import com.huntcareer.qa.util.FakerUtil;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class JobsTests extends TestBase {

    static String jobId;
    String jobTitle = FakerUtil.generateJobTitle();
    String companyName = EmployerAuthenticationTests.companyName;
    String description = "Developing and maintaining web applications.";
    String location = "San Francisco, CA";
    String jobType = "Full-Time";

    @Test(priority = 1, dependsOnMethods = {"com.huntcareer.qa.tests.EmployerAuthenticationTests.loginEmployerSuccess"})
    public void createJob() {
        String payload = Payloads.getCreateJobPayload(jobTitle, companyName, description, location, jobType);

        jobId = given().
                auth().oauth2(EmployerAuthenticationTests.authToken_employer).
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/jobs").
                then().
                statusCode(201).
                body("_id", notNullValue()).
                extract().
                path("_id");
    }

    @Test(priority = 2)
    public void getAllJobs() {
        given().
                when().
                get("/api/jobs").
                then().
                statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = {"com.huntcareer.qa.tests.EmployerAuthenticationTests.loginEmployerSuccess"})
    public void getEmployerJobs() {
        given().
                auth().oauth2(EmployerAuthenticationTests.authToken_employer).
                when().
                get("/api/jobs/employer").
                then().
                statusCode(200);
    }

    @Test(priority = 4, dependsOnMethods = {"createJob"})
    public void getJobById() {
        given().
                when().
                get("/api/jobs/" + jobId).
                then().
                statusCode(200);
    }

    @Test(priority = 5, dependsOnMethods = {"createJob"})
    public void updateJob() {
        String payload = Payloads.getUpdateJobPayload("Senior Software Engineer", companyName, "Leading the development of new features.", "Remote", "Full-Time");

        given().
                auth().oauth2(EmployerAuthenticationTests.authToken_employer).
                contentType(ContentType.JSON).
                body(payload).
                when().
                put("/api/jobs/" + jobId).
                then().
                statusCode(200);
    }

    @Test(dependsOnMethods = {"com.huntcareer.qa.tests.EmployerAuthenticationTests.loginEmployerSuccess"})
    public void createJobMissingFields() {
        String payload = Payloads.getCreateJobPayloadMissingFields("Software Engineer");

        given().
                auth().oauth2(EmployerAuthenticationTests.authToken_employer).
                contentType(ContentType.JSON).
                body(payload).
                when().
                post("/api/jobs").
                then().
                statusCode(400).
                body("message", equalTo("All fields are required"));
    }
}
