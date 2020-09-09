package com.jbk.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.jbk.repository.DownloadPageRepository;
import com.jbk.testClasses.Utility;

public class DownloadsPage extends DownloadPageRepository {
	
	public static WebDriver driver;
	static List<String> websiteList = null;
	static List<String> list = null;
	static String filename = null;
	static String sheetname = null;
	
	public DownloadsPage(WebDriver driver) {
		//super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public static void getExcelfromProperties() {
		filename = System.getProperty("user.dir") +"/src/main/resources/DownloadsList.xlsx";
		sheetname = "Sheet1";
	}
	
	public static void login(){
		username.sendKeys("kiran@gmail.com");
		password.sendKeys("123456");
		loginButton.click();
		downloadPageNavigate.click();
	}
		
		
	public static void getColumnNWeblist(WebElement columnHeader,List<WebElement> columnElements,int ExcelSheetColNo) {

		websiteList = new ArrayList<String>();

		websiteList.add(columnHeader.getText());
		for (WebElement HeaderText : columnElements) {
			websiteList.add(HeaderText.getText());
		}
		System.out.println(websiteList);
		

		getExcelfromProperties();

		list = Utility.readExcelDataWithColno(filename, sheetname, ExcelSheetColNo);
		System.out.println(list);

	}

	public static String checkTitle(ExtentTest test1) {
		//login();
		test1.info("Check title");
		String actTitle = driver.getTitle();
		test1.info(" title is check");
		System.out.println(actTitle);
		return actTitle;
	}

	public static boolean checkColumnHeaderSize() {
		//login();
		ArrayList websiteColumnHeaders = new ArrayList<String>();

		for (WebElement HeaderText : Header) {
			websiteColumnHeaders.add(HeaderText.getText().replaceAll("\n", " "));
		}
		System.out.println(websiteColumnHeaders);

		getExcelfromProperties();

		list = Utility.ReadExcelDataWithRowNCol(filename, sheetname, 1, 8);
		System.out.println(list);

		boolean isCheckSize;
		if (list.size() == websiteColumnHeaders.size()) {
			System.out.println("Column Header Size is match...");
			isCheckSize = true;
		} else {
			System.out.println("Column Header Size is not match...");
			isCheckSize = false;
		}
		return isCheckSize;
	}

	public static boolean checkColumnHeaders() {

		ArrayList websiteColumnHeaders = new ArrayList<String>();

		for (WebElement HeaderText : Header) {
			websiteColumnHeaders.add(HeaderText.getText().replaceAll("\n", " "));
		}
		System.out.println(websiteColumnHeaders);

		getExcelfromProperties();

		ArrayList list = Utility.ReadExcelDataWithRowNCol(filename, sheetname, 1, 8);
		System.out.println(list);

		boolean isCheckList;
		if (list.equals(websiteColumnHeaders)) {
			System.out.println("List of Headers are match....");
			isCheckList = true;
		} else {
			System.out.println("List of Headers are not match....");
			isCheckList = false;
		}
		return isCheckList;

	}

	public static boolean checkListOfVendorsSize() {
		
		getColumnNWeblist(HeaderOfVendors, vendors, 2);

		boolean isCheckSize;
		if (list.size() == websiteList.size()) {
			System.out.println("List of vendors Size is match...");
			isCheckSize = true;
		} else {
			System.out.println("List of vendors Size is not match...");
			isCheckSize = false;
		}
		return isCheckSize;
	}

	public static boolean checkListOfVendors() throws IOException {
		
		getColumnNWeblist(HeaderOfVendors, vendors, 2);

		boolean isCheckList;
		if (list.equals(websiteList)) {
			System.out.println("List of vendors are match....");
			isCheckList = true;
		} else {
			System.out.println("List of vendors are not match....");
			isCheckList = false;
		}
		return isCheckList;
	}
	
	public static boolean checkListOfSrNumber() {
		
		getColumnNWeblist(HeaderOfSr, Sr, 0);

		boolean isCheckList;
		if (list.equals(websiteList)) {
			System.out.println("List of Sr number are match....");
			isCheckList = true;
		} else {
			System.out.println("List of Sr number are not match....");
			isCheckList = false;
		}
		return isCheckList;
	}
	
	public static boolean checkListOfVersion() {
		
		getColumnNWeblist(HeaderOfVersion, version, 3);

		boolean isCheckList;
		if (list.equals(websiteList)) {
			System.out.println("List of version are match....");
			isCheckList = true;
		} else {
			System.out.println("List of version are not match....");
			isCheckList = false;
		}
		return isCheckList;
	}
	
	public static boolean checkListOf32bit() {
		
		getColumnNWeblist(HeaderOf32bit, bit32, 4);

		boolean isCheckList;
		if (list.equals(websiteList)) {
			System.out.println("List of 32bit are match....");
			isCheckList = true;
		} else {
			System.out.println("List of 32bit are not match....");
			isCheckList = false;
		}
		return isCheckList;
	}
	
	public static boolean checkListOf64bit() {

		getColumnNWeblist(HeaderOf64bit, bit64, 5);

		boolean isCheckList;
		if (list.equals(websiteList)) {
			System.out.println("List of 64bit are match....");
			isCheckList = true;
		} else {
			System.out.println("List of 64bit are not match....");
			isCheckList = false;
		}
		return isCheckList;
	}
	
	public static boolean checkListOfCommon() {

		getColumnNWeblist(HeaderOfcommon, common, 6);

		boolean isCheckList;
		if (list.equals(websiteList)) {
			System.out.println("List of common are match....");
			isCheckList = true;
		} else {
			System.out.println("List of common are not match....");
			isCheckList = false;
		}
		return isCheckList;
	}

	public static boolean checkNameColumnImg() {
		
		boolean checkImg = false;
		for (WebElement Img : nameColumnImg) {
			checkImg = Img.isDisplayed();
		}
		if (checkImg = true) {
			System.out.println("Name Images are displayed....");
		}else {
			System.out.println("Name Images are not displayed....");
		}

		return checkImg;
		
	}
	
	public static boolean checkOfficialLinks() throws InterruptedException{
		
		websiteList = new ArrayList<String>();

		websiteList.add(officialWebsiteHeader.getText().replaceAll("\n", " "));
		for (WebElement HeaderText : officialWebsite) {
			websiteList.add(HeaderText.getAttribute("href"));
		}
		System.out.println(websiteList);

		getExcelfromProperties();

		list = Utility.readExcelDataWithColno(filename, sheetname, 7);
		System.out.println(list);
		
		boolean isCheckList;
		if (list.equals(websiteList)) {
			System.out.println("Official websites are match....");
			isCheckList = true;
		} else {
			System.out.println("Official websites are not match....");
			isCheckList = false;
		}
		return isCheckList;

	}

	public static boolean sortSrNo(ExtentTest test1) {
		test1.info("Check Sr Number");
		websiteList = new ArrayList<String>();
		System.out.println(Sr.size());
		boolean isCheckList;
		for (WebElement srCol : Sr) {
			String srNo = srCol.getText();
			websiteList.add(srNo);

		}
		System.out.println(websiteList);

		ArrayList<String> sortedList = new ArrayList<String>();
		
		for (WebElement srCol : Sr) {
			String srNo = srCol.getText();
			sortedList.add(srNo);
		}
		Collections.sort(sortedList);
		System.out.println(sortedList);

		
		if (sortedList.equals(websiteList)) {
			isCheckList = true;
			test1.info( "Sr No List is sorted");
		} else {
			isCheckList = false;
			test1.info( "Sr No List is not sorted");
		}
		return isCheckList;

	}

	

}
