package com.ravi.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

	public WebDriverWait wait;
	public WebDriver driver;
	
	public WebElement findElement(By locator) { 
		  WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		  ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		  return element; 
	  }
}
