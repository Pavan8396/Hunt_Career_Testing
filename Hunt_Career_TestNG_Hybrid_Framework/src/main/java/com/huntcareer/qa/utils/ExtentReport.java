package com.huntcareer.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Hunt Career - Advanced QA Dashboard Report
 * ------------------------------------------------------------
 * ✨ Features:
 *  - Visual summary with total pass/fail/skip counts
 *  - Per-group pie charts (auto-generated)
 *  - Material-inspired dark UI
 *  - Dynamic runtime analytics (Chart.js)
 */
public class ExtentReport {

    private static File latestReportFile;

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();

        // Create report directory
        String reportDirPath = System.getProperty("user.dir") + "/test-output/ExtentReports/";
        File reportDir = new File(reportDirPath);
        if (!reportDir.exists()) reportDir.mkdirs();

        // Timestamped report file
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        latestReportFile = new File(reportDirPath + "HuntCareer_DashboardReport_" + timeStamp + ".html");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(latestReportFile);

        // 🎨 Base theme and titles
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Hunt Career | Automation Dashboard");
        sparkReporter.config().setReportName("🚀 Hunt Career QA Automation Results");
        sparkReporter.config().setTimeStampFormat("EEE, dd MMM yyyy HH:mm:ss z");

        // 💅 Custom CSS
        sparkReporter.config().setCss(
            "body { font-family: 'Segoe UI', Roboto, sans-serif; background-color: #111; color: #ddd; }" +
            ".nav-wrapper { background-color: #222 !important; }" +
            ".brand-logo { font-weight: bold; color: #00bcd4 !important; }" +
            ".card { background: #1e1e1e; border-radius: 12px; box-shadow: 0 0 12px rgba(0,0,0,0.4); }" +
            ".test-status.pass { color: #00e676 !important; }" +
            ".test-status.fail { color: #ff5252 !important; }" +
            ".test-status.skip { color: #ffb300 !important; }" +
            ".summary-container { display: flex; flex-wrap: wrap; justify-content: space-around; margin: 20px 0; }" +
            ".summary-card { flex: 1 1 200px; margin: 10px; padding: 20px; background: #1e1e1e; text-align: center; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.3); }" +
            ".summary-card h2 { margin: 10px 0 0; font-size: 26px; }" +
            ".summary-card p { margin: 5px 0 0; color: #aaa; font-size: 14px; }" +
            ".chart-section { margin-top: 40px; }" +
            "canvas { max-width: 350px; margin: 0 auto; }"
        );

        // 📊 Custom JS
        sparkReporter.config().setJs(
            "document.title='Hunt Career QA Dashboard';" +
            "var script=document.createElement('script');" +
            "script.src='https://cdn.jsdelivr.net/npm/chart.js';" +
            "script.onload=function(){" +
            "setTimeout(()=>{" +
            "const dashboard=document.querySelector('.dashboard-view');" +
            "if(!dashboard) return;" +

            // Inject top summary
            "dashboard.insertAdjacentHTML('afterbegin',`" +
            "<div class='summary-container'>" +
            "  <div class='summary-card'><h2 id='passed'>0</h2><p>Passed</p></div>" +
            "  <div class='summary-card'><h2 id='failed'>0</h2><p>Failed</p></div>" +
            "  <div class='summary-card'><h2 id='skipped'>0</h2><p>Skipped</p></div>" +
            "</div>" +
            "<div class='chart-section'><canvas id='globalChart'></canvas></div>`);" +

            // Count totals
            "let pass=document.querySelectorAll('.test-status.pass').length;" +
            "let fail=document.querySelectorAll('.test-status.fail').length;" +
            "let skip=document.querySelectorAll('.test-status.skip').length;" +
            "document.getElementById('passed').innerText=pass;" +
            "document.getElementById('failed').innerText=fail;" +
            "document.getElementById('skipped').innerText=skip;" +

            // Global Chart
            "const ctx=document.getElementById('globalChart');" +
            "new Chart(ctx,{type:'doughnut',data:{labels:['Passed','Failed','Skipped'],datasets:[{data:[pass,fail,skip],backgroundColor:['#00e676','#ff5252','#ffb300']}]},options:{plugins:{legend:{labels:{color:'#fff',font:{size:14}}}}}});" +

            // Per-group charts
            "let groups={};" +
            "document.querySelectorAll('.test').forEach(t=>{" +
            "let cats=t.getAttribute('category');" +
            "if(cats){cats.split(',').forEach(g=>{g=g.trim();if(!groups[g])groups[g]={pass:0,fail:0,skip:0};" +
            "if(t.querySelector('.test-status.pass')) groups[g].pass++;" +
            "if(t.querySelector('.test-status.fail')) groups[g].fail++;" +
            "if(t.querySelector('.test-status.skip')) groups[g].skip++;});}});" +
            "if(Object.keys(groups).length>0){" +
            "  const charts=document.createElement('div');charts.style.margin='40px auto';charts.style.maxWidth='800px';" +
            "  charts.innerHTML='<h3 style=\"text-align:center;margin-bottom:10px;\">📊 Group Analytics</h3>';" +
            "  Object.keys(groups).forEach((g,i)=>{" +
            "    const div=document.createElement('div');div.style.textAlign='center';div.style.marginBottom='40px';" +
            "    div.innerHTML=`<h4 style='color:#00bcd4'>${g}</h4><canvas id='chart${i}'></canvas>`;" +
            "    charts.appendChild(div);" +
            "    setTimeout(()=>{" +
            "      const ctx=document.getElementById('chart'+i);" +
            "      new Chart(ctx,{type:'pie',data:{labels:['Passed','Failed','Skipped'],datasets:[{data:[groups[g].pass,groups[g].fail,groups[g].skip],backgroundColor:['#00e676','#ff5252','#ffb300']}]},options:{plugins:{legend:{labels:{color:'#fff'}}}}});" +
            "    },500);" +
            "  });" +
            "  dashboard.appendChild(charts);" +
            "}" +
            "},1500);" +
            "};document.head.appendChild(script);"
        );

        extentReport.attachReporter(sparkReporter);

        // ⚙️ System info
        Properties prop = loadProperties(System.getProperty("user.dir") + "/src/main/java/com/huntcareer/qa/config/Config.properties");
        extentReport.setSystemInfo("🌐 Application URL", prop.getProperty("url", "N/A"));
        extentReport.setSystemInfo("🧪 Environment", prop.getProperty("environment", "QA"));
        extentReport.setSystemInfo("🖥️ Browser", prop.getProperty("browser", "N/A"));
        extentReport.setSystemInfo("💻 OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("☕ Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("👤 Executed By", System.getProperty("user.name"));
        extentReport.setSystemInfo("🕒 Generated On", new SimpleDateFormat("dd MMM yyyy, HH:mm:ss").format(new Date()));

        return extentReport;
    }

    public static File getLatestReportFile() {
        return latestReportFile;
    }

    private static Properties loadProperties(String path) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            prop.load(fis);
        } catch (Exception e) {
            System.err.println("⚠️ Config not loaded: " + e.getMessage());
        }
        return prop;
    }
}
