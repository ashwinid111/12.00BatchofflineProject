package com.jbk.test;


import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.jbk.pages.DownloadsPage;
import com.jbk.testClasses.TestBase;

public class DownloadsTestCases extends TestBase{
	
	public DownloadsPage downloadsPage;
	
	@BeforeMethod
	public void loginTest(){
		DownloadsPage downloadsPage = new DownloadsPage(driver);
		downloadsPage.login();
	}
	
	/*@Test(priority=1)
	public void checkTitleTestCases(){
		
		Assert.assertEquals(downloadsPage.checkTitle(childTest), "JavaByKiran | Downloads");
		
	}
	
	@Test(priority=2)
	public void checkAllTableHeadersSize(){
		
	//	Assert.assertTrue(false);
		
		Assert.assertTrue(downloadsPage.checkColumnHeaderSize());
		
	}*/
//	
//	@Test(priority=3)
//	public void checkAllTableHeadersSpelling(){
//		
//		Assert.assertTrue(downloadsPage.checkColumnHeaders());
//		
//	}
//	
//	@Test(priority=4)
//	public void CheckListOfVendorSize(){
//		
//		Assert.assertTrue(downloadsPage.checkListOfVendorsSize());
//	}
//	
//	@Test(priority=5)
//	public void CheckListOfVendorSpelling() throws IOException{
//		
//		Assert.assertTrue(downloadsPage.checkListOfVendors());
//	}
//	
//	@Test(priority=6)
//	public void CheckListOfSrNumber(){
//		
//		Assert.assertTrue(downloadsPage.checkListOfSrNumber());
//	}
//
//	@Test(priority=7)
//	public void CheckListOfVersionColumn(){
//		
//		Assert.assertTrue(downloadsPage.checkListOfVersion());
//	}
//	
//	@Test(priority=8)
//	public void CheckListOf32bitColumn(){
//		
//		Assert.assertTrue(downloadsPage.checkListOf32bit());
//	}
//	
//	@Test(priority=9)
//	public void CheckListOf64bitColumn(){
//		
//		Assert.assertTrue(downloadsPage.checkListOf64bit());
//	}
//	
	@Test(priority=10)
	public void CheckListOfCommonColumn(){
		
	Assert.assertTrue(downloadsPage.checkListOfCommon());
	}
	
//	@Test(priority=11)
//	public void CheckNameImg(){
//		
//		Assert.assertTrue(downloadsPage.checkNameColumnImg());
//	}
//	
//	@Test(priority=12)
//	public void CheckOfficialWebsite() throws InterruptedException{
//		
//		Assert.assertTrue(downloadsPage.checkOfficialLinks());
//	}
	
}
