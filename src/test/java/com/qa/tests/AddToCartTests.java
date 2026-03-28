package com.qa.tests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;
import com.qa.base.BaseTest;
import com.qa.pages.AddToCartPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.AndroidUtils;

import io.appium.java_client.android.AndroidDriver;

public class AddToCartTests extends BaseTest {

	LoginPage loginpage;
	ProductsPage productpage;
	AddToCartPage addtocartpage;
	SoftAssert softassert;

	@BeforeMethod
	public void TestMethodSetup() {
		
		// Activity to start the first page or login page again before execution of
		// every @Test method defined in this class.
		((JavascriptExecutor) getDriver()).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.AllProductsActivity"));
	}
	
	@Test(description = "Verify that user gets below mentioned labels/Fields on \"Cart' webpage : -\r\n" + "\r\n"
			+ "1- Cart label\r\n"
			+ "2- Checkbox with label as “Send me a e-mail on discounts related to selected products in future”\r\n"
			+ "3- Button with label as Visit to the website to complete the purchase\r\n"
			+ "4- Button with label as “Please read our terms and conditions”", priority = 1)
	public void Method1() throws InterruptedException {

		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());
		addtocartpage = new AddToCartPage(getDriver());
		softassert = new SoftAssert();

		Thread.sleep(Duration.ofSeconds(10));
		// Activity to start the first page or login page again before execution of
		// every @Test method defined in this class.
		((JavascriptExecutor) getDriver()).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.AllProductsActivity"));

		
	/*	loginpage.SelectCountry("Algeria");
		loginpage.EnterName("Amit");
		loginpage.SelectGender("Female");
		loginpage.PressLetsShopButton(); */

		productpage.pressProduct1AddToCartButton();
		productpage.pressProduct2AddToCartButton();

		productpage.pressCartButton();

		softassert.assertEquals(addtocartpage.getCartLabel(), "Cart");
		softassert.assertEquals(addtocartpage.CheckPresenceWarningCheckbox(), true);
		softassert.assertEquals(addtocartpage.CheckPresenceTermsOfConditionButton(), true);
		softassert.assertEquals(addtocartpage.CheckPresenceVisitWebSiteButton(), true);

		softassert.assertAll();

	}

	@Test(description = "Verify that user gets all the selected products on Cart page as per the selection made on Products page", priority = 2)
	public void Method2() {

		String[] selectedProductsNames = new String[2];
		double[] selectedProductsPrices = new double[2];

		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());
		addtocartpage = new AddToCartPage(getDriver());
		softassert = new SoftAssert();

	/*	loginpage.SelectCountry("Algeria");
		loginpage.EnterName("Amit");
		loginpage.SelectGender("Female");
		loginpage.PressLetsShopButton(); */

		productpage.pressProduct1AddToCartButton();
		productpage.pressProduct2AddToCartButton();

		selectedProductsNames[0] = productpage.getProduct1Name();
		selectedProductsNames[1] = productpage.getProduct2Name();

		selectedProductsPrices[0] = Double.parseDouble(productpage.getProduct1Price().replace("$", ""));
		selectedProductsPrices[1] = Double.parseDouble(productpage.getProduct2Price().replace("$", ""));

		productpage.pressCartButton();

	/*	System.out.println("Selected product names : ");
		for (String s : selectedProductsNames) {
			System.out.println(s + ", ");
		}

		System.out.println("Selected product prices : ");
		for (Double s : selectedProductsPrices) {
			System.out.println(s + ", ");
		} */

		List<WebElement> selectedProductsAddToCart = getDriver().findElements(By.id("com.androidsample.generalstore:id/productName"));

		softassert.assertEquals(selectedProductsAddToCart.size(), selectedProductsNames.length);

		softassert.assertEquals(selectedProductsAddToCart.get(0), selectedProductsNames[0]);

		softassert.assertEquals(selectedProductsAddToCart.get(1), selectedProductsNames[1]);

	}

	@Test(description = "Verify that Total Purchase Amount shows the summed up amount of all the selected products", priority = 2)
	public void Method3() {

		String[] selectedProductsNames = new String[2];
		double[] selectedProductsPrices = new double[2];

		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());
		addtocartpage = new AddToCartPage(getDriver());
		softassert = new SoftAssert();

	/*	loginpage.SelectCountry("Algeria");
		loginpage.EnterName("Amit");
		loginpage.SelectGender("Female");
		loginpage.PressLetsShopButton(); */

		productpage.pressProduct1AddToCartButton();
		productpage.pressProduct2AddToCartButton();

		selectedProductsNames[0] = productpage.getProduct1Name();
		selectedProductsNames[1] = productpage.getProduct2Name();

		selectedProductsPrices[0] = Double.parseDouble(productpage.getProduct1Price().replace("$", ""));
		selectedProductsPrices[1] = Double.parseDouble(productpage.getProduct2Price().replace("$", ""));

		double totalSumProductsPage = 0;

		for (double d : selectedProductsPrices) {
			totalSumProductsPage += d;
		}

		productpage.pressCartButton();

	/*	System.out.println("Selected product names : ");
		for (String s : selectedProductsNames) {
			System.out.println(s + ", ");
		}

		System.out.println("Selected product prices : ");
		for (Double s : selectedProductsPrices) {
			System.out.println(s + ", ");
		} */

		List<WebElement> selectedProductsAddToCart = getDriver().findElements(By.xpath("//*[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]"));

		double summedUpAmount = 0;
		for (int i = 0; i < selectedProductsAddToCart.size(); i++) {

			summedUpAmount += Double.parseDouble(selectedProductsAddToCart.get(i).getText().replace("$", ""));
		}
	//	System.out.println("Summed up == " + summedUpAmount);

		softassert.assertEquals(summedUpAmount, totalSumProductsPage);

		softassert.assertAll();

	}

	@Test(description = "Verify that user gets popup with message as below with button to close the same 'Terms of Conditions' provided user long presses the “Read our terms and condition” button. Additionally, user should be able to close the same on clicking the CLOSE button.", priority = 3)
	public void Method4() {

		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());
		addtocartpage = new AddToCartPage(getDriver());

		softassert = new SoftAssert();

	/*	loginpage.SelectCountry("Algeria");
		loginpage.EnterName("Amit");
		loginpage.SelectGender("Female");
		loginpage.PressLetsShopButton(); */

		productpage.pressProduct1AddToCartButton();
		productpage.pressProduct2AddToCartButton();
		productpage.pressCartButton();

		AndroidUtils.PerformLongPress(getDriver().findElement(By.id("com.androidsample.generalstore:id/termsButton")));

		System.out.println(addtocartpage.getTermsOfConditionMessage());

		softassert.assertEquals(addtocartpage.getTermsOfConditionMessage(),
				"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

		addtocartpage.PressTermsCloseButton();
	}

	@Test(description = "Verify that user lands on in-app browser with page url = google.co.in provided user clicks on Button with label as Visit to the website to complete the purchase", priority = 4)
	public void Method5() throws InterruptedException {

		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());
		addtocartpage = new AddToCartPage(getDriver());

		softassert = new SoftAssert();

	/*	loginpage.SelectCountry("Algeria");
		loginpage.EnterName("Amit");
		loginpage.SelectGender("Female");
		loginpage.PressLetsShopButton(); */

		productpage.pressProduct1AddToCartButton();
		productpage.pressProduct2AddToCartButton();
		productpage.pressCartButton();

		AndroidUtils.PerformLongPress(getDriver().findElement(By.id("com.androidsample.generalstore:id/termsButton")));

		addtocartpage.PressTermsCloseButton();

		addtocartpage.PressWarningCheckbox();

		addtocartpage.PressVisitWebSiteButton();

		Thread.sleep(Duration.ofSeconds(10));

		Set<String> ContextHandles = getDriver().getContextHandles();

		for (String string : ContextHandles) {
			System.out.println("Context: " + string);
		}

		if (getDriver().getContext().equalsIgnoreCase("NATIVE_APP"))
			getDriver().context("WEBVIEW_com.androidsample.generalstore"); //context switch from app to webview
		
		softassert.assertEquals(getDriver().getCurrentUrl(), "https://www.google.com/");
	}
	
	@AfterMethod
	public void TestMethodTeardown() {
		//NA
	}
}
