package com.qa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidUtils extends AppiumUtils {

	static AndroidDriver driver;

	// Constructor
	public AndroidUtils(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
	}

	// Method1: Perform LongPress
	public static void PerformLongPress(WebElement element) {

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));

		System.out.println("Performed long press");
	}

	// Method2: Perform Click
	public static void PerformClick(WebElement element) {

		((JavascriptExecutor) driver).executeScript("mobile: ClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));

		System.out.println("Performed Click");
	}

	// Method3: Perform Double Click
	public static void PerformDoubleClick(WebElement element) {

		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));

		System.out.println("Performed double click");
	}

	public static boolean CheckElementExistense(WebElement element) {

		return element.isDisplayed();
	}

	public static void SrollToText(String text) {

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + text
						+ "\"));"));

	}

}
