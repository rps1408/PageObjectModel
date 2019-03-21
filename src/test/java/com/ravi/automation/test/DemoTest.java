package com.ravi.automation.test;

import java.sql.Driver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ravi.automation.base.TestBase;

public class DemoTest extends TestBase{
	
	public DemoTest() {
		
		super();
		System.out.println("Demo constructor");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("DemoTest - Before method.");
	}
	
	@Test
	public void test1() {
		System.out.println("DemoTest 1.");
		driver.get("https://www.google.com");
	}
	
	@AfterTest
	public void afterTest() {
		if(driver!=null) {
			driver.quit();
			System.out.println("driver quits.");
		}
	}
	
}
