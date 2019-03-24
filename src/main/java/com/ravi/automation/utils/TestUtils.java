package com.ravi.automation.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class TestUtils{
	
	WebDriver driver;
	
	public TestUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	//to take screenshot
	public void takeScreenshot() throws IOException {
		//TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		File dest = new File(currentDir + "/screenshots");
		if (!dest.exists()) {
			dest.mkdir();
		}
		dest = new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png");
		String path = dest.getAbsolutePath();
		System.out.println("Screenshot save to: " + path);
		FileHandler.copy(scrFile, dest);
	}
}
