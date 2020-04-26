package com.way2Automation.pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.ravi.automation.pageObjects.BasePageObject;
import com.ravi.automation.utils.TestUtils;

public class RegistrationPageObjects extends BasePageObject{

	private By registrationTab = By.linkText("Registration");
	private By registrationForm = By.id("load_form");
	private By getLabelByName(String value) {return By.xpath("//label[contains(text(),'"+value+"')]");}
	//private By getInputByLabelName(String value){return By.xpath("//label[contains(text(),'"+value+"')]/following-sibling::input");}
	private By inputBy(String str) {return By.name(str);}
	private By submitBtn = By.className("button");
	
	private TestUtils utils;
	
	public RegistrationPageObjects(WebDriver driver) {
		super.driver = driver;
		utils = new TestUtils(driver);
	}
	
	public void fillRegistrationForm(Map<String, String> testData) {
		driver.findElement(registrationTab).click();
		//switch windows here
		utils.switchToNewTab();
		driver.findElement(registrationForm);
		String nameString = driver.findElement(getLabelByName("Name")).getText();
		sAssert.assertEquals(nameString, "Name:");
		driver.findElement(inputBy("name")).sendKeys(testData.get("Name"));
		
		nameString = driver.findElement(getLabelByName("Phone")).getText();
		sAssert.assertEquals(nameString, "Phone:");
		driver.findElement(inputBy("phone")).sendKeys(testData.get("Phone"));
		
		nameString = driver.findElement(getLabelByName("Email")).getText();
		sAssert.assertEquals(nameString, "Email:");
		driver.findElement(inputBy("email")).sendKeys(testData.get("Email"));
		
		nameString = driver.findElement(getLabelByName("City")).getText();
		sAssert.assertEquals(nameString, "City:", "City didnot matched - ");
		driver.findElement(inputBy("city")).sendKeys(testData.get("City"));
		
		nameString = driver.findElements(getLabelByName("Username")).get(1).getText();
		sAssert.assertEquals(nameString, "Username:");
		driver.findElements(inputBy("username")).get(1).sendKeys(testData.get("Username"));
		
		nameString = driver.findElements(getLabelByName("Password")).get(1).getText();
		sAssert.assertEquals(nameString, "Password:");
		driver.findElements(inputBy("password")).get(1).sendKeys(testData.get("Password"));
		
		nameString = driver.findElement(getLabelByName("Country")).getText();
		sAssert.assertEquals(nameString, "Country:");
		Select selectCountry = new Select(driver.findElement(inputBy("country")));
		selectCountry.selectByValue(testData.get("Country"));
		
		driver.findElements(submitBtn).get(1).click();
		
		sAssert.assertAll();
	}

	
}
