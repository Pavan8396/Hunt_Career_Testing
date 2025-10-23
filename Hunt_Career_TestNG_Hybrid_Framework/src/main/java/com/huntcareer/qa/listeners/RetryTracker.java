package com.huntcareer.qa.listeners;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;

public class RetryTracker {

    private static final Map<String, Throwable> retriedTests = Collections.synchronizedMap(new HashMap<>());

    public static String getTestIdentifier(ITestResult result) {
        StringBuilder identifier = new StringBuilder();
        identifier.append(result.getTestClass().getName());
        identifier.append(":");
        identifier.append(result.getMethod().getMethodName());
        
        Object[] params = result.getParameters();
        if (params != null && params.length > 0) {
            identifier.append(":");
            identifier.append(Arrays.toString(params));
        }
        
        return identifier.toString();
    }

    public static void addRetriedTest(ITestResult result) {
        retriedTests.put(getTestIdentifier(result), result.getThrowable());
    }

    public static boolean isTestRetried(ITestResult result) {
        return retriedTests.containsKey(getTestIdentifier(result));
    }
    
    public static Throwable getOriginalException(ITestResult result) {
        return retriedTests.get(getTestIdentifier(result));
    }

    public static void removeTestFromTracker(ITestResult result) {
        retriedTests.remove(getTestIdentifier(result));
    }
}
