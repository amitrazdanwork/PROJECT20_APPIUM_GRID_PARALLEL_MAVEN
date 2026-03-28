package com.qa.utils;

import io.appium.java_client.android.AndroidDriver;

public class IOSUtils extends AppiumUtils {

	// Utility methods for IOS specific tests or platform.
	AndroidDriver driver;

	public IOSUtils(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
