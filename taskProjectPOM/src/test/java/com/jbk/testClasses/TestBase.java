package com.jbk.testClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.jbk.pages.LoginPage;

public class TestBase {
	public static WebDriver driver;
	Properties prop = null;
	FileInputStream fis = null;
	LoginPage lp=null;
	
	String readAnyProperty( String propName) {
		String val = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "/Config.properties");
			prop = new Properties();
			prop.load(fis);

			val = prop.getProperty(propName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	public WebDriver launchApplication() throws Throwable {
		String URL = readAnyProperty("url");
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		lp=new LoginPage(driver);
		
		return driver;
	}
	
	public LoginPage loadLoginpage(){
		
		LoginPage lp=new LoginPage(driver);
		return lp;
	}
	public void CloseLaunchApplication() {
		driver.close();
	}

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;

	@BeforeSuite
	public void setup() {
		String  Pagesname = readAnyProperty("PagesTest");
		String  TesterName = readAnyProperty("TesterName");
		
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport/reportOfDownloadPage.html");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("DownloadPage Test Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", Pagesname);
		extent.setSystemInfo("Tester", TesterName);
		extent.setSystemInfo("OS", "Win10");
		extent.setSystemInfo("Browser", "Chrome");
		

	}
	
	@BeforeClass
	 public void parentSetup() {
		
		 parentTest= extent.createTest(getClass().getSimpleName());
		 
	 }
	
	@BeforeMethod
	public void intialization(Method method) {

		String URL = readAnyProperty("url");
		String browser = readAnyProperty("browser");
		//String browser = prop.getProperty("browser");
		//String url = prop.getProperty("urldownload");
		
		childTest = parentTest.createNode(method.getName());

		if ("Chrome".equals(browser)) {
			System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
			driver = new ChromeDriver();

		}
		if ("Firefox".equals(browser)) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/java/driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
		
		

	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			// MarkupHelper is used to display the output in different colors
			childTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			childTest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			String screenshotPath = TakeScreenshot(driver, result.getName());

			childTest.fail("Test Case Failed Snapshot is below " + childTest.addScreenCaptureFromPath(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			// logger.log(Status.SKIP, "Test Case Skipped is
			// "+result.getName());
			childTest.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			childTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		}
		driver.close();
	}

	public static String TakeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@AfterSuite
	public void closeExtentReport() {
		extent.flush();

	}


}
