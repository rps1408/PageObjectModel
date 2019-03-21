/**
 * @author ravi
 *
 */

package com.ravi.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ravi.automation.utils.WebEventListners;


public class TestBase {
	public WebDriver driver;
	Properties prop;
	WebDriverWait wait;
	EventFiringWebDriver ef_Driver;
	WebEventListners webEventListners;
	final String CONFIGPATH = System.getProperty("user.dir")+"/src/main/resources/config.properties";
	
	public TestBase() {
		initProperties();
		initdriver();
		wait = new WebDriverWait(driver, Integer.parseUnsignedInt(prop.getProperty("defaultWait")));
	}
	
	public void initdriver() {
		String browserName = prop.getProperty("browser");
		Browser browser = new Browser();
		switch (browserName) {
			case "chrome":
				driver = browser.setChromeDriver();
				break;
			case "firefox":
				driver = browser.setFirefoxDriver();
				break;
			default:
				driver = browser.setChromeDriver();
				break;
		}
		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("defaultWait")), TimeUnit.SECONDS);
		
		
		//return driver;
	}
	
	public void initProperties() {
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(CONFIGPATH);
			prop.load(file);
		} catch (FileNotFoundException e) {
			System.out.println("can't find config.properties");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebElement findElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("", element);
		return element;
	}
	
}
