package com.ravi.automation.test;


import java.io.IOException;

import org.testng.annotations.Test;

import com.ravi.automation.base.TestBase;

public class GoogleTest extends TestBase{
	
	public GoogleTest() {
		super();
	}
	
	@Test(groups = {"OMV_Login"})
	public void test2() throws IOException {
		driver.get("http://www.google.co.in");
		//test.log(Status.INFO, "ScreenShot:" + test.addBase64ScreenShot(utils.addScreenShot()));
	}
	
	
	
}
