package com.jbk.testClasses;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

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


}
