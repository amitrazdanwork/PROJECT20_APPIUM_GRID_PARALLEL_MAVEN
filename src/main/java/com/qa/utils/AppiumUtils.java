package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import io.appium.java_client.android.AndroidDriver;

public class AppiumUtils {

	// Utility methods common for both AndroidUtil class and IOSUtil class.
	AndroidDriver driver;
	String FileName;
	
	
	// Constructor
	public AppiumUtils(AndroidDriver driver) {
		this.driver = driver;
	}

	// Function to Capture screenshot
	public String getScreenshotPath() {

		FileName = new SimpleDateFormat("dd-MM-yyy_hh.mm.ss").format(new Date());
		
		File fileSource = driver.getScreenshotAs(OutputType.FILE);

		String destinationFile = ".//Screenshots//"+FileName+".png";
		
		try {
			FileUtils.copyFile(fileSource,new File(destinationFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error while capturing the screenshot");
		}
		
		return destinationFile;
		
	}
}
