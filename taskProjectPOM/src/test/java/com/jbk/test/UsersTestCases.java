package com.jbk.test;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jbk.pages.UsersPage;
import com.jbk.testClasses.TestBase;

public class UsersTestCases extends TestBase {
	
	private static final Method method = null;
	UsersPage usersPage = null;
	
	
	public UsersTestCases(){
		super();
	}  
	     
	
	@BeforeMethod
	public void setup1() throws Throwable {
		// driver=super.launchApplication();
		// intialization(method);
		usersPage = new UsersPage(driver);
		usersPage.login();
	}

	@Test(priority=1)
	public void checkAddUserButton(){
	Assert.assertTrue(usersPage.checkAddUserButton(childTest));
	}
	
	@Test(priority=2)
	public void checkUserCount(){
		//test=extent.createTest("checkUserCount");
		Assert.assertTrue(usersPage.checkUserCount(childTest));
		//test.log(Status.PASS, "Test case passed is PassTest");
	}
	
	@Test(priority=3)
	public void checkHeaderElementCount(){
		//test=extent.createTest("checkHeaderElementCount");
		Assert.assertTrue(usersPage.checkHeaderElementCount(childTest));
		//test.log(Status.PASS, "Test case passed is PassTest");
	}
	
	@Test(priority=4)
	public void checkGender(){
		Assert.assertTrue(usersPage.checkGender(childTest));
	
	}
	
	@Test(priority=5)
	public void checkEmailSuffix(){
		Assert.assertTrue(usersPage.checkEmailsSuffix(childTest));
	}
	
	@Test(priority=6)
	public void checkHeaderList() throws Exception  {
		Assert.assertTrue(usersPage.checkHeaderList(childTest));
	}
	
	@Test(priority=7)
	public void checkHeaderName() throws Exception  {
		Assert.assertTrue(usersPage.checkHeaderNames(childTest));
	}
	


}
