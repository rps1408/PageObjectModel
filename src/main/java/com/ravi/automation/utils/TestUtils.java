package com.ravi.automation.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;


public class TestUtils{
	
	WebDriver driver;
	String capturedScreenShotPath;
	public TestUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	//to take screenshot
	public void takeScreenshot() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		File dest = new File(currentDir + "/screenshots");
		if (!dest.exists()) {
			dest.mkdir();
		}
		dest = new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png");
		capturedScreenShotPath = dest.getAbsolutePath();
		System.out.println("Screenshot save to: " + capturedScreenShotPath);
		FileHandler.copy(scrFile, dest);
	}
	
	public String addScreenShot() {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		File dest = new File(currentDir + "/screenshots");
		if (!dest.exists()) {
			dest.mkdir();
		}
		dest = new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png");
		capturedScreenShotPath = dest.getAbsolutePath();
		System.out.println("Screenshot save to: " + capturedScreenShotPath);
		try {
			FileHandler.copy(scrFile, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dest.getAbsolutePath();
	}
	
	@DataProvider(name = "OMV_Data")
	public static Object[][] testData(ITestContext context){
		String sheetName = context.getName();
		
		Map<String, String> testData;
		Object[][] data = null;
		try {
			Sheet sheet = ExcelUtils.getSheetByName(sheetName);

			int rowNum = sheet.getPhysicalNumberOfRows();
			int cellNum = sheet.getRow(0).getPhysicalNumberOfCells();
			int j;
			data = new Object[rowNum-1][1];
			Row keyRow = sheet.getRow(0);
			
			for(int i = 1;i<rowNum;i++) {
				testData = new HashMap<>();
				Row row = sheet.getRow(i);
				for (j = 0; j < cellNum; j++) {
					String key = keyRow.getCell(j).getStringCellValue();
					String value = row.getCell(j).getStringCellValue();
					testData.put(key, value);
				}
				data[i-1][0] = testData;
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	public void switchToNewTab() {
		String oldTab = driver.getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		tabs.remove(oldTab);
		driver.switchTo().window(tabs.get(0));
	}
}
