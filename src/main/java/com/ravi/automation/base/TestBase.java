/**
 * @author ravi
 *
 */

package com.ravi.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.ravi.automation.utils.GlobalValues;
import com.ravi.automation.utils.TestUtils;
import com.ravi.automation.utils.WebEventListners;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestBase {
	public WebDriver driver;
	public static Properties prop;
	public WebDriverWait wait;
	public TestUtils utils;
	
	static String CONFIGPATH = GlobalValues.CONFIG;
	public Long waitTime;
	ExtentReports reports;
	public ExtentTest test;
	String suiteName, testName;
	
	static {
		initProperties();
	}
	
	public TestBase() {
		
	}
	
	public void initdriver() {
		String browserName = prop.getProperty("browser");
		Browser browser = new Browser();
		switch (browserName) {
			case "chrome":
				driver = browser.setChromeDriver();
				break;
			case "firefox":
				driver = browser.setFirefoxDriver();
				break;
			default:
				driver = browser.setChromeDriver();
				break;
		}
		
		driver.manage().window().maximize();
		///driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);
	}
	
	@BeforeTest
	public void beforeTest(ITestContext context) {
		suiteName = context.getCurrentXmlTest().getSuite().getName();
		testName = context.getName();
		System.out.println("Exectuting Test: " + testName);
		
		reports = new ExtentReports("Extent_" + suiteName + "_" + testName + ".html");
		
		test = reports.startTest("Test: " + suiteName + "_" + testName);
		initdriver();
		wait = new WebDriverWait(driver, waitTime);
		utils = new TestUtils(driver);
		
		EventFiringWebDriver ef_Driver = new EventFiringWebDriver(this.driver);
		WebEventListners webEventListners = new WebEventListners(test);
		ef_Driver.register(webEventListners);
		driver = ef_Driver;
		
		GlobalValues.setGlobalMapvalue(testName, driver);
	}
	
	@AfterTest
	public void AfterTest() {
		System.out.println("Test End");
		if(driver!=null) {
			driver.quit();
			System.out.println("driver quits.");
		}
	}
	
	@BeforeClass
	public void beforeClass() {
	}
	
	@AfterClass
	public void afterClass() {
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void AfterMethod(ITestResult result) {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, test.addBase64ScreenShot(utils.addScreenShot()));
		}
		
		reports.endTest(test); 
		reports.flush();
		
	}
	
	@BeforeGroups
	public void beforeGroups() {
	}
	
	@AfterGroups
	public void afterGroups() {
	}
	
	@BeforeSuite
	public void beforeSuite(ITestContext context) {
		/*
		 * suiteName = context.getCurrentXmlTest().getSuite().getName(); testName =
		 * context.getName(); System.out.println("Exectution Test: " + testName);
		 */
		waitTime = Long.parseLong(prop.getProperty("defaultWait"));
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("Closing Test: " + suiteName);
		reports.close();
		System.out.println("Report Closed.");
	}
	
	public static void initProperties() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(CONFIGPATH);
			prop.load(file);
		} catch (FileNotFoundException e) {
			System.out.println("can't find config.properties");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
