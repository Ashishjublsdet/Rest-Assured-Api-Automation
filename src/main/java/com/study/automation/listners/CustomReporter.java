package com.study.automation.listners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.study.automation.customReporter.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CustomReporter implements ITestListener {
    private ConcurrentHashMap<String, ExtentTest> allTests = new ConcurrentHashMap<>();

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = getMethodNamewithParams(result);
        String className = result.getMethod().getRealClass().getSimpleName();
        String qualifiedName = className + "." + methodName;
        ExtentTest extentTest = ExtentManager.getInstance().createTest(methodName, result.getMethod().getDescription());
        allTests.put(qualifiedName, extentTest);
        ExtentManager.setTest(extentTest);
        //addParametersInReport(result);

    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        System.out.println(iTestResult.getName() + " = [Pass]\n");
        Reporter.log(iTestResult.getName() + " = [Pass]<br>");
        String className = iTestResult.getMethod().getRealClass().getSimpleName();
        ExtentManager.getTest().get().assignCategory(className);
        ExtentManager.getTest().get().createNode(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN).getMarkup());
        ExtentManager.getInstance().flush();
    }

    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        System.out.println(iTestResult.getName() + " = [Fail]\n");
        Reporter.log(iTestResult.getName() + " = [Fail]<br>");
        String className = iTestResult.getMethod().getRealClass().getSimpleName();
        ExtentManager.getTest().get().assignCategory(className);
        ExtentManager.getChild().get().createNode(MarkupHelper.createLabel("Test Failed", ExtentColor.RED).getMarkup())
                .fail(iTestResult.getThrowable());
        ExtentManager.getInstance().flush();
    }

    @Override
    public synchronized void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public synchronized void onStart(ITestContext context) {

    }

    @Override
    public synchronized void onFinish(ITestContext iTestContext) {

    }


    public String getMethodNamewithParams(ITestResult result) {
        String methodName = result.getName();
        String nextLineCharacter = "<br>";
        if (result.getParameters().length > 0 && result.getParameters()[0] instanceof HashMap) {
            methodName = methodName + nextLineCharacter + result.getParameters()[0].toString();
        }
        System.out.println("Method Nname :" + methodName);

        return methodName;
    }
}
