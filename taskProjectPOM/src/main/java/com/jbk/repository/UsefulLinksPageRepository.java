package com.jbk.repository;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsefulLinksPageRepository {
	
	@FindBy(xpath = "//table/tbody/tr/td")
	public List<WebElement> headerRow;

	@FindBy(xpath = "//table/tbody/tr[1]/th")
	public List<WebElement> header;

	@FindBy(xpath = "//span[text()='Go !']")
	public List<WebElement> goButton;

	@FindBy(xpath = "//tr/td[3]")
	public List<WebElement> go;

	@FindBy(xpath = "//tr/td[1]")
	public List<WebElement> srNo;
//usefulinks
}
