package com.jbk.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.jbk.repository.UsersPageRepository;
import com.jbk.testClasses.UtilityJXL;
import com.aventstack.extentreports.ExtentTest;
public class UsersPage extends UsersPageRepository {
	
	public static WebDriver driver;
	static String filename = null;
	static String sheetname = null;
	
	public UsersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public static void getExcelfromProperties() {
		filename = System.getProperty("user.dir") +"/src/main/resources/UsersData.xls";
		sheetname = "Sheet1";
		}
	public void login(){
		username.sendKeys("kiran@gmail.com");
		password.sendKeys("123456");
		loginButton.click();
		usersPageNavigate.click();
	}
	
	
	public boolean checkAddUserButton(ExtentTest test1) {
		test1.info("Validate AddUserButton");
		addUserButton.click();
		String actTitle = driver.getTitle();
		test1.info("Expected List "+actTitle);
		System.out.println(actTitle);
	
		String expTitle = "JavaByKiran | Add User";
		test1.info("Actual List "+expTitle);
		if (actTitle.equals(expTitle)) {
			test1.info("Title is Match");
			System.out.println("Title is correct");
			test1.info("Test Case Passed");
			return true;
		} else {
			test1.info("Title is not Match");
			System.out.println("Title is Incorret");
			test1.info("Test Case Failed");
			return false;
		}
	}

	public boolean checkUserCount(ExtentTest test1){
		test1.info("Validate User Count");
		int actUserSize = usercount.size();
		test1.info("Actual User Count "+actUserSize);
		int expUserSize = 4;
		test1.info("Expected User Count "+expUserSize);
		if (actUserSize==expUserSize) {
			test1.info("User Count is Match");
			System.out.println("User count is correct");
			test1.info("Test Case is Passed");
			return true;
		} else {
			test1.info("User Count is not Match");
			System.out.println("User count is incorrect");
			test1.info("Test Case is Failed");
			return false;
		}
	}

	public boolean checkHeaderElementCount(ExtentTest test1) {
		test1.info("Validate Heder Element Count");
		int actHeaderElementSize = headerElementCount.size();
		test1.info("Actual Header Element "+actHeaderElementSize);
		int expHeaderElementSize = 8;
		test1.info("Expected Header Element" +expHeaderElementSize);
		if (actHeaderElementSize == expHeaderElementSize) {
			test1.info("Header Element Count is Match");
			System.out.println("Header Element Count is Correct");
			test1.info("TestCase is Passed");
			return true;
		} else {
			test1.info("Header Element Count is not Match");
			System.out.println("Header Element count is incorrect");
			test1.info("TestCase is Failed");
			return false;
		}
	}

	public boolean checkGender(ExtentTest test1) {
		test1.info("Validate Gender");
		for (WebElement ele : genderList) {
			String gender = ele.getText();
			if (gender.equals("Male") || gender.equals("Female")) {
				test1.info("Gender is Match");
				System.out.println("Gender is correct");
				test1.info("Test Case is Passed");
				return true;
			} else {
				test1.info("Gender is not Match");
				System.out.println("Gender is incorrect");
				test1.info("Test Case is Failed");
				return false;
			}
		}
		return false;
	}
	
	public Boolean checkEmailsSuffix(ExtentTest test1) {
		test1.info("Validate Emails Suffix");
		ArrayList<String> actEmail = new ArrayList<String>();
		for (WebElement element : emailList) {
			actEmail.add(element.getText());
		}
		HashSet<String> emailSuffix = new HashSet<String>();
		for (String emails : actEmail) {
			emailSuffix.add(emails.substring(emails.indexOf("@"), emails.length()));
		}
		if (emailSuffix.size() == 1) {
			test1.info("Emails Suffix is Match");
			test1.info("Test Case Passed");
			return true;
		} else {
			test1.info("Emails Suffix is not Match");
			System.out.println("suffix is absent");
			test1.info("Test Case is Failed");
			return false;
		}
	}
	
	public Boolean checkHeaderList(ExtentTest test1) throws Exception {
		test1.info("Validate Header List");
		getExcelfromProperties();
		ArrayList<String> expList = UtilityJXL.readAnyRowData(filename, sheetname, 0);
		System.out.println("expList" + expList);
		System.out.println("actual list name" +headerList);
		ArrayList<String> actHeaderList = new ArrayList<String>();             
		for (WebElement col : headerList) {
			String headerNamesList = col.getText();

			actHeaderList.add(headerNamesList);
		}
		//System.out.println("actual list1" + actHeaderList.size());
		//System.out.println("actual list2" + headerList.size());
		if (actHeaderList.size() == expList.size()) {
			System.out.println("Header Names is correct" + actHeaderList.size());
			return true;
		} else {
			System.out.println("Header Names is incorrect");
			return false;
		}

	}
	
	public Boolean checkHeaderNames(ExtentTest test1) throws Exception {
		test1.info("Validate Header Names");
		getExcelfromProperties();
		ArrayList<String> expList = UtilityJXL.readAnyRowData(filename, sheetname, 0);
		test1.info("Expected List "+expList);
		System.out.println(expList);
		ArrayList<String> actHeaderList = new ArrayList<String>();
		for (WebElement col : headerList) {
			String headerNamesList = col.getText();
			actHeaderList.add(headerNamesList);
		}
		test1.info("Actual List"+actHeaderList);
		if (actHeaderList.equals(expList)) {
			test1.info("Header List is Match");
			System.out.println("Header Names is correct");
			test1.info("TestCase is Passed");
			return true;
		} else {
			test1.info("Header List is Not Match");
			System.out.println("Header Names is incorrect");
			test1.info("TestCase is Failed");
			return false;
		}

	}

	}
