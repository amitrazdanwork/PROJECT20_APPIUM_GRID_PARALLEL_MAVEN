package com.qa.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.utils.ConfigUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest extends DriverManager{

	private AppiumServiceBuilder serviceBuilder;
	private UiAutomator2Options uioptions;
	public static ExtentSparkReporter reporter;
	public static ExtentReports reports;
	String reportName;
	ConfigUtil config;
    public AppiumDriverLocalService service;

	@BeforeSuite(alwaysRun = true)
	public void SuiteSetup() {

		System.out.println("Before Suite execution started");
		
		reports = new ExtentReports();

		reportName = new SimpleDateFormat("dd-MM-yyy_hh.mm.ss").format(new Date());

		reporter = new ExtentSparkReporter(
				".//Reports//" + reportName
						+ ".html");

		reports.attachReporter(reporter);

		reports.setSystemInfo("OS", System.getProperty("os.name"));
		reports.setSystemInfo("JAVA VERSION", System.getProperty("java.version"));

	}

	@BeforeTest(alwaysRun = true)
	@Parameters({"port", "deviceName", "udid", "platformName", "SystemPort"})
	public void ClassSetup(String portNumber, String DeviceName, String UDID, String PlatformName, String SystemPort) throws MalformedURLException {
	    System.out.println("Starting Appium Node Service on port: " + portNumber);

	    config = new ConfigUtil();

	    // 1. Start the local Appium Server (The Node)
	    serviceBuilder = new AppiumServiceBuilder();
	    service = serviceBuilder
	            .withAppiumJS(new File("C:\\Windows\\System32\\node_modules\\appium\\build\\lib\\main.js"))
	            .withIPAddress("127.0.0.1")
	            .usingPort(Integer.parseInt(portNumber))
	            .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
	            .build();
	    service.start();

	    // 2. Set Capabilities (Must match your JSON 'stereotype')
	    uioptions = new UiAutomator2Options();
	    uioptions.setApp(new File(".//src//main//resources//ApkFiles//General-Store.apk").getAbsolutePath());
	    uioptions.setDeviceName(DeviceName);
	    uioptions.setPlatformName(PlatformName);
	    uioptions.setUdid(UDID);
	    uioptions.setAutomationName("UiAutomator2"); // Explicitly set this
	    uioptions.setChromedriverExecutable("C:\\Users\\chromedriver.exe");
	    uioptions.setCapability("uiautomator2ServerLaunchTimeout", 600000);
	    uioptions.setCapability("systemPort", Integer.parseInt(SystemPort));

	    // 3. Connect to the HUB, not the Port
	    // The Hub (4444) will see 'UDID' or 'DeviceName' and route to 'portNumber' automatically
	  //  String serverUrl = "http://127.0.0.1:" + portNumber + "/wd/hub";
	    
	    String serverUrl = "http://127.0.0.1:" + portNumber + "/wd/hub";
	    DriverManager.setDriver(new AndroidDriver(new URL(serverUrl), uioptions));
	    
	    DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterTest
	public void ClassTeardown() throws InterruptedException {
		System.out.println("After Class execution started");

		Thread.sleep(Duration.ofSeconds(10));

		DriverManager.getDriver().quit();
		service.stop();

	}

	@AfterSuite
	public void AfterSetup() throws IOException {

		System.out.println("After Suite execution started");

		
		reports.flush();

		Desktop.getDesktop()
				.browse(new File(".//Reports//"
						+ reportName + ".html").toURI());
	}
	
}
