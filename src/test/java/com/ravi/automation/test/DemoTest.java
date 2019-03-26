package com.ravi.automation.test;


import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ravi.automation.base.TestBase;
import com.ravi.automation.pageObjects.LoginPageObjects;
import com.relevantcodes.extentreports.LogStatus;

public class DemoTest extends TestBase{
	
	LoginPageObjects loginPage;
	
	public DemoTest() {
		super();
		
	}
	
	@BeforeTest
	void beforeTest() {
		
	}
	
	@Test
	public void loginOMV() throws IOException {
		driver.get(prop.getProperty("url"));
		test.log(LogStatus.INFO, "ScreenShot:" + test.addBase64ScreenShot(utils.addScreenShot()));
		loginPage = new LoginPageObjects(driver,test);
		loginPage.loginHome();
	}
	
	
	
	
}
