package com.huntcareer.qa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A thread-safe class to collect and manage statistics for a single test run.
 * It tracks summaries, class-level results, and individual test details.
 */
public class TestRunStatistics {

    // Overall summary counters
    private final AtomicInteger totalTests = new AtomicInteger(0);
    private final AtomicInteger passedTests = new AtomicInteger(0);
    private final AtomicInteger failedTests = new AtomicInteger(0);
    private final AtomicInteger skippedTests = new AtomicInteger(0);
    private final AtomicInteger blockedTests = new AtomicInteger(0);
    private final AtomicInteger flakyTests = new AtomicInteger(0);

    // Detailed results storage
    private final List<TestResultRecord> testResults = new CopyOnWriteArrayList<>();
    private final Map<String, ClassMetrics> classMetrics = new ConcurrentHashMap<>();

    // Execution timing
    private long suiteStartTime;
    private long suiteEndTime;

    /**
     * Records the start of the test suite.
     */
    public void startSuite() {
        this.suiteStartTime = System.currentTimeMillis();
    }

    /**
     * Records the end of the test suite and calculates total duration.
     */
    public void endSuite() {
        this.suiteEndTime = System.currentTimeMillis();
    }

    /**
     * Records a single test result, updating all relevant counters and metrics.
     *
     * @param result The record of the test that has finished.
     */
    public void addTestResult(TestResultRecord result) {
        totalTests.incrementAndGet();
        testResults.add(result);

        // Update overall counters based on status
        switch (result.getStatus()) {
            case "PASS":
                passedTests.incrementAndGet();
                break;
            case "FAIL":
                failedTests.incrementAndGet();
                break;
            case "SKIP":
                skippedTests.incrementAndGet();
                break;
            case "BLOCKED":
                blockedTests.incrementAndGet();
                break;
        }

        if (result.isFlaky()) {
            flakyTests.incrementAndGet();
        }

        // Update per-class metrics
        classMetrics.computeIfAbsent(result.getClassName(), k -> new ClassMetrics(result.getClassName()))
                .addResult(result.getStatus(), result.isFlaky());
    }

    /**
     * Generates a structured map of the collected data, suitable for JSON serialization.
     *
     * @return A map containing the full report data for this run.
     */
    public Map<String, Object> generateReportData() {
        Map<String, Object> reportData = new ConcurrentHashMap<>();

        // --- Summary Section ---
        Map<String, Object> summary = new ConcurrentHashMap<>();
        summary.put("total", totalTests.get());
        summary.put("passed", passedTests.get());
        summary.put("failed", failedTests.get());
        summary.put("skipped", skippedTests.get());
        summary.put("blocked", blockedTests.get());
        summary.put("flaky", flakyTests.get());
        summary.put("duration", suiteEndTime - suiteStartTime);
        summary.put("startTime", suiteStartTime);
        summary.put("endTime", suiteEndTime);

        reportData.put("summary", summary);
        reportData.put("results", testResults);
        reportData.put("classMetrics", new ArrayList<>(classMetrics.values()));

        return reportData;
    }

    /**
     * Converts the report data to a JSON string.
     *
     * @return A JSON representation of the test run statistics.
     */
    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(generateReportData());
    }

    /**
     * Represents the result of a single test case.
     */
    public static class TestResultRecord {
        private final String className;
        private final String testName;
        private final String status;
        private final long duration;
        private final String screenshotPath;
        private final boolean flaky;
        private final String errorMessage;

        public TestResultRecord(String className, String testName, String status, long duration, String screenshotPath, boolean flaky, String errorMessage) {
            this.className = className;
            this.testName = testName;
            this.status = status;
            this.duration = duration;
            this.screenshotPath = screenshotPath;
            this.flaky = flaky;
            this.errorMessage = errorMessage;
        }

        // Getters for serialization
        public String getClassName() { return className; }
        public String getTestName() { return testName; }
        public String getStatus() { return status; }
        public long getDuration() { return duration; }
        public String getScreenshotPath() { return screenshotPath; }
        public boolean isFlaky() { return flaky; }
        public String getErrorMessage() { return errorMessage; }
    }

    /**
     * Holds aggregated metrics for a single test class.
     */

    public static class ClassMetrics {
        private final String className;
        private final AtomicInteger total = new AtomicInteger(0);
        private final AtomicInteger passed = new AtomicInteger(0);
        private final AtomicInteger failed = new AtomicInteger(0);
        private final AtomicInteger skipped = new AtomicInteger(0);
        private final AtomicInteger blocked = new AtomicInteger(0);
        private final AtomicInteger flaky = new AtomicInteger(0);


        public ClassMetrics(String className) {
            this.className = className;
        }

        public void addResult(String status, boolean isFlaky) {
            total.incrementAndGet();
            switch (status) {
                case "PASS":
                    passed.incrementAndGet();
                    break;
                case "FAIL":
                    failed.incrementAndGet();
                    break;
                case "SKIP":
                    skipped.incrementAndGet();
                    break;
                case "BLOCKED":
                    blocked.incrementAndGet();
                    break;
            }
            if (isFlaky) {
                flaky.incrementAndGet();
            }
        }

        // Getters for serialization
        public String getClassName() { return className; }
        public int getTotal() { return total.get(); }
        public int getPassed() { return passed.get(); }
        public int getFailed() { return failed.get(); }
        public int getSkipped() { return skipped.get(); }
        public int getBlocked() { return blocked.get(); }
        public int getFlaky() { return flaky.get(); }
    }
}