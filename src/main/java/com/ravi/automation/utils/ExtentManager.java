package com.ravi.automation.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentManager implements  IReporter {
	private ExtentReports reports;
	private WebDriver driver;
	@Override //TestNG 
	public void generateReport(List<XmlSuite> xmlSuite, List<ISuite> suites, String filePath) {
		reports = new ExtentReports(filePath + File.separator + "Extent.html", true);
		System.out.println("Report saved to: " + filePath);
		for (ISuite suite : suites) { 
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		reports.flush();
		reports.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = reports.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
				
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

				reports.endTest(test);
			}
		}
	}
	
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	 

}
