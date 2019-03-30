package com.ravi.automation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ravi.automation.base.TestBase;

public class ExcelUtils {
	
	static final String path = System.getProperty("user.dir") + TestBase.prop.getProperty("testDataSheet");
	
	public static void main(String[] args) {
		
		try {
			Sheet sheet = getSheetByName("OMV_Login");
			
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
			System.out.println(cell.getStringCellValue());
			cell = row.getCell(1);
			System.out.println(cell.getStringCellValue());
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Sheet getSheetByName(String sheetName) throws EncryptedDocumentException, FileNotFoundException, IOException {
		return WorkbookFactory.create(new FileInputStream(path)).getSheet(sheetName);
	}
}
