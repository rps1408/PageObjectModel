package com.ravi.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	WebDriver driver;
	//private static String OS = System.getProperty("os.name").toLowerCase();
	//private static String driverPath;
	//public ExtentTest test;
	
	WebDriver setChromeDriver() {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		//driver = eventDriver();
		
		return driver;
	}
	
	WebDriver setFirefoxDriver() {
		System.out.println("Firefox browser");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		return driver;
	}
	
	WebDriver setIEDriver() {
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		return driver;
	}
	/*
	 * WebDriver eventDriver() { WebDriver driver; EventFiringWebDriver ef_Driver =
	 * new EventFiringWebDriver(this.driver); WebEventListners webEventListners =
	 * new WebEventListners(test); ef_Driver.register(webEventListners); driver =
	 * ef_Driver; return driver; }
	 */
}
