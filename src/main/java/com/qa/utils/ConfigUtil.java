package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

	/* Objects */
	File file;
	Properties prop;

	// Constructor
	public ConfigUtil() {
		// TODO Auto-generated constructor stub
		file = new File(".//src//main//resources//Config//config.properties");
		prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found at specified location : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("I/O operation - failed" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("Config file got loaded successfully !!!");
	}

	/* Action methods for reading the configProperties. */
	// Method 1
	public String getApkFilePath() {
		return prop.getProperty("ApkFilePath");
	}

	// Method 2
	public String getTestEnv() {
		return prop.getProperty("TestEnv");
	}

	// Method 3
	public String getIPAddress() {
		return prop.getProperty("IPAddress");
	}

	// Method 4
	public String getPortNumber() {
		return prop.getProperty("PortNumber");
	}

	// Method 5
	public String getDeviceName() {
		return prop.getProperty("DeviceName");
	}

	// Method 6
	public String getPlatformName() {
		return prop.getProperty("platformName");
	}

	public String getMainJSFilePath() {
		return prop.getProperty("mainJSFilePath");
	}

	public String getTestDataXLPath() {
		return prop.getProperty("testdatafilepath");

	}

}
