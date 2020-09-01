package com.jbk.test;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jbk.pages.LoginPage;
import com.jbk.testClasses.TestBase;

public class LoginTestCases extends TestBase{
	LoginPage lp = null;

	@BeforeMethod
	public void loadUrl() throws Throwable {
		driver=super.launchApplication();
		lp = new LoginPage(driver);
	}
	

	@Test(priority = 1)
	public void checkTitle() {
		Assert.assertTrue(lp.checkTitle(driver));
	}

	@Test(priority = 2)
	public void checkHeading() {
		Assert.assertTrue(lp.checkHeading());
	}

	@Test(priority = 3)
	public void checkLoginBoxMsg() {
		Assert.assertTrue(lp.checkLoginMsg());
	}

	@Test(priority = 4)
	public void checkNavigationBetRegPageAndLogin() {
		Assert.assertTrue(lp.navigateToLoginFromReg(driver));
	}

	@Test(priority = 5)
	public void validateInvalidLoginCred() {
		lp.clickLoginButton();
		Assert.assertTrue(lp.checkErrorMessage());
	}

	
	@AfterMethod
	public void closeApplication(){
		super.CloseLaunchApplication();
	}

}
