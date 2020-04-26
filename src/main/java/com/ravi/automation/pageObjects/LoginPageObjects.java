package com.ravi.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;



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
	
	public void loginHome(String loginID, String passwd) {
		try {
			Assert.assertEquals(driver.findElement(user_label).getText(), "Username");
			test.pass("Username label is correct.");
			Assert.assertEquals(driver.findElement(password_label).getText(), "Password");
			test.pass("Password label is correct.");
			Assert.assertEquals(driver.findElement(subit_button).getText(), "Login");
			test.pass("Login button label is correct.");
			Assert.assertTrue(driver.findElement(OMV_logo).isDisplayed());
			test.pass("Logo is visible.");
		} catch (AssertionError e) {
			test.fail(e.getMessage());
			e.printStackTrace();
		}
		driver.findElement(user_input).sendKeys(loginID);
		driver.findElement(password_input).sendKeys(passwd);
	}
	
}
