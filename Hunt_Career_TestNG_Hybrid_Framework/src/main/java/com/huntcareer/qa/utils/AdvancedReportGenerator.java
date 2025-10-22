package com.huntcareer.qa.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;

public class AdvancedReportGenerator {

    private static final String REPORTS_HISTORY_DIR = Paths.get(System.getProperty("user.dir"), "test-output", "ReportsHistory").toString();
    private static final int MAX_HISTORY_FILES = 10;
    private static final Gson GSON = new GsonBuilder().create();

    public static void generateMultiRunDashboard(String htmlOutputPath) {
        try {
            // 1. Manage historical data
            List<File> reportFiles = manageReportHistory();

            // 2. Load and process all historical run data
            List<Map<String, Object>> historicalRuns = loadHistoricalRuns(reportFiles);

            // 3. Get the most recent run for the main dashboard view
            Map<String, Object> currentRun = historicalRuns.isEmpty() ? new HashMap<>() : historicalRuns.get(historicalRuns.size() - 1);

            // 4. Load the HTML template
            String template = loadTemplate();

            // 5. Populate the template with data
            String populatedHtml = populateTemplate(template, currentRun, historicalRuns);

            // 6. Write the final HTML file
            writeToFile(htmlOutputPath, populatedHtml);

            // 7. Copy assets
            copyAssets();

            System.out.println("Advanced dashboard generated at: " + htmlOutputPath);

        } catch (IOException e) {
            System.err.println("Error generating the multi-run dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<File> manageReportHistory() throws IOException {
        File historyDir = new File(REPORTS_HISTORY_DIR);
        if (!historyDir.exists()) {
            historyDir.mkdirs();
        }

        File[] files = historyDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files == null) {
            return Collections.emptyList();
        }

        List<File> sortedFiles = Stream.of(files)
                .sorted(Comparator.comparingLong(File::lastModified))
                .collect(Collectors.toList());

        if (sortedFiles.size() > MAX_HISTORY_FILES) {
            int filesToDelete = sortedFiles.size() - MAX_HISTORY_FILES;
            for (int i = 0; i < filesToDelete; i++) {
                if (sortedFiles.get(i).delete()) {
                    System.out.println("Deleted old report: " + sortedFiles.get(i).getName());
                }
            }
            return sortedFiles.subList(filesToDelete, sortedFiles.size());
        }
        return sortedFiles;
    }

    private static List<Map<String, Object>> loadHistoricalRuns(List<File> reportFiles) {
        return reportFiles.stream()
                .map(file -> {
                    try (FileReader reader = new FileReader(file)) {
                        Type type = new TypeToken<Map<String, Object>>() {}.getType();
                        Map<String, Object> data = GSON.fromJson(reader, type);
                        data.put("runId", file.getName()); // Add runId for tracking
                        return data;
                    } catch (IOException e) {
                        System.err.println("Warning: Failed to read report file " + file.getName());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static String loadTemplate() throws IOException {
        InputStream is = AdvancedReportGenerator.class.getClassLoader().getResourceAsStream("dashboard-template.html");
        if (is == null) {
            throw new FileNotFoundException("dashboard-template.html not found in resources");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static String populateTemplate(String template, Map<String, Object> currentRun, List<Map<String, Object>> historicalRuns) {
        Map<String, Object> summary = (Map<String, Object>) currentRun.getOrDefault("summary", new HashMap<>());

        return template
                .replace("${summary_stats}", buildSummaryStatsHtml(summary))
                .replace("${run_id}", (String) currentRun.getOrDefault("runId", "N/A"))
                .replace("${timestamp}", formatTimestamp(summary.get("startTime")))
                .replace("${class_details}", buildClassDetailsHtml((List<Map<String, Object>>) currentRun.get("classMetrics")))
                .replace("${dashboard_data_json}", GSON.toJson(Map.of("currentRun", currentRun, "historical", historicalRuns)));
    }

    private static String buildSummaryStatsHtml(Map<String, Object> summary) {
        return statBlock("Total Tests", summary.get("total")) +
               statBlock("Passed", summary.get("passed"), "badge-pass") +
               statBlock("Failed", summary.get("failed"), "badge-fail") +
               statBlock("Skipped", summary.get("skipped"), "badge-skip") +
               statBlock("Blocked", summary.get("blocked"), "badge-blocked") +
               statBlock("Flaky", summary.get("flaky"), "badge-flaky");
    }

    private static String buildClassDetailsHtml(List<Map<String, Object>> classMetrics) {
        if (classMetrics == null || classMetrics.isEmpty()) {
            return "<div class='card'><p>No class-level data available.</p></div>";
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Map<String, Object> metrics : classMetrics) {
            String className = (String) metrics.get("className");
            sb.append("<div class='card'>")
              .append("<h3>").append(className.substring(className.lastIndexOf('.') + 1)).append("</h3>")
              .append("<canvas id='classChart-").append(index++).append("'></canvas>")
              .append("</div>");
        }
        return sb.toString();
    }

    private static void copyAssets() {
        try {
            File srcDir = new File(System.getProperty("user.dir") + "/test-output/dashboard-assets");
            File destDir = new File(System.getProperty("user.dir") + "/test-output/dashboard-assets");
            if (srcDir.exists()) {
                FileUtils.copyDirectory(srcDir, destDir);
            }
        } catch (IOException e) {
            System.err.println("Error copying dashboard assets: " + e.getMessage());
        }
    }
    
    private static void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private static String statBlock(String title, Object value, String badgeClass) {
        String valueStr = (value != null) ? String.valueOf(((Number) value).intValue()) : "0";
        return "<div class='stat'><h3>" + title + "</h3><p><span class='badge " + badgeClass + "'>" + valueStr + "</span></p></div>";
    }

    private static String statBlock(String title, Object value) {
        String valueStr = (value != null) ? String.valueOf(((Number) value).intValue()) : "0";
        return "<div class='stat'><h3>" + title + "</h3><p>" + valueStr + "</p></div>";
    }

    private static String formatTimestamp(Object timestamp) {
        if (timestamp instanceof Number) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(((Number) timestamp).longValue()));
        }
        return "N/A";
    }
}
