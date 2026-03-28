package com.qa.utils;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	static ConfigUtil configutil;
	static ExcelUtil excelutil;

	//DataProvider for LoginPageTests
	@DataProvider(name = "TestData-LoginPage")
	public static Object[][] GetTestData() throws Exception {

		configutil = new ConfigUtil();

		String filePath = configutil.getTestDataXLPath();
		System.out.println("Filepath = "+filePath);
		String sheetName = "LoginPageData";

		excelutil = new ExcelUtil();

		return excelutil.getExcelData(filePath, sheetName);

	}

}
