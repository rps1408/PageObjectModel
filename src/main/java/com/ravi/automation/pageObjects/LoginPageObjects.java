package com.ravi.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageObjects extends BasePageObject{
	
	private By user_input= By.id("username-inputEl");
	private By user_label= By.id("username-labelEl");
	private By password_input= By.id("password-inputEl");
	private By password_label= By.id("password-labelEl");
	private By subit_button= By.partialLinkText("Login");
	private By OMV_logo= By.xpath("//a[@title='openmediavault']");
	
	ExtentTest test;
	
	public LoginPageObjects(WebDriver driver, ExtentTest test) {
		super.driver = driver;
		this.test = test;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}
	
	public void loginHome() {
		try {
			Assert.assertEquals(findElement(user_label).getText(), "Username");
			test.log(LogStatus.PASS, "Username label is correct.");
			Assert.assertEquals(findElement(password_label).getText(), "Password");
			test.log(LogStatus.PASS, "Password label is correct.");
			Assert.assertEquals(findElement(subit_button).getText(), "Login");
			test.log(LogStatus.PASS, "Login button label is correct.");
			Assert.assertTrue(findElement(OMV_logo).isDisplayed());
			test.log(LogStatus.PASS, "Logo is visible.");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, e.getMessage());
			e.printStackTrace();
		}
		findElement(user_input).sendKeys("admin");
		findElement(password_input).sendKeys("XXXXXXXXXX");
	}
	
}
