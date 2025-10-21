package com.huntcareer.qa.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * AdvancedReportGenerator
 * - Reads JSON run files from test-output/ReportsHistory/
 * - Builds a single polished merged dashboard HTML containing:
 * - Global summary
 * - Per-class doughnut charts and duration bars
 * - Collapsible test logs with screenshot links
 * - Multi-run trend charts (Pass%, Fail%, Avg Duration)
 * - Link & iframe to ExtentReport (if present)
 *
 * Expected JSON structure per run: a list of objects with fields:
 * { "className":"...", "testName":"...", "status":"PASS"|"FAIL"|"SKIP",
 * "duration":123, "screenshotPath":"..." }
 *
 * The listener should save each run to
 * test-output/ReportsHistory/ReportData_<timestamp>.json
 */
public class AdvancedReportGenerator {

    private static final String REPORTS_HISTORY_DIR = System.getProperty("user.dir") + "/test-output/ReportsHistory/";
    private static final String EXTENT_REPORT_PATH = System.getProperty("user.dir")
            + "/test-output/ExtentReports/extentReport.html";
    private static final Gson gson = new Gson();

    public static void generateMultiRunDashboard(String htmlPath) {
        try {
            // Ensure history dir exists
            File historyDir = new File(REPORTS_HISTORY_DIR);
            if (!historyDir.exists())
                historyDir.mkdirs();

            // Collect JSON files sorted by last modified (ascending = older -> newer)
            File[] jsonFiles = historyDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
            if (jsonFiles == null) {
                jsonFiles = new File[0];
            }
            Arrays.sort(jsonFiles, Comparator.comparingLong(File::lastModified));

            // If more than 10 runs, delete the oldest ones
            if (jsonFiles.length > 10) {
                System.out.println("More than 10 reports found, trimming the oldest ones.");
                int filesToDelete = jsonFiles.length - 10;
                for (int i = 0; i < filesToDelete; i++) {
                    if (jsonFiles[i].delete()) {
                        System.out.println("Deleted old report file: " + jsonFiles[i].getName());
                    } else {
                        System.err.println("Warning: failed to delete old report file " + jsonFiles[i].getName());
                    }
                }
                // Reload the file list after deletion
                jsonFiles = historyDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
                if (jsonFiles == null) {
                    jsonFiles = new File[0];
                }
                Arrays.sort(jsonFiles, Comparator.comparingLong(File::lastModified));
            }

            // Read all runs into a list of runs; each run is a list of maps
            List<RunData> runs = new ArrayList<>();
            for (File f : jsonFiles) {
                try (FileReader fr = new FileReader(f)) {
                    Type listType = new TypeToken<List<Map<String, Object>>>() {
                    }.getType();
                    List<Map<String, Object>> items = gson.fromJson(fr, listType);
                    if (items == null)
                        items = new ArrayList<>();
                    RunData run = convertToRunData(items, f.getName(), f.lastModified());
                    runs.add(run);
                } catch (Exception e) {
                    System.err.println("Warning: failed to read run file " + f.getName() + " -> " + e.getMessage());
                }
            }

            // If no historical runs, still try to include current Extent JSON if available
            // (should be saved by listener)
            // Build aggregated current run if present as last run
            // Prepare aggregated current run (if no runs, we will produce an empty
            // dashboard)
            RunData currentRun = runs.isEmpty() ? null : runs.get(runs.size() - 1);

            // Build HTML
            String html = buildHtml(runs, currentRun);
            // Write to file
            try (FileWriter fw = new FileWriter(htmlPath)) {
                fw.write(html);
            }

            System.out.println("Merged dashboard generated at: " + htmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert raw map items (from JSON) into RunData structure
    private static RunData convertToRunData(List<Map<String, Object>> items, String fileName, long timestamp) {
        RunData run = new RunData();
        run.runId = fileName;
        run.timestamp = timestamp;
        run.tests = new ArrayList<>();
        for (Map<String, Object> m : items) {
            String className = safeToString(m.get("className"));
            String testName = safeToString(m.get("testName"));
            String status = safeToString(m.get("status")).toUpperCase();
            long duration = 0L;
            if (m.get("duration") instanceof Number)
                duration = ((Number) m.get("duration")).longValue();
            String screenshot = m.get("screenshotPath") == null ? "" : safeToString(m.get("screenshotPath"));
            run.tests.add(new TestRecord(className, testName, status, duration, screenshot));
        }
        return run;
    }

    private static String safeToString(Object o) {
        return o == null ? "" : o.toString();
    }

    // Build the final merged HTML string using runs data
    private static String buildHtml(List<RunData> runs, RunData currentRun) {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "<!doctype html><html><head><meta charset='utf-8'><meta name='viewport' content='width=device-width,initial-scale=1'>");
        sb.append("<title>Hunt Career - Merged Test Dashboard</title>");
        // Chart.js
        sb.append("<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>");

        // --- Clean, modern dark theme ---
        sb.append("<style>");
        sb.append(
                ":root{--bg:#0d1117;--card:#161b22;--muted:#8b949e;--accent:#58a6ff;--success:#3fb950;--danger:#f85149;--warn:#d29922;--border-color:rgba(255,255,255,0.1);} ");
        sb.append(
                "body{margin:0;font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Helvetica,Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji';background:var(--bg);color:#c9d1d9;line-height:1.6;} ");
        sb.append(".container{max-width:1400px;margin:40px auto;padding:0 24px;} ");

        // --- Header layout ---
        sb.append(
                ".header{display:flex;justify-content:space-between;align-items:center;flex-wrap:wrap;gap:16px;margin-bottom:32px;padding-bottom:16px;border-bottom:1px solid var(--border-color);} ");
        sb.append(
                ".title h1{margin:0;font-size:28px;font-weight:600;color:#fff;} ");
        sb.append(
                ".title p{margin:8px 0 0;color:var(--muted);font-size:16px;} ");
        sb.append(
                ".controls{display:flex;flex-wrap:wrap;gap:12px;align-items:center;} ");
        sb.append(
                ".btn{background:#21262d;border:1px solid var(--border-color);padding:10px 18px;border-radius:8px;color:#c9d1d9;cursor:pointer;transition:all 0.2s ease;font-size:14px;font-weight:500;} ");
        sb.append(
                ".btn:hover{background:var(--accent);color:#fff;border-color:var(--accent);} ");
        sb.append(
                ".btn:disabled{opacity:0.5;cursor:not-allowed;background:#161b22;border-color:var(--border-color);} ");

        // --- Grid and card styling ---
        sb.append(
                ".grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(360px,1fr));gap:24px;margin-top:24px;} ");
        sb.append(
                ".card{background:var(--card);padding:24px;border-radius:12px;border:1px solid var(--border-color);box-shadow:0 8px 24px rgba(1,4,9,0.5);} ");
        sb.append(
                ".chart-container{width:100%;height:240px !important;margin-top:16px;} .duration-chart-wrapper{overflow-x:auto;height:300px;margin-top:16px;} ");
        sb.append(
                ".summary{display:grid;grid-template-columns:repeat(auto-fit,minmax(120px,1fr));gap:20px;} ");
        sb.append(
                ".stat{text-align:center;} .stat h3{margin:0;font-size:15px;color:var(--muted);font-weight:500;} .stat p{margin:8px 0 0;font-size:22px;color:#fff;font-weight:600;} ");
        sb.append(
                ".class-header{display:flex;justify-content:space-between;align-items:center;margin-bottom:12px;} .class-header h3{margin:0;color:var(--accent);font-size:18px;} ");
        sb.append(
                ".collapsible{background:#0d1117;border:1px solid var(--border-color);padding:10px 14px;border-radius:8px;color:var(--muted);cursor:pointer;width:100%;text-align:left;margin-top:16px;transition:all 0.2s;font-size:12px;} ");
        sb.append(
                ".collapsible:hover{background:#161b22;color:#fff;} ");
        sb.append(
                ".content{display:none;padding-top:16px;overflow-y:auto;max-height:400px;}" +
                        "table{width:100%;border-collapse:collapse;table-layout:fixed;}" +
                        "th,td{padding:12px 10px;text-align:left;border-bottom:1px solid var(--border-color);" +
                        "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}" +
                        ".content table th, .content table td{font-size:12px !important;padding:6px 8px;}" +
                        "th{font-weight:600;}");

        sb.append(
                "th:first-child,td:first-child{width:50%;} th:nth-child(2),td:nth-child(2){width:20%;} th:nth-child(3),td:nth-child(3){width:20%;} th:nth-child(4),td:nth-child(4){width:10%;} td:first-child{cursor:pointer;} td:first-child:hover{text-decoration:underline;} ");
        sb.append(
                ".badge{padding:6px 10px;border-radius:16px;font-weight:600;font-size:12px;} .badge-pass{background:rgba(63,185,80,0.15);color:var(--success);} .badge-fail{background:rgba(248,81,73,0.15);color:var(--danger);} .badge-skip{background:rgba(210,153,34,0.15);color:var(--warn);} ");
        sb.append(
                "canvas{max-height:280px !important;} ");
        sb.append(
                ".small{font-size:13px;color:var(--muted);} .iframe-wrap{margin-top:32px;} iframe{width:100%;height:700px;border:1px solid var(--border-color);border-radius:12px;box-shadow:0 8px 24px rgba(1,4,9,0.5);} ");
        sb.append(
                "@media(max-width:768px){.header{flex-direction:column;align-items:flex-start;} .grid{grid-template-columns:1fr;} .chart-wide{overflow-x:auto;} .container{margin:24px auto;} .title h1{font-size:24px;} .title p{font-size:14px;}}");
        sb.append("</style></head><body>");

        // --- Header section ---
        sb.append("<div class='container'>");
        sb.append("<div class='header'>");
        sb.append("<div class='title'>");
        sb.append("<h1>Hunt Career — Merged Test Dashboard</h1>");
        sb.append("<p>Interactive test analytics combining Extent Reports, class-level results, and trends</p>");
        sb.append("</div>");
        sb.append("<div class='controls'>");

        // Extent report button (open in new tab)
        File extentFile = new File(EXTENT_REPORT_PATH);
        if (extentFile.exists()) {
            sb.append("<a href='").append(relativePathForHtml(EXTENT_REPORT_PATH))
                    .append("' target='_blank' class='btn'>Open Extent Report</a>");
        } else {
            sb.append("<button class='btn' disabled>No Extent Report</button>");
        }

        sb.append("<button class='btn' onclick='window.location.reload()'>Refresh</button>");
        sb.append("</div></div>"); // header end

        // Top summary: use latest run if available
        RunData latest = runs.isEmpty() ? null : runs.get(runs.size() - 1);
        int totalTests = 0, passed = 0, failed = 0, skipped = 0;
        long totalDuration = 0;
        if (latest != null) {
            for (TestRecord tr : latest.tests) {
                totalTests++;
                totalDuration += tr.duration;
                switch (tr.status) {
                    case "PASS":
                        passed++;
                        break;
                    case "FAIL":
                        failed++;
                        break;
                    case "SKIP":
                        skipped++;
                        break;
                }
            }
        }

        // Summary card
        sb.append("<div class='grid'>");
        sb.append("<div class='card'>");
        sb.append("<h2>Current Run Summary</h2>");
        sb.append("<div class='summary'>");
        sb.append(statBlock("Total Tests", String.valueOf(totalTests)));
        sb.append(statBlock("Passed", String.valueOf(passed), "badge-pass"));
        sb.append(statBlock("Failed", String.valueOf(failed), "badge-fail"));
        sb.append(statBlock("Skipped", String.valueOf(skipped), "badge-skip"));
        sb.append(statBlock("Total Time (ms)", String.valueOf(totalDuration)));
        sb.append("</div>"); // summary
        if (latest != null) {
            sb.append("<p class='small'>Run file: ").append(latest.runId).append(" &nbsp; • &nbsp; ")
                    .append(new Date(latest.timestamp).toString()).append("</p>");
        } else {
            sb.append("<p class='small'>No runs found in ReportsHistory.</p>");
        }
        sb.append("</div>"); // card

        // Multi-run trend card (pass% / fail% / avg duration)
        sb.append("<div class='card chart-wide'>");
        sb.append("<h2>Trends Across Runs</h2>");
        sb.append("<canvas id='trendChart' style='width:100%;height:320px;'></canvas>");
        sb.append("</div>");

        sb.append("</div>"); // grid end

        // Per-class section (grouped)
        sb.append("<div style='margin-top:20px;'>");
        sb.append("<h2 style='color:var(--accent);'>Per-class Details</h2>");
        sb.append("<div class='grid'>");

        // If no runs, skip per-class
        if (latest == null) {
            sb.append("<div class='card'><p class='small'>No test data available.</p></div>");
        } else {
            // group by class in latest run
            Map<String, List<TestRecord>> classMap = new LinkedHashMap<>();
            for (TestRecord t : latest.tests) {
                classMap.computeIfAbsent(t.className, k -> new ArrayList<>()).add(t);
            }

            int classIndex = 0;
            for (Map.Entry<String, List<TestRecord>> ce : classMap.entrySet()) {
                String cls = ce.getKey();
                List<TestRecord> tests = ce.getValue();
                int cPass = 0, cFail = 0, cSkip = 0;
                for (TestRecord tr : tests) {
                    if ("PASS".equals(tr.status))
                        cPass++;
                    else if ("FAIL".equals(tr.status))
                        cFail++;
                    else
                        cSkip++;
                }

                sb.append("<div class='card'>");
                String simpleClassName = cls.substring(cls.lastIndexOf('.') + 1);
                sb.append("<div class='class-header'><h3>").append(simpleClassName).append("</h3>");
                sb.append(
                        "<div style='display:flex;gap:8px;align-items:center;flex-wrap:wrap;justify-content:flex-end;'>");
                sb.append("<div class='small'>Tests: ").append(tests.size()).append("</div>");
                sb.append("<div class='small' style='color:var(--muted)'>Pass: ").append(cPass).append("</div>");
                sb.append("<div class='small' style='color:var(--muted)'>Fail: ").append(cFail).append("</div>");
                sb.append("</div></div>"); // class-header

                // Charts
                sb.append("<canvas id='classChart-").append(classIndex).append("' class='chart-container'></canvas>");
                sb.append("<div class='duration-chart-wrapper'><canvas id='classDurChart-").append(classIndex)
                        .append("' style='height:300px !important; width:100%;'></canvas></div>");

                // Collapsible logs
                sb.append("<button class='collapsible'>Toggle Test Logs</button>");
                sb.append("<div class='content'><table>");
                sb.append(
                        "<thead><tr><th>Test</th><th>Status</th><th>Duration (ms)</th><th>Screenshot</th></tr></thead><tbody>");
                for (TestRecord tr : tests) {
                    String badgeClass = tr.status.equals("PASS") ? "badge-pass"
                            : tr.status.equals("FAIL") ? "badge-fail" : "badge-skip";
                    String screenshotLink = (tr.screenshotPath == null || tr.screenshotPath.isEmpty()) ? "-"
                            : ("<a href='" + relativePathForHtml(tr.screenshotPath) + "' target='_blank'>View</a>");
                    sb.append("<tr><td title='").append(jsEscape(tr.testName)).append("'>").append(tr.testName)
                            .append("</td>")
                            .append("<td><span class='badge ").append(badgeClass).append("'>").append(tr.status)
                            .append("</span></td>")
                            .append("<td>").append(tr.duration).append("</td>")
                            .append("<td>").append(screenshotLink).append("</td></tr>");
                }
                sb.append("</tbody></table></div>"); // content & card
                sb.append("</div>");

                // Add JS data for this class (we will create scripts later)
                classIndex++;
            }
        }

        sb.append("</div></div>"); // end per-class section

        // ExtentReport iframe (merged view)
        sb.append("<div class='card iframe-wrap'>");
        sb.append("<h2>Extent Report (embedded)</h2>");
        if (extentFile.exists()) {
            String relPath = relativePathForHtml(EXTENT_REPORT_PATH);
            // Fallback if relative fails or file not accessible
            if (!new File(EXTENT_REPORT_PATH).exists()) {
                relPath = "file:///" + EXTENT_REPORT_PATH.replace("\\", "/");
            }
            sb.append("<p class='small'>Extent report is embedded below. "
                    + "If it doesn’t display, click 'Open Extent Report' above.</p>");
            sb.append("<iframe src='").append(relPath)
                    .append("' sandbox='allow-same-origin allow-popups allow-forms'></iframe>");
        } else {
            sb.append("<p class='small'>ExtentReport not found at ").append(EXTENT_REPORT_PATH).append(".</p>");
        }
        sb.append("</div>");

        // Now we append the scripts for charts and interactions.
        sb.append("<script>");
        // Collapsible behavior
        sb.append(
                "var coll=document.getElementsByClassName('collapsible');for(var i=0;i<coll.length;i++){coll[i].addEventListener('click',function(){this.classList.toggle('active');var content=this.nextElementSibling; if(content.style.display==='block'){content.style.display='none';}else{content.style.display='block';}});} ");

        // Build trend data (run labels + pass%, fail%, avg duration)
        List<String> runLabels = new ArrayList<>();
        List<Double> runPassPct = new ArrayList<>();
        List<Double> runFailPct = new ArrayList<>();
        List<Double> runAvgDur = new ArrayList<>();
        for (RunData r : runs) {
            int tcount = r.tests.size();
            if (tcount == 0)
                continue;
            int p = 0, f = 0, s = 0;
            long sumDur = 0;
            for (TestRecord tr : r.tests) {
                if ("PASS".equals(tr.status))
                    p++;
                else if ("FAIL".equals(tr.status))
                    f++;
                else
                    s++;
                sumDur += tr.duration;
            }
            double passPct = (p * 100.0) / tcount;
            double failPct = (f * 100.0) / tcount;
            double avgDur = tcount > 0 ? (sumDur * 1.0 / tcount) : 0;
            runLabels.add(r.runId.replace(".json", ""));
            runPassPct.add(round(passPct, 2));
            runFailPct.add(round(failPct, 2));
            runAvgDur.add(round(avgDur, 2));
        }

        // Trend chart script
        sb.append("const trendCtx=document.getElementById('trendChart').getContext('2d');");
        sb.append("const trendChart=new Chart(trendCtx,{type:'line',data:{labels:[").append(jsStringArray(runLabels))
                .append("],datasets:[");
        // Pass%
        sb.append("{label:'Pass %',data:[").append(jsNumberArray(runPassPct)).append("],borderColor:'")
                .append("#4caf50").append("',backgroundColor:'rgba(76,175,80,0.12)',tension:0.3},");
        // Fail%
        sb.append("{label:'Fail %',data:[").append(jsNumberArray(runFailPct)).append("],borderColor:'")
                .append("#f44336").append("',backgroundColor:'rgba(244,67,54,0.12)',tension:0.3},");
        // Avg duration (secondary axis)
        sb.append("{label:'Avg Duration (ms)',data:[").append(jsNumberArray(runAvgDur)).append("],borderColor:'")
                .append("#00bcd4").append("',backgroundColor:'rgba(0,188,212,0.12)',yAxisID:'yDur',tension:0.3}");
        sb.append(
                "]},options:{responsive:true,interaction:{mode:'index',intersect:false},scales:{x:{ticks:{color:'#ccc',callback:function(val,idx){const label=this.getLabelForValue(val);return label.length>15?label.substring(0,15)+'...':label;}}},y:{type:'linear',position:'left',ticks:{color:'#ccc'}},yDur:{type:'linear',position:'right',ticks:{color:'#ccc'},grid:{display:false}}},plugins:{legend:{labels:{color:'#ccc'}},tooltip:{callbacks:{title:function(ctx){return ctx[0].chart.data.labels[ctx[0].dataIndex];}}}}}});");

        // Per-class charts: we need to create unique chart scripts for each class shown
        // above.
        // Recreate the class map (from latest) to produce arrays in the same order as
        // rendered.
        if (latest != null) {
            Map<String, List<TestRecord>> classMap = new LinkedHashMap<>();
            for (TestRecord t : latest.tests)
                classMap.computeIfAbsent(t.className, k -> new ArrayList<>()).add(t);

            int ci = 0;
            for (Map.Entry<String, List<TestRecord>> ce : classMap.entrySet()) {
                List<TestRecord> tests = ce.getValue();
                int pass = 0, fail = 0, skip = 0;
                List<String> names = new ArrayList<>();
                List<Long> durs = new ArrayList<>();
                for (TestRecord tr : tests) {
                    names.add(tr.testName);
                    durs.add(tr.duration);
                    if ("PASS".equals(tr.status))
                        pass++;
                    else if ("FAIL".equals(tr.status))
                        fail++;
                    else
                        skip++;
                }

                // Doughnut
                sb.append("new Chart(document.getElementById('classChart-").append(ci)
                        .append("'),{type:'doughnut',data:{labels:['Passed','Failed','Skipped'],datasets:[{data:[")
                        .append(pass).append(",").append(fail).append(",").append(skip)
                        .append("],backgroundColor:['#4caf50','#f44336','#ff9800'],borderColor:'#111',borderWidth:1}]},options:{plugins:{legend:{position:'bottom',labels:{color:'#ccc'}}},cutout:'60%'}});");

                // Duration bar
                sb.append("new Chart(document.getElementById('classDurChart-").append(ci)
                        .append("'),{type:'bar',data:{labels:[").append(jsStringArrayFromList(names))
                        .append("],datasets:[{label:'Duration(ms)',data:[").append(jsNumberArrayFromListLong(durs))
                        .append("],backgroundColor:'#00bcd4'}]},options:{plugins:{legend:{display:false},tooltip:{callbacks:{title:function(ctx){return ctx[0].label;}}}},scales:{x:{ticks:{display:false}},y:{beginAtZero:true,ticks:{color:'#ccc'}}}}});");

                ci++;
            }
        }

        sb.append("</script>");
        sb.append("</div></body></html>");

        return sb.toString();
    }

    // small helpers
    private static String statBlock(String title, String value) {
        return statBlock(title, value, null);
    }

    private static String statBlock(String title, String value, String badgeClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='stat'><h3>").append(title).append("</h3>");
        if (badgeClass != null)
            sb.append("<p class='small'><span class='badge ").append(badgeClass).append("'>").append(value)
                    .append("</span></p>");
        else
            sb.append("<p>").append(value).append("</p>");
        sb.append("</div>");
        return sb.toString();
    }

    // --- JS / HTML helpers ---
    private static String jsStringArray(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (i > 0)
                sb.append(",");
            sb.append("'").append(jsEscape(items.get(i))).append("'");
        }
        return sb.toString();
    }

    private static String jsStringArrayFromList(List<String> items) {
        return jsStringArray(items);
    }

    private static String jsNumberArray(List<Double> nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            if (i > 0)
                sb.append(",");
            sb.append(nums.get(i));
        }
        return sb.toString();
    }

    private static String jsNumberArrayFromListLong(List<Long> nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            if (i > 0)
                sb.append(",");
            sb.append(nums.get(i));
        }
        return sb.toString();
    }

    // Escape single quotes and backslashes for JS string literal
    private static String jsEscape(String s) {
        if (s == null)
            return "";
        return s.replace("\\", "\\\\").replace("'", "\\'");
    }

    // Convert absolute file paths to relative paths usable in HTML opened locally.
    // For local file links, browsers use file:/// — but relative path from HTML
    // file is fine.
    private static String relativePathForHtml(String absPath) {
        try {
            Path htmlRoot = Paths.get(System.getProperty("user.dir"), "test-output").toAbsolutePath();
            Path target = Paths.get(absPath).toAbsolutePath();
            Path rel = htmlRoot.relativize(target);
            return rel.toString().replace("\\", "/");
        } catch (Exception e) {
            // fallback to absolute path (may work as file:/// in some browsers)
            return absPath.replace("\\", "/");
        }
    }

    // --- Small data holders ---
    private static class RunData {
        String runId;
        long timestamp;
        List<TestRecord> tests;
    }

    private static class TestRecord {
        String className;
        String testName;
        String status;
        long duration;
        String screenshotPath;

        public TestRecord(String className, String testName, String status, long duration, String screenshotPath) {
            this.className = className;
            this.testName = testName;
            this.status = status;
            this.duration = duration;
            this.screenshotPath = screenshotPath;
        }
    }

    private static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException("Decimal places must be >= 0");
        long factor = (long) Math.pow(10, places);
        long tmp = Math.round(value * factor);
        return (double) tmp / factor;
    }
}