package com.huntcareer.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

    private static ExtentReports extent;

    // Store per-class stats
    public static Map<String, ClassStats> classStatsMap = new HashMap<>();
    // Store per-test duration
    public static Map<String, Long> testDurationMap = new HashMap<>();

    public static ExtentReports generateExtentReport() {
        if (extent == null) {
            extent = createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        ExtentReports extentReport = new ExtentReports();

        String reportPath = System.getProperty("user.dir") + "/reports/extent-reports/extentReport.html";
        File reportFile = new File(reportPath);
        reportFile.getParentFile().mkdirs();

        ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
        configureVisuals(spark);

        extentReport.attachReporter(spark);

        Properties prop = loadProperties();
        addSystemInfo(extentReport, prop);

        return extentReport;
    }

    private static void configureVisuals(ExtentSparkReporter spark) {
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Hunt Career Automation Dashboard");
        spark.config().setReportName("Hunt Career Project – Test Execution Report");
        spark.config().setEncoding("utf-8");
        spark.config().setTimeStampFormat("EEEE, dd MMMM yyyy | hh:mm:ss a");

        // CSS for dashboard and cards
        String css = """
            body { font-family: 'Segoe UI', sans-serif; background-color: #121212; color: #e0e0e0; }
            .report-name { color: #00bcd4 !important; font-weight: 600; }
            .badge-success { background-color: #4caf50 !important; }
            .badge-danger { background-color: #f44336 !important; }
            .badge-warning { background-color: #ff9800 !important; }
            .chart-container { width: 300px; height: 300px; margin: 20px auto; }
            .summary-card { background:#1e1e1e; padding:20px; border-radius:12px; margin:10px; text-align:center; box-shadow:0 4px 10px rgba(0,0,0,0.3);}
        """;

        String js = generateDashboardJS();
        spark.config().setCss(css);
        spark.config().setJs(js);
    }

    private static String generateDashboardJS() {
        StringBuilder sb = new StringBuilder();
        sb.append("document.addEventListener('DOMContentLoaded', function(){");
        sb.append("const dashboard=document.createElement('div');");
        sb.append("dashboard.id='dashboard-container';dashboard.style.display='flex';dashboard.style.flexWrap='wrap';dashboard.style.justifyContent='center';dashboard.style.marginTop='30px';");
        sb.append("document.body.insertBefore(dashboard, document.body.firstChild);");

        // Per-class Pass/Fail/Skip doughnut charts
        for (Map.Entry<String, ClassStats> entry : classStatsMap.entrySet()) {
            String cls = entry.getKey();
            ClassStats stats = entry.getValue();
            sb.append("const container=document.createElement('div');");
            sb.append("container.className='summary-card';");
            sb.append("container.innerHTML=`<h3>").append(cls).append("</h3><canvas id='chart-").append(cls).append("' class='chart-container'></canvas>`;");
            sb.append("dashboard.appendChild(container);");

            sb.append("const ctx=document.getElementById('chart-").append(cls).append("').getContext('2d');");
            sb.append("new Chart(ctx,{type:'doughnut',data:{labels:['Passed','Failed','Skipped','Flaky','Blocked'],datasets:[{data:[")
              .append(stats.passed).append(",").append(stats.failed).append(",").append(stats.skipped).append(",").append(stats.flaky).append(",").append(stats.blocked)
              .append("],backgroundColor:['#4caf50','#f44336','#ff9800','#e69a2e','#6e7681'],borderColor:'#222',borderWidth:2}]},options:{plugins:{legend:{position:'bottom',labels:{color:'#ccc'}},title:{display:true,text:'Execution Summary',color:'#00bcd4',font:{size:16}}},cutout:'70%'}});");
        }

        // Execution duration bar chart
        if (!testDurationMap.isEmpty()) {
            sb.append("const barContainer=document.createElement('div');");
            sb.append("barContainer.className='summary-card';");
            sb.append("barContainer.innerHTML=`<h3>Test Execution Duration (ms)</h3><canvas id='duration-chart' class='chart-container'></canvas>`;");
            sb.append("dashboard.appendChild(barContainer);");

            sb.append("const ctx2=document.getElementById('duration-chart').getContext('2d');");
            sb.append("new Chart(ctx2,{type:'bar',data:{labels:[");

            int count = 0;
            for (String test : testDurationMap.keySet()) {
                if (count++ > 0) sb.append(",");
                sb.append("'").append(test).append("'");
            }

            sb.append("],datasets:[{label:'Duration (ms)',data:[");

            count = 0;
            for (Long duration : testDurationMap.values()) {
                if (count++ > 0) sb.append(",");
                sb.append(duration);
            }

            sb.append("],backgroundColor:'#00bcd4'}]},options:{plugins:{legend:{display:false},title:{display:true,text:'Execution Time per Test',color:'#00bcd4',font:{size:16}}},scales:{y:{beginAtZero:true,color:'#ccc'},x:{color:'#ccc'}}}});");
        }

        sb.append("});");
        return sb.toString();
    }

    private static Properties loadProperties() {
        Properties prop = new Properties();
        String configPath = System.getProperty("user.dir") + "/src/main/java/com/huntcareer/qa/config/Config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            prop.load(fis);
        } catch (Exception e) { System.err.println("⚠️ Could not load Config.properties: " + e.getMessage()); }
        return prop;
    }

    private static void addSystemInfo(ExtentReports extentReport, Properties prop) {
        extentReport.setSystemInfo("Application URL", prop.getProperty("url","Not Provided"));
        extentReport.setSystemInfo("Browser", prop.getProperty("browser","Not Provided"));
        extentReport.setSystemInfo("Environment", prop.getProperty("env","QA"));
        extentReport.setSystemInfo("OS Name", System.getProperty("os.name"));
        extentReport.setSystemInfo("OS Version", System.getProperty("os.version"));
        extentReport.setSystemInfo("Architecture", System.getProperty("os.arch"));
        extentReport.setSystemInfo("User", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("Framework Version", "Hunt Career Automation v5.0");
        extentReport.setSystemInfo("Execution Time", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
    }

    // Update stats per class
    public static void updateClassStats(String className, String status) {
        ClassStats stats = classStatsMap.getOrDefault(className, new ClassStats());
        switch (status.toUpperCase()) {
            case "PASS": stats.passed++; break;
            case "FAIL": stats.failed++; break;
            case "SKIP": stats.skipped++; break;
            case "FLAKY": stats.flaky++; break;
            case "BLOCKED": stats.blocked++; break;
        }
        classStatsMap.put(className, stats);
    }

    // Update execution duration per test
    public static void updateTestDuration(String testName, long duration) {
        testDurationMap.put(testName, duration);
    }

    public static class ClassStats {
        int passed = 0;
        int failed = 0;
        int skipped = 0;
        int flaky = 0;
        int blocked = 0;
    }
}
