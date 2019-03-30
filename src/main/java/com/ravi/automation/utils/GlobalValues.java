package com.ravi.automation.utils;

import java.util.HashMap;
import java.util.Map;

public class GlobalValues {

	public static final String CONFIG = System.getProperty("user.dir")+"/src/main/resources/config.properties";

	public static Map<Object, Object> globalMap = new HashMap<Object, Object>();
	
	public static Object getGlobalMapvalue(Object key) {
		return globalMap.get(key);
	}
	
	public static void setGlobalMapvalue(Object key, Object value) {
		globalMap.put(key, value);
	}
	
}
