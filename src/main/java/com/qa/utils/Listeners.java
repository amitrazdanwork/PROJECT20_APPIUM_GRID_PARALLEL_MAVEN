package com.qa.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.base.BaseTest;

public class Listeners extends BaseTest implements ITestListener {

	public static ExtentTest test;
	AppiumUtils utils;

	@Override
	public void onTestStart(ITestResult result) {

//		/* create ExtentTest object for every @Test method */
		test = reports.createTest(result.getName(), result.getMethod().getDescription());

		// Add grps to Extent test
		/*
		 * Assign Group/category to test ...capturing value from groups attribute
		 * of @Test annotation
		 */

		// test.assignCategory("Category1");
		String[] strA = result.getMethod().getGroups();

		for (int i = 0; i < strA.length; i++) {
			test.assignCategory(strA[i]);

			System.out.println("GROUP : " + strA[i] + ", ");
		}

		/* Assign Author and Device value to test */

		// test.assignAuthor(context.getCurrentXmlTest().getParameter("author"));

		// test.assignDevice(context.getCurrentXmlTest().getParameter("device"));

	//	utils = new AppiumUtils(driver);

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.pass("Test got passed!!!!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test.fail("Test got failed !!!!");
		test.addScreenCaptureFromPath(utils.getScreenshotPath());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
