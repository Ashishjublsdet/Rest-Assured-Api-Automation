package com.study.automation.customReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.study.automation.constants.GlobalVariable;

public class ExtentManager {
    private static ExtentReports extent = null;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();
    private static ThreadLocal<ExtentTest> child = new ThreadLocal();
    private static ThreadLocal<ExtentTest> subchild = new ThreadLocal();


    public ExtentManager() {
    }


    public synchronized static ThreadLocal<ExtentTest> getTest() {
        return test;
    }

    public synchronized static void setTest(ExtentTest test) {
        getTest().set(test);
    }

    public synchronized static ThreadLocal<ExtentTest> getChild() {
        return child;
    }

    public synchronized static void setChild(ExtentTest child) {
        getChild().set(child);
    }


    public synchronized static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public synchronized static ExtentReports createInstance() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(GlobalVariable.OUTPUT_FOLDER_REPORT + GlobalVariable.FILE_NAME_REPORT);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName(" API Automation Reports ");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    public static void addExecutionDetails_ExtentReport() {
        extent.flush();
    }
}


