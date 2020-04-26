package com.ravi.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtility {

	public File getFile(String name) {
		return new File(getClass().getClassLoader().getResource(name).getFile());
	}
	
	public FileInputStream getFileAsStream(String name) throws FileNotFoundException {
		return new FileInputStream(getFile(name));
	}
	
}
