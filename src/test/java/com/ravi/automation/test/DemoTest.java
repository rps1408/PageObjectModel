package com.ravi.automation.test;


import java.io.IOException;
import java.util.Map;
import org.testng.annotations.Test;
import com.ravi.automation.base.TestBase;
import com.ravi.automation.pageObjects.LoginPageObjects;
import com.ravi.automation.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class DemoTest extends TestBase{
	
	LoginPageObjects loginPage;
	
	public DemoTest() {
		super();
	}
	
	@Test(dataProvider = "OMV_Data",dataProviderClass = TestUtils.class, groups = {"OMV_Login"})
	public void loginOMV(Map<String, String> data) throws IOException {
		String loginID =  data.get("LoginID");
		String passwd =  data.get("Password");
		driver.get(prop.getProperty("url"));
		test.log(LogStatus.INFO, "ScreenShot:" + test.addBase64ScreenShot(utils.addScreenShot()));
		loginPage = new LoginPageObjects(driver,test);
		loginPage.loginHome(loginID,passwd);
	}

}
