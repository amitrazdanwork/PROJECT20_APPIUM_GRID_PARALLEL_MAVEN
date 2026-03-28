package com.qa.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

	// ThreadLocal ensures each thread has its own driver instance
	private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

	public static AndroidDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(AndroidDriver appiumDriver) {
		driver.set(appiumDriver);
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}

}
