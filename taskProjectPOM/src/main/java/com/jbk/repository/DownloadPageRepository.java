package com.jbk.repository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DownloadPageRepository{

	//public WebDriver driver;

	@FindBy(xpath = "//tbody//tr//th")
	public static List<WebElement> Header;

	@FindBy(xpath = "//tbody//tr/td[3]")
	public static List<WebElement> vendors;

	@FindBy(xpath = "//tbody//tr/th[3]")
	public static WebElement HeaderOfVendors;
	
	@FindBy(xpath = "//tbody//tr/td[1]")
	public static List<WebElement> Sr;

	@FindBy(xpath = "//tbody//tr//th[4]")
	public static WebElement HeaderOfVersion;
	
	@FindBy(xpath = "//tbody//tr//td[4]")
	public static List<WebElement> version;

	@FindBy(xpath = "//tbody//tr//th[1]")
	public static WebElement HeaderOfSr;
	
	@FindBy(xpath = "//tbody//tr//td[5]")
	public static List<WebElement> bit32;

	@FindBy(xpath = "//tbody//tr//th[5]")
	public static WebElement HeaderOf32bit;
	
	@FindBy(xpath = "//tbody//tr//td[6]")
	public static List<WebElement> bit64;

	@FindBy(xpath = "//tbody//tr//th[6]")
	public static WebElement HeaderOf64bit;
	
	@FindBy(xpath = "//tbody//tr//td[7]")
	public static List<WebElement> common;

	@FindBy(xpath = "//tbody//tr//th[7]")
	public static WebElement HeaderOfcommon;
	
	@FindBy(xpath = "//tbody//tr//td//img")
	public static List<WebElement> nameColumnImg ;
	
	@FindBy(xpath = "//tbody//tr//th[8]")
	public static WebElement officialWebsiteHeader;
	
	@FindBy(xpath = "//tbody//tr//td[8]//a")
	public static List<WebElement> officialWebsite ;
	

	@FindBy(id = "email")
	public static WebElement username;

	@FindBy(id = "password")
	public static WebElement password;
	
	@FindBy(xpath = "//button")
	public static WebElement loginButton;
	
	@FindBy(xpath = "//a[@href='downloads.html']")
	public static WebElement downloadPageNavigate;
	
}
