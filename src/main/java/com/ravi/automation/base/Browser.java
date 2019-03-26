package com.ravi.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
	WebDriver driver;
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static String driverPath;
	//public ExtentTest test;
	
	WebDriver setChromeDriver() {
		
		switch (OS) {
			case "linux":
				driverPath = "/src/main/resources/chromedriver";
				break;
			case "windows":
				driverPath = "/src/main/resources/chromedriver.exe";
			
			default:
				System.out.println("Unknown OS: " + OS);
				break;
		}
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverPath);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		//driver = eventDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	WebDriver setFirefoxDriver() {
		System.out.println("Firefox browser");
		return driver;
	}
	
	/*
	 * WebDriver eventDriver() { WebDriver driver; EventFiringWebDriver ef_Driver =
	 * new EventFiringWebDriver(this.driver); WebEventListners webEventListners =
	 * new WebEventListners(test); ef_Driver.register(webEventListners); driver =
	 * ef_Driver; return driver; }
	 */
}
