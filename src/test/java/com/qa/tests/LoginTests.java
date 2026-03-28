package com.qa.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ImmutableMap;
import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.AndroidUtils;
import com.qa.utils.DataProviderClass;

public class LoginTests extends BaseTest {

	LoginPage loginPage;
	ProductsPage productPage;
	SoftAssert softassert;
	ExtentTest test;

	@BeforeMethod
	public void TestSetup(Method m, ITestContext context) {

		// Activity to start the first page or login page again before execution of
		// every @Test method defined in this class.
		((JavascriptExecutor) getDriver()).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));

	}

	@Test(description = "Verify that the Form page contains below mentioned fields : -\r\n" + "\r\n" + "Labels: -\r\n"
			+ "\"Select the country where you want to shop\"\r\n" + "\"Your Name\"\r\n" + "\"Gender\"\r\n" + "\r\n"
			+ "Textboxes:-\r\n" + "\"First Name\" textbox\r\n" + "\"Last Name\" textbox\r\n" + "\"Email\" textbox\r\n"
			+ "\"Password\" textbox\r\n" + "\"Confirm Password\" textbox\r\n" + "\r\n" + "Drop-downs:-\r\n"
			+ "Country \r\n" + "\r\n" + "Gender radio boxes: -\r\n" + "Male\r\n" + "Female\r\n" + "\r\n"
			+ "Buttons:-\r\n" + "\"Let’s Shop\" button", priority = 0, groups = "Form Tests")
	public void Method1() {

		softassert = new SoftAssert();

		loginPage = new LoginPage(getDriver());

		softassert.assertEquals(loginPage.CountryDropdownExistense(), true);

		softassert.assertEquals(loginPage.NameFieldExistence(), true);

		softassert.assertEquals(loginPage.NameTextboxExistense(), true);

		softassert.assertEquals(loginPage.GenderCheckboxExistence("Male"), true);

		softassert.assertEquals(loginPage.GenderCheckboxExistence("Female"), true);

		softassert.assertEquals(loginPage.CheckLetsShopButtonExisence(), true);

		softassert.assertAll();

	}

	@Test(description = "Verify that user lands on Products page provided user enters valid value and selection for all the required elements on Form page followed by click on Lets Go button", priority = 1, dataProvider = "TestData-LoginPage", dataProviderClass = DataProviderClass.class, groups = "Login Tests")
	public void Method2(String Name, String Country, String Gender) {

		softassert = new SoftAssert();

		loginPage = new LoginPage(getDriver());
		productPage = new ProductsPage(getDriver());

		loginPage.EnterName(Name);

		loginPage.SelectCountry(Country);

		loginPage.SelectGender(Gender);

		loginPage.PressLetsShopButton();

		softassert.assertEquals(productPage.getProductPageLabel(), "Products");

		softassert.assertAll();

	}

	@Test(description = "Verify that system generates a validation message as below when clicking on Let’s Shop button without entering value in Name textbox 'Please enter your name'.", priority = 2, groups = "Login Tests")
	public void Method3() {

		softassert = new SoftAssert();

		loginPage = new LoginPage(getDriver());

		loginPage.PressLetsShopButton();

		softassert.assertEquals(loginPage.getErrorMessage(), "Please enter your name1");

		softassert.assertAll();
	}

	@AfterMethod
	public void TestTeardowh() {
		// NA
	}
}
