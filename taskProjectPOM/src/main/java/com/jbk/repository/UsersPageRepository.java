package com.jbk.repository;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsersPageRepository {
  
	@FindBy(xpath = "//button[text()='Add User']")
	public WebElement addUserButton;

	@FindBy(xpath = "//table[@class='table table-hover']")
	public WebElement table;

	@FindBy(xpath = "//tr/td[2]")
	public List<WebElement> usercount;

	@FindBy(xpath = "//th")
	public List<WebElement> headerElementCount;

	@FindBy(xpath = "//tr[1]")
	public List<WebElement> headerName;

	@FindBy(xpath = "//td[6]")
	public List<WebElement> genderList;

	@FindBy(xpath = "//tr/td[3]")
	public List<WebElement> emailList;

	@FindBy(xpath = "//table/tbody/tr[1]/th")
	public List<WebElement> headerList;

	@FindBy(xpath = "//tr[1]")
	public List<WebElement> header;

	@FindBy(id = "email")
	public static WebElement username;

	@FindBy(id = "password")
	public static WebElement password;

	@FindBy(xpath = "//button")
	public static WebElement loginButton;

	@FindBy(xpath = "//a[@href='users.html']")
	public static WebElement usersPageNavigate;

}
