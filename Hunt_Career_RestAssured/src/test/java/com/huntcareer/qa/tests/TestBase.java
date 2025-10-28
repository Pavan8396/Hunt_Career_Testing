package com.huntcareer.qa.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "http://localhost:5000";
    }
}
