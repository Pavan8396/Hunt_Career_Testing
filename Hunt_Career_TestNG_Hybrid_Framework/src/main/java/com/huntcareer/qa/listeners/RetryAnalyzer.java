package com.huntcareer.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = 1;
    private ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 0);

    @Override
    public boolean retry(ITestResult result) {
        int count = retryCount.get();
        if (count < MAX_RETRY_COUNT) {
            retryCount.set(count + 1);
            System.out.println("Retrying test " + result.getName() + " for the " + (count + 1) + " time(s).");
            return true;
        }
        return false;
    }
}
