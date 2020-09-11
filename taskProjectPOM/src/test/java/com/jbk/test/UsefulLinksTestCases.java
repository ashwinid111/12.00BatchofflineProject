package com.jbk.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jbk.pages.UsefulLinksPage;
import com.jbk.testClasses.TestBase;

public class UsefulLinksTestCases extends TestBase {

	UsefulLinksPage usefulLinksPage;

	public UsefulLinksTestCases() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		usefulLinksPage = new UsefulLinksPage(driver);
		usefulLinksPage.login();
	}

	@Test(priority = 1)
	public void checkUsefulLinksSequenceTest() throws Exception {
		Assert.assertTrue(usefulLinksPage.checkUsefulLinksSequence(childTest));
	}

	@Test(priority = 2)
	public void checkHeaderLinksTest() throws Exception {
		Assert.assertTrue(usefulLinksPage.checkHeaderSequence(childTest));
	}

	@Test(priority = 3)
	public void checkClickLinksTest() throws Exception {
		Assert.assertTrue(usefulLinksPage.checkClickLinks(childTest));
	}

	@Test(priority = 4)
	public void checkClickGOButtonTest() throws Exception {
		Assert.assertTrue(usefulLinksPage.checkClickGOButton(childTest));
	}

	@Test(priority = 5)
	public void checkSrNoTest() throws Exception {
		Assert.assertTrue(usefulLinksPage.checkSrNo(childTest));
	}
}
