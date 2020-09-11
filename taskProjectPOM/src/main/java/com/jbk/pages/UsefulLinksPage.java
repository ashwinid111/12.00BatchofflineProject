package com.jbk.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.jbk.repository.UsefulLinksPageRepository;
import com.jbk.testClasses.UtilityJXL;

public class UsefulLinksPage extends UsefulLinksPageRepository {

	public WebDriver driver;
	static String filename = null;
	static String sheetname = null;
	static String sheetnametable = null;
	static String sheetnamelinks = null;

	public UsefulLinksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static void getExcelfromProperties() {
		filename = System.getProperty("user.dir") + "/src/main/resources/usefulLinks.xls";
		sheetname = "Header";
		sheetnametable = "UsefulLinksTable";
		sheetnamelinks = "Links";
	}

	public void login() {
		username.sendKeys("kiran@gmail.com");
		password.sendKeys("123456");
		loginButton.click();
		usefulLinksPageNavigate.click();
	}

	public boolean checkUsefulLinksSequence(ExtentTest test1) throws Exception {
		test1.info("validate usefulLinks table sequence");
		getExcelfromProperties();

		test1.info("calling values from excel shit");
		ArrayList<String> alExcepted = UtilityJXL.getDataFromExcel(filename, sheetname);

		test1.info("Excepted list:" + alExcepted);
		System.out.println("alExcepted" + alExcepted);

		ArrayList<String> actList = new ArrayList<String>();
		for (WebElement element : headerRow) {
			actList.add(element.getText());
		}
		test1.info("Actual list:" + actList);
		// System.out.println("actList" + actList);

		System.out.println(actList.size() + " actList " + alExcepted.size());

		for (int i = 0; i < alExcepted.size(); i++) {
			if (alExcepted.get(i).equals(actList.get(i))) {
				test1.info("List is macth");
				System.out.println("List is match..");
				test1.info("Testcase is passed");
				return true;

			} else {
				test1.info("List is not match");
				System.out.println("List is not match..");
				test1.info("Testcase is failed");
				return false;
			}
		}
		return false;
	}

	// 2. CheckHeaderSequence

	public boolean checkHeaderSequence(ExtentTest test1) throws Exception {
		test1.info("validate usefulLinks header sequence");
		getExcelfromProperties();

		test1.info("calling values from excel shit");

		ArrayList<String> alAct = new ArrayList<String>();
		List<WebElement> listHeader = header;
		for (WebElement element : listHeader) {
			alAct.add(element.getText());
		}
		test1.info("Actual list size :" + alAct.size());
		System.out.println("Actual list size:" + alAct.size());
		test1.info("Actual list:" + alAct);
		System.out.println("Actual list: " + alAct);

		ArrayList<String> expListRow = UtilityJXL.readAnyRowData(filename, sheetnametable, 0);
		test1.info("Expected list size :" + expListRow.size());
		System.out.println("expList:" + expListRow.size());
		test1.info("Expected list:" + expListRow);
		System.out.println("expList" + expListRow);

		if (alAct.equals(expListRow)) {
			test1.info("List is macth");
			System.out.println("List is match..");
			test1.info("Testcase is passed");
			return true;
		} else {
			test1.info("List is not macthed");
			System.out.println("List is not match..");
			test1.info("Testcase is failed");
			return false;
		}
	}

	// 3. check click links
	public boolean checkClickLinks(ExtentTest test1) throws Exception {
		test1.info("validate usefulLinks click links sequence");
		getExcelfromProperties();

		test1.info("calling values from excel shit");

		ArrayList<String> expListCol = UtilityJXL.readAnyColumnData(filename, sheetnamelinks, 0);
		test1.info("Expected list size :" + expListCol.size());
		System.out.println("expListCol:" + expListCol.size());
		test1.info("Expected list:" + expListCol);
		System.out.println("expListCol" + expListCol);

		ArrayList<String> actLinks = new ArrayList<String>();

		boolean flag = false;
		try {
			String oldtab = driver.getWindowHandle();
			for (WebElement element : goButton) {
				if (element.isEnabled()) {
					element.click();
					// window switch handle
					Set<String> newTab = driver.getWindowHandles();// all window handles are in set
					for (String win : newTab) {
						if (!win.equals(oldtab)) {
							driver.switchTo().window(win);
							actLinks.add(driver.getCurrentUrl());// 2 wins
							test1.info("Actual list size :" + actLinks.size());
							System.out.println("actLinks:" + actLinks.size());
							driver.close();
						}
					}
				}

				driver.switchTo().window(oldtab);
			}
			test1.info("Actual list:" + actLinks);
			System.out.println("actLinks" + actLinks);
		} catch (Exception e) {
			test1.info("Button is not enable....");
			System.out.println("Button is not enable....");
		}

		if (actLinks.equals(expListCol)) {
			test1.info("List is macth");
			System.out.println("List is match..");
			test1.info("Testcase is passed");
			return flag = true;
		} else {
			test1.info("List is not macthed");
			System.out.println("List is not match..");
			test1.info("Testcase is failed");
			return flag;
		}
	}

	// 4. check go button
	public boolean checkClickGOButton(ExtentTest test1) throws Exception {
		test1.info("validate usefulLinks Go button sequence");
		getExcelfromProperties();

		test1.info("calling values from excel shit");

		ArrayList<String> expListCol = UtilityJXL.readAnyColumnData(filename, sheetname, 2);
		test1.info("Expected list:" + expListCol);
		System.out.println("expListCol" + expListCol);

		ArrayList<String> actListCol = new ArrayList<String>();

		for (WebElement clickButton : go) {
			actListCol.add(clickButton.getText());
		}
		test1.info("Actual list:" + actListCol);
		System.out.println("actListCol" + actListCol);

		if (actListCol.equals(expListCol)) {
			test1.info("List is macth");
			System.out.println("List is match..");
			test1.info("Testcase is passed");
			return true;
		} else {
			test1.info("List is not macthed");
			System.out.println("List is not match..");
			test1.info("Testcase is failed");
			return false;
		}
	}

	// 5.Sr column should have all sr no correctly

	public boolean checkSrNo(ExtentTest test1) throws Exception {
		test1.info("validate usefulLinks sr no sequence");
		getExcelfromProperties();

		test1.info("calling values from excel shit");

		ArrayList<String> expListCol = UtilityJXL.readAnyColumnData(filename, sheetname, 0);
		test1.info("Expected list:" + expListCol);
		System.out.println("expListCol" + expListCol);

		ArrayList<String> actListNo = new ArrayList<String>();

		for (WebElement srNum : srNo) {
			actListNo.add(srNum.getText());
		}
		test1.info("Actual list:" + actListNo);
		System.out.println("actListNo" + actListNo);

		if (actListNo.equals(expListCol)) {
			test1.info("List is macth");
			System.out.println("List is match..");
			test1.info("Testcase is passed");
			return true;
		} else {
			test1.info("List is not macthed");
			System.out.println("List is not match..");
			test1.info("Testcase is failed");
			return false;
		}
	}
}
