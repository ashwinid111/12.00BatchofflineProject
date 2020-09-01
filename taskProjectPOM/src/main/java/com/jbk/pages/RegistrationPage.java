package com.jbk.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	WebDriver driver=null;
	public RegistrationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

}
