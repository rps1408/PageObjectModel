package com.ravi.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class BasePageObject {

	public WebDriverWait wait;
	public WebDriver driver;
	public SoftAssert sAssert = new SoftAssert();
	
}
