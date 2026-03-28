package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;
import com.qa.utils.AndroidUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends AndroidUtils {

	AndroidDriver driver; // assume driver is initialized

	/* Page objects */
	@AndroidFindBy(xpath = "//android.widget.Spinner[@resource-id=\"com.androidsample.generalstore:id/spinnerCountry\"]")
	WebElement CountryDropdown;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")
	WebElement NameTextbox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Your Name\"]")
	WebElement NameField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Gender\"]")
	WebElement GenderField;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]")
	WebElement MaleGender;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioFemale\"]")
	WebElement FemaleGender;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]")
	WebElement LetsShopButton;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Please enter your name\"]")
	WebElement ErrorMessage;

	@AndroidFindBy(xpath = "//*[@text=\"Select the country where you want to shop\"]")
	WebElement CountryLabel;

	@AndroidFindBy(xpath = "//*[@text=\"Your Name\"]")
	WebElement EnterNameLabel;

	@AndroidFindBy(xpath = "//*[@text=\"Gender\"]")
	WebElement GenderLabel;

	// Constructor to initialize elements using PageFactory
	public LoginPage(AndroidDriver driver) {

		super(driver); // Called constructor of Parent class of LoginPage class i.e. CommonUtil class

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

	}

	/* Action methods */

	// method to select country
	public void SelectCountry(String countryName) {

		CountryDropdown.click();

		AndroidUtils.SrollToText(countryName);

		driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + countryName + "\"]")).click();

	}

	// Method to get the text of Name Label
	public String getNameFieldValue() {
		return NameField.getText();
	}

	// Method to enter name in name text-box
	public void EnterName(String Name) {
		NameTextbox.sendKeys(Name);
	}

	public void SelectGender(String gender) {

		if (gender.equalsIgnoreCase("Male"))
			MaleGender.click();
		else if (gender.equalsIgnoreCase("Female"))
			FemaleGender.click();
		else
			System.out.println("Invalid gender value : " + gender);
	}

	// Method to get the Gender field
	public String getGenderFieldValue() {
		return GenderField.getText();
	}

	public void PressLetsShopButton() {
		LetsShopButton.click();
	}

	public String getErrorMessage() {

		return ErrorMessage.getText();
	}

	public String getCountryLabel() {
		return CountryLabel.getText();
	}

	public String getEnterNameLabel() {
		return EnterNameLabel.getText();
	}

	public String getGenderLabel() {
		return GenderLabel.getText();
	}

	public boolean CountryDropdownExistense() {

		return CountryDropdown.isDisplayed();
	}

	public boolean NameTextboxExistense() {

		return NameTextbox.isDisplayed();
	}

	public boolean NameFieldExistence() {

		return NameField.isDisplayed();
	}

	public boolean GenderFieldExistence() {

		return GenderField.isDisplayed();
	}

	public boolean GenderCheckboxExistence(String Gender) {

		if (Gender.equalsIgnoreCase("Male"))
			return MaleGender.isDisplayed();
		else if (Gender.equalsIgnoreCase("Female"))
			return FemaleGender.isDisplayed();
		else {
			System.out.println("Invalid Gender : " + Gender);
			return false;
		}

	}

	public boolean CheckLetsShopButtonExisence() {

		return LetsShopButton.isDisplayed();
	}

}
