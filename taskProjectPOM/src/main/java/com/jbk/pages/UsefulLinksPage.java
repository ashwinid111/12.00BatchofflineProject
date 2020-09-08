package com.jbk.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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
		filename = System.getProperty("user.dir") +"/src/main/resources/usefulLinks.xls";
		sheetname = "Header";
		sheetnametable = "UsefulLinksTable";
		sheetnamelinks = "Links";
	}
	
	public void login(){
		username.sendKeys("kiran@gmail.com");
		password.sendKeys("123456");
		loginButton.click();
		usefulLinksPageNavigate.click();
	}
	
	public boolean checkUsefulLinksSequence() throws Exception {
		getExcelfromProperties();

		ArrayList<String> alExcepted = UtilityJXL.getDataFromExcel(filename, sheetname);
		System.out.println("alExcepted" + alExcepted);

		ArrayList<String> actList = new ArrayList<String>();
		for (WebElement element : headerRow) {
			actList.add(element.getText());
		}
		System.out.println("actList" + actList);
		System.out.println(actList.size() + " actList " + alExcepted.size());

		for (int i = 0; i < alExcepted.size(); i++) {
			if (alExcepted.get(i).equals(actList.get(i))) {
				System.out.println("List is match..");
				return true;
			} else {
				System.out.println("List is not match..");
				return false;
			}
		}
		return false;
	}

	// 2. CheckHeaderSequence
	public boolean checkHeaderSequence() throws Exception {

		getExcelfromProperties();
		
		ArrayList<String> alAct = new ArrayList<String>();
		List<WebElement> listHeader = header;
		for (WebElement element : listHeader) {
			alAct.add(element.getText());
		}
		System.out.println("actualList:" + alAct.size());

		ArrayList<String> expListRow = UtilityJXL.readAnyRowData(filename, sheetnametable, 0);
		System.out.println("expList:" + expListRow.size());
		System.out.println("expList" + expListRow);

		if (alAct.equals(expListRow)) {
			System.out.println("List is match..");
			return true;
		} else {
			System.out.println("List is not match..");
			return false;
		}
	}

	// 3. check click links
	public boolean checkClickLinks() throws Exception {
		
		getExcelfromProperties();
		
		ArrayList<String> expListCol = UtilityJXL.readAnyColumnData(filename, sheetnamelinks, 0);
		System.out.println("expListCol:" + expListCol.size());
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
							System.out.println("actLinks:" + actLinks.size());
							driver.close();
						}
					}
				}

				driver.switchTo().window(oldtab);
			}
			System.out.println("actLinks" + actLinks);
		} catch (Exception e) {
			System.out.println("Button is not enable....");
		}

		if (actLinks.equals(expListCol)) {
			System.out.println("List is match..");
			return flag = true;
		} else {
			System.out.println("List is not match..");
			return flag;
		}
	}

	// 4. check go button
	public boolean checkClickGOButton() throws Exception {
		getExcelfromProperties();
		
		ArrayList<String> expListCol = UtilityJXL.readAnyColumnData(filename, sheetname, 2);
		System.out.println("expListCol" + expListCol);

		ArrayList<String> actListCol = new ArrayList<String>();

		for (WebElement clickButton : go) {
			actListCol.add(clickButton.getText());
		}
		System.out.println("actListCol" + actListCol);

		if (actListCol.equals(expListCol)) {
			System.out.println("List is match..");
			return true;
		} else {
			System.out.println("List is not match..");
			return false;
		}
	}

	// 5.Sr column should have all sr no correctly

	public boolean checkSrNo() throws Exception {
		getExcelfromProperties();
		
		ArrayList<String> expListCol = UtilityJXL.readAnyColumnData(filename, sheetname, 0);
		System.out.println("expListCol" + expListCol);

		ArrayList<String> actListNo = new ArrayList<String>();

		for (WebElement srNum : srNo) {
			actListNo.add(srNum.getText());
		}
		System.out.println("actListNo" + actListNo);

		if (actListNo.equals(expListCol)) {
			System.out.println("List is match..");
			return true;
		} else {
			System.out.println("List is not match..");
			return false;
		}
	}
}
