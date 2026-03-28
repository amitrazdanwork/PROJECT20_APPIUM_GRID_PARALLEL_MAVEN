package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.utils.AndroidUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage extends AndroidUtils {

	AndroidDriver driver; // assume driver is initialized

	// Page objects
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Air Jordan 4 Retro\"]")
	WebElement Product1Name;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$160.97\"]")
	WebElement Product1Price;

	@AndroidFindBy(xpath = "(//*[@text=\"ADD TO CART\"])[1]")
	WebElement Product1AddToCartButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Air Jordan 1 Mid SE\"]")
	WebElement Product2Name;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\" and @text=\"$120.0\"]")
	WebElement Product2Price;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")
	WebElement Product2AddToCartButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	WebElement CartButton;

	@AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Please add some product at first\"]")
	WebElement ErrorMessage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/counterText\"]")
	WebElement CounterText;

	@AndroidFindBy(xpath = "//*[@text=\"Products\"]")
	WebElement ProductPageLabel;
	

	// Action methods

	// Constructor to initialize elements using PageFactory
	public ProductsPage(AndroidDriver driver) {

		super(driver); // Called constructor of Parent class of LoginPage class i.e. CommonUtil class

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

	}

	// Method1
	public String getProduct1Name() {

		return Product1Name.getText();
	}

	// Method2
	public String getProduct1Price() {

		return Product1Price.getText();
	}

	// Method3
	public void pressProduct1AddToCartButton() {

		Product1AddToCartButton.click();
	}

	// Method1
	public String getProduct2Name() {

		return Product2Name.getText();
	}

	// Method2
	public String getProduct2Price() {

		return Product2Price.getText();
	}

	// Method3
	public void pressProduct2AddToCartButton() {

		Product2AddToCartButton.click();
	}

	// Method3
	public void pressCartButton() {

		CartButton.click();
	}

	// Method4
	public String getErrorMessage() {

		return ErrorMessage.getText();
	}

	// Method4
	public String getCounterText() {
		return CounterText.getText();
	}

	public String getProductPageLabel() {
		return ProductPageLabel.getText();
	}
	
	public boolean CheckPresenceAddToCartBtnProduct1() {
		return Product1AddToCartButton.isDisplayed();
	}
	
	public boolean CheckPresenceAddToCartBtnProduct2() {
		return Product2AddToCartButton.isDisplayed();
	}
	
	public boolean CheckPresenceCartButton() {
		return CartButton.isDisplayed();
	}
	
	public boolean CheckPresenceCounterText() {
		return CounterText.isDisplayed();
	}
}
