package com.ravi.automation.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.ravi.automation.utils.WebEventListners;

public class Browser {
	WebDriver driver;
	
	WebDriver setChromeDriver() {
		System.out.println("Chrome browser");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
		driver = new ChromeDriver();
		driver = eventDriver();
		return driver;
	}
	
	WebDriver setFirefoxDriver() {
		System.out.println("Firefox browser");
		return driver;
	}
	
	WebDriver eventDriver() {
		WebDriver driver;
		EventFiringWebDriver ef_Driver = new EventFiringWebDriver(this.driver);
		WebEventListners webEventListners = new WebEventListners();
		ef_Driver.register(webEventListners);
		driver = ef_Driver;
		return driver;
	}
}
