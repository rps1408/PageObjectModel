package com.ravi.automation.test;


import java.io.IOException;

import org.testng.annotations.Test;

import com.ravi.automation.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class GoogleTest extends TestBase{
	
	public GoogleTest() {
		super();
	}
	
	@Test
	public void test2() throws IOException {
		driver.get("http://www.google.co.in");
		test.log(LogStatus.INFO, "ScreenShot:" + test.addBase64ScreenShot(utils.addScreenShot()));
	}
	
	
	
}
