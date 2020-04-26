package com.way2automation.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.ravi.automation.base.TestBase;
import com.ravi.automation.utils.TestUtils;
import com.way2Automation.pageObjects.RegistrationPageObjects;

public class RegisterNewUser extends TestBase{

	@Test(dataProvider = "OMV_Data", dataProviderClass = TestUtils.class)
	void registerNewUserTest(Map<String, String> data) {
		driver.get(prop.getProperty("url"));
		
		RegistrationPageObjects register = new RegistrationPageObjects(driver);
		
		register.fillRegistrationForm(data);
	}
}
