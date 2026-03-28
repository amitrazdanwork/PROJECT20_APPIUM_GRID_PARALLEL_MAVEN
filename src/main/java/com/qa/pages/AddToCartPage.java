package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.qa.utils.AndroidUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddToCartPage extends AndroidUtils{

	AndroidDriver driver; // assume driver is initialized

	// WebElements or Page objects

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.androidsample.generalstore:id/productName\"])[1]")
	WebElement SelectedProduct1Name;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.androidsample.generalstore:id/productPrice\"])[1]")
	WebElement SelectedProduct1Price;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.androidsample.generalstore:id/productName\"])[2]")
	WebElement SelectedProduct2Name;

	@AndroidFindBy(xpath = "(//*[@resource-id=\"com.androidsample.generalstore:id/productPrice\"])[2]")
	WebElement SelectedProduct2Price;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/totalAmountLbl\"]")
	WebElement TotalPurchaseAmtValue;

	@AndroidFindBy(xpath = "//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")
	WebElement WarningCheckbox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	WebElement VisitWebSiteButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	WebElement TermsOfConditionButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/message\"]")
	WebElement TermsOfConditionMessage;

	@AndroidFindBy(id = "android:id/button1")
	WebElement TermsCloseButton;

	@AndroidFindBy(xpath = "//*[@text=\"Cart\"]")
	WebElement CartLabel;

	// Constructor to initialize elements using PageFactory
	public AddToCartPage(AndroidDriver driver) {

		super(driver); // Called constructor of Parent class of LoginPage class i.e. CommonUtil class

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

	}

	// Action methods
	public String getSelectedProduct1Name() {
		return SelectedProduct1Name.getText();
	}

	public String getSelectedProduct1Price() {
		return SelectedProduct1Price.getText();
	}

	public String getSelectedProduct2Name() {
		return SelectedProduct2Name.getText();
	}

	public String getSelectedProduct2Price() {
		return SelectedProduct2Price.getText();
	}

	public String getTotalPurchaseAmtValue() {
		return TotalPurchaseAmtValue.getText();
	}

	public void SelectWarningCheckbox() {
		WarningCheckbox.click();
	}

	public void PressVisitWebSiteButton() {
		VisitWebSiteButton.click();
	}

	public void PressTermsOfConditionButton() {
		TermsOfConditionButton.click();
	}

	public String getTermsOfConditionMessage() {
		return TermsOfConditionMessage.getText();
	}

	public void PressTermsCloseButton() {
		TermsCloseButton.click();
	}

	public String getCartLabel() {

		return CartLabel.getText();
	}

	public boolean CheckPresenceWarningCheckbox() {
		return WarningCheckbox.isDisplayed();
	}

	public boolean CheckPresenceVisitWebSiteButton() {
		return VisitWebSiteButton.isDisplayed();
	}

	public boolean CheckPresenceTermsOfConditionButton() {
		return TermsOfConditionButton.isDisplayed();
	}
	
	public void PressWarningCheckbox() {
		WarningCheckbox.click();
		
	}
}
