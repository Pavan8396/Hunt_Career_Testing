package com.huntcareer.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = 1;
    public static ThreadLocal<Map<String, Integer>> retryCounts = ThreadLocal.withInitial(HashMap::new);

    @Override
    public boolean retry(ITestResult result) {
        String testIdentifier = result.getTestClass().getName() + "." + result.getName();
        Map<String, Integer> counts = retryCounts.get();
        int count = counts.getOrDefault(testIdentifier, 0);

        if (count < MAX_RETRY_COUNT) {
            counts.put(testIdentifier, count + 1);
            System.out.println("Retrying test " + result.getName() + " for the " + (count + 1) + " time(s).");
            return true;
        }
        return false;
    }
}