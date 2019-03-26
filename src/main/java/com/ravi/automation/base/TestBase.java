/**
 * @author ravi
 *
 */

package com.ravi.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.ravi.automation.utils.TestUtils;
import com.ravi.automation.utils.WebEventListners;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class TestBase {
	public WebDriver driver;
	public Properties prop;
	public WebDriverWait wait;
	public TestUtils utils;
	
	final String CONFIGPATH = System.getProperty("user.dir")+"/src/main/resources/config.properties";
	public Long waitTime;
	ExtentReports reports;
	public ExtentTest test;
	
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
		
		
		driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);
	}
	
	@BeforeTest
	public void beforeTest(ITestContext context) {
		String suiteName = context.getCurrentXmlTest().getSuite().getName();
		String testName = context.getName();
		System.out.println("Executing test: " + testName);
		
		initProperties();
		waitTime = Long.parseLong(prop.getProperty("defaultWait"));
		
		//Extent Report init
		reports = new ExtentReports("Extent_" + suiteName + "_" + testName + ".html");
		test = reports.startTest("Test: " + suiteName + "_" + testName);
		
		initdriver();
		wait = new WebDriverWait(driver, waitTime);
		utils = new TestUtils(driver);
		
		EventFiringWebDriver ef_Driver = new EventFiringWebDriver(this.driver);
		WebEventListners webEventListners = new WebEventListners(test);
		ef_Driver.register(webEventListners);
		driver = ef_Driver;
	}
	
	@BeforeMethod
	void beforeMethod() {
		
	}
	
	@AfterMethod
	void AfterMethod() {
		
	}
	
	
	
	@BeforeSuite
	void beforeSuite() {
		
	}
	
	public void initProperties() {
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
	
	@AfterTest
	public void afterSuite() {
		reports.endTest(test);
		reports.flush();
		reports.close();
		
		if(driver!=null) {
			driver.quit();
			System.out.println("driver quits.");
		}
	}
	
}
