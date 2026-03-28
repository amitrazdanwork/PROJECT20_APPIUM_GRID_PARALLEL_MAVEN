package com.qa.tests;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;
import com.qa.base.BaseTest;
import com.qa.pages.AddToCartPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

public class ProductTests extends BaseTest {

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

	@Test(description = "Verify that the Products page contains below mentioned fields and specifications : -\r\n"
			+ "\r\n" + "Labels: -\r\n" + "\"Air Jorden 4 Retro\"\r\n" + "\"Air Jorden 1 Mid SE\"\r\n"
			+ "Price of Product1\r\n" + "Price of Product2\r\n" + "\r\n" + "Buttons:-\r\n"
			+ "\"Add To cart\" button for Product1 and Product 2\r\n" + "“Cart”\r\n" + "“Cart Counter”", priority = 1)
	public void Method1() throws InterruptedException {

		softassert = new SoftAssert();
		// loginpage = new LoginPage(driver);
		productpage = new ProductsPage(getDriver());

		/* -> I have declared @BeforeMethod with code to Start login page as part of Start Activity
		 * 
		 * loginpage.SelectCountry("China");
		 * 
		 * loginpage.EnterName("Amit");
		 * 
		 * loginpage.SelectGender("Female");
		 * 
		 * loginpage.PressLetsShopButton();
		 */

		Thread.sleep(Duration.ofSeconds(10));
		// Activity to start the first page or login page again before execution of
		// every @Test method defined in this class.
		((JavascriptExecutor) getDriver()).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.AllProductsActivity"));

		softassert.assertEquals(productpage.getProduct1Name(), "Air Jordan 4 Retro");
		softassert.assertEquals(productpage.getProduct2Name(), "Air Jordan 1 Mid SE");
		softassert.assertEquals(productpage.getProduct1Price(), "$160.97");
		softassert.assertEquals(productpage.getProduct2Price(), "$120.0");
		softassert.assertEquals(productpage.CheckPresenceAddToCartBtnProduct1(), true);
		softassert.assertEquals(productpage.CheckPresenceAddToCartBtnProduct2(), true);
		softassert.assertEquals(productpage.CheckPresenceCartButton(), true);

		productpage.pressProduct1AddToCartButton();

		softassert.assertEquals(productpage.CheckPresenceCounterText(), true);

		softassert.assertAll();

	}

	@Test(description = "Verify that user gets should get the counter updated based on the product selection made", priority = 2)
	public void Method2() {

		softassert = new SoftAssert();
		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());

		/*
		 * loginpage.SelectCountry("China");
		 * 
		 * loginpage.EnterName("Amit");
		 * 
		 * loginpage.SelectGender("Female");
		 * 
		 * loginpage.PressLetsShopButton();
		 */

		productpage.pressProduct1AddToCartButton();

		productpage.pressProduct2AddToCartButton();

		softassert.assertEquals(productpage.getCounterText(), "2");

		softassert.assertAll();

	}

	@Test(description = "Verify that user should be able to proceed to cart page successfully provided user has selected certain products on Products page", priority = 3)
	public void Method3() {

		softassert = new SoftAssert();
		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());
		addtocartpage = new AddToCartPage(getDriver());

		/*
		 * loginpage.SelectCountry("China");
		 * 
		 * loginpage.EnterName("Amit");
		 * 
		 * loginpage.SelectGender("Female");
		 * 
		 * loginpage.PressLetsShopButton();
		 */

		productpage.pressProduct1AddToCartButton();

		productpage.pressProduct2AddToCartButton();

		productpage.pressCartButton();

		softassert.assertEquals("Cart", addtocartpage.getCartLabel());

		softassert.assertAll();

	}

	@Test(description = "Verify that user gets an error message as \"Please add some product at first\" provided proceeds to cart page without selecting any products on Products page", priority = 4)
	public void Method4() {

		softassert = new SoftAssert();
		loginpage = new LoginPage(getDriver());
		productpage = new ProductsPage(getDriver());
		addtocartpage = new AddToCartPage(getDriver());

		/*
		 * loginpage.SelectCountry("China");
		 * 
		 * loginpage.EnterName("Amit");
		 * 
		 * loginpage.SelectGender("Female");
		 * 
		 * loginpage.PressLetsShopButton();
		 */

		productpage.pressCartButton();

		softassert.assertEquals("Please add some product at first", productpage.getErrorMessage());

		softassert.assertAll();

	}

	@AfterMethod
	public void TestMethodTeardowh() {
		// NA
	}
}
