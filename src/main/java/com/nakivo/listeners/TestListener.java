package com.nakivo.listeners;

import com.nakivo.reports.ExtentReportManager;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentReportManager.initReports();
        log.info("Start suite: " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("Start test: " + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReports();
        log.info("Test finish " + context.getName());
    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
