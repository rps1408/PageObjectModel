package com.ravi.automation.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ExtentManager implements  IReporter {
	private ExtentReports extent;
	private WebDriver driver;
	@Override //TestNG 
	public void generateReport(List<XmlSuite> xmlSuite, List<ISuite> suites, String filePath) {
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath + File.separator + "Extent.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		System.out.println("Report saved to: " + filePath);
		for (ISuite suite : suites) { 
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), Status.PASS);
				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
			}
		}

		extent.flush();
	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());
				driver = (WebDriver) GlobalValues.getGlobalMapvalue(result.getTestName());
				System.out.println("Key: " + result.getTestName());
				System.out.println("Driver: " + driver);
				//test.log(LogStatus.INFO, test.addBase64ScreenShot(new TestUtils(driver).addScreenShot()));
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}

			}
		}
	}
	


}
