package com.huntcareer.qa.utils;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtils {

    public static Object[][] getTestData(String filePath, String testCaseType, String... keys) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject testData = (JSONObject) parser.parse(new FileReader(filePath));
            JSONArray testCases = (JSONArray) testData.get(testCaseType);

            Object[][] data = new Object[testCases.size()][keys.length];
            for (int i = 0; i < testCases.size(); i++) {
                JSONObject testCase = (JSONObject) testCases.get(i);
                for (int j = 0; j < keys.length; j++) {
                    data[i][j] = testCase.get(keys[j]);
                }
            }
            return data;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}