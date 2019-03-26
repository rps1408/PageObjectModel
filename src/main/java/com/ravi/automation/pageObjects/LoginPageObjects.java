package com.ravi.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageObjects{
	
	By user_input= By.id("username-inputEl");
	By user_label= By.id("username-labelEl");
	By password_input= By.id("password-inputEl");
	By password_label= By.id("password-labelEl");
	By subit_button= By.partialLinkText("Login");
	By OMV_logo= By.xpath("//a[@title='openmediavault']");
	
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;
	public LoginPageObjects(WebDriver driver, ExtentTest test) {
		this.driver = driver;
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
	
	
	  public WebElement findElement(By locator) { 
		  WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		  ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		  return element; 
	  }
	 
	
}
