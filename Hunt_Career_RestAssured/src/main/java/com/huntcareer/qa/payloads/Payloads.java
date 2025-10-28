package com.huntcareer.qa.payloads;

import org.json.simple.JSONObject;

public class Payloads {

    public static String getRegisterUserPayload(String firstName, String lastName, String email, String password, String phoneNumber) {
        JSONObject request = new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);
        request.put("email", email);
        request.put("password", password);
        request.put("phoneNumber", phoneNumber);
        return request.toJSONString();
    }

    public static String getRegisterUserPayloadMissingField(String lastName, String email, String password, String phoneNumber) {
        JSONObject request = new JSONObject();
        request.put("lastName", lastName);
        request.put("email", email);
        request.put("password", password);
        request.put("phoneNumber", phoneNumber);
        return request.toJSONString();
    }

    public static String getLoginPayload(String email, String password) {
        JSONObject request = new JSONObject();
        request.put("email", email);
        request.put("password", password);
        return request.toJSONString();
    }

    public static String getLoginPayloadMissingField(String email) {
        JSONObject request = new JSONObject();
        request.put("email", email);
        return request.toJSONString();
    }

    public static String getRegisterEmployerPayload(String companyName, String email, String password) {
        JSONObject request = new JSONObject();
        request.put("companyName", companyName);
        request.put("email", email);
        request.put("password", password);
        return request.toJSONString();
    }

    public static String getRegisterEmployerPayloadMissingField(String email, String password) {
        JSONObject request = new JSONObject();
        request.put("email", email);
        request.put("password", password);
        return request.toJSONString();
    }

    public static String getCreateJobPayload(String title, String company, String description, String location, String jobType) {
        JSONObject request = new JSONObject();
        request.put("title", title);
        request.put("company", company);
        request.put("description", description);
        request.put("candidate_required_location", location);
        request.put("job_type", jobType);
        return request.toJSONString();
    }

    public static String getCreateJobPayloadMissingFields(String title) {
        JSONObject request = new JSONObject();
        request.put("title", title);
        return request.toJSONString();
    }

    public static String getUpdateJobPayload(String title, String company, String description, String location, String jobType) {
        JSONObject request = new JSONObject();
        request.put("title", title);
        request.put("company", company);
        request.put("description", description);
        request.put("candidate_required_location", location);
        request.put("job_type", jobType);
        return request.toJSONString();
    }
}
