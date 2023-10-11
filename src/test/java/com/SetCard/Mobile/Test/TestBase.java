package com.SetCard.Mobile.Test;

import com.SetCard.Mobile.Utilities.ConfigurationReader;
import com.SetCard.Mobile.Utilities.Driver;
import com.SetCard.Mobile.Utilities.MobileUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;


public class TestBase {



    public static ExtentReports report;
    public ExtentHtmlReporter htmlReporter;
    public ExtentTest extentLogger;


    @BeforeTest

    public void setUpTest() {

        report = new ExtentReports();

        //create a report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/report.html";

        //initialize the html reporter with the report path
        htmlReporter = new ExtentHtmlReporter(path);

        //attach the html report to report object
        report.attachReporter(htmlReporter);

        //title in report
        htmlReporter.config().setReportName("Flexlife Mobile App Test");

        //set environment information
        report.setSystemInfo("Environment", "QA");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("Tester", "ibrahim uçar");
        extentLogger = report.createTest("Flexlife Mobile App Test");

    }


    @AfterMethod

    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {
        //if any test fails, it can detect it, take a screenshot at the point and attach to report
        if (result.getStatus() == ITestResult.FAILURE) {
            //record the name of failed test case
            extentLogger.fail(result.getName());

            //take the screenshot and return location of screenshot
            String screenShotPath = MobileUtilities.getScreenshot(result.getName());
            //add your screenshot to your report
            String failed = MobileUtilities.captureScreenShot();
            extentLogger.addScreenCaptureFromPath(screenShotPath).addScreenCaptureFromBase64String(failed);

            //capture the exception and put inside the report
            extentLogger.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentLogger.pass(result.getName());
            //if test skipped, this information will appear on the report
            String screenShotPath = MobileUtilities.getScreenshot(result.getName());
            //add your screenshot to your report
            String passed = MobileUtilities.captureScreenShot();
            extentLogger.addScreenCaptureFromPath(screenShotPath).addScreenCaptureFromBase64String(passed);
        }
        Thread.sleep(1000);
        Driver.closeDriver();

    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        //if test fails
        report.flush();
    }

}


