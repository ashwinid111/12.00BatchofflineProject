package com.jbk.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.jbk.repository.LoginPageRepository;

public class LoginPage extends LoginPageRepository {

	public static WebDriver driver;

	public String geturl() {
		return driver.getCurrentUrl();
	}

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String typeUserName(String uName) {
		username.clear();
		username.sendKeys(uName);
		return uName;
	}

	public void typePassword(String pass) {
		password.clear();
		password.sendKeys(pass);

	}

	public void clickLoginButton() {
		loginButton.click();
	}

	public LoginPage clickLogoutButton() {
		logout.click();
		return new LoginPage(driver);
	}

	public RegistrationPage clickOnRegLink() {
		regLink.click();
		return new RegistrationPage(driver);
	}

	public LoginPage clickOnalreadymemberLink() {
		alreadymember.click();
		return new LoginPage(driver);
	}

	public boolean navigateToLoginFromReg(WebDriver driver) {
		clickOnRegLink();
		clickOnalreadymemberLink();

		if (driver.getTitle().equals("JavaByKiran | Log in")) {

			System.out.println("Navigated to login page from registration page.");
			return true;
		} else

			System.out.println("Should navigate to login page from registration page.");
		return false;
	}

	public boolean checkTitle(WebDriver driver) {
		if (driver.getTitle().equals("JavaByKiran | Log in")) {
			System.out.println("We are on Login Page!!");
			return true;
		} else {
			System.out.println("We should be on Login Page!!");
			return false;
		}
	}

	public boolean checkTitleOfDashBoard() {
		if (driver.getTitle().equals("JavaByKiran | Dashboard")) {
			System.out.println("Login successfully-----We are on Dashboard!!");
			return true;
		} else {
			System.out.println("We Should be on Dashboard!!");
			return false;
		}
	}

	public boolean checkHeading() {
		String actHeading = websiteName.getText();
		if (actHeading.equals("Java By Kiran")) {

			System.out.println("Heading is -Java By Kiran ");
			return true;
		} else {

			System.out.println("Heading is invalid. It should be- Java By Kiran");
			return false;
		}

	}

	public boolean checkLoginMsg() {
		String loginmsg = LoginMsg.getText();
		if (loginmsg.equals("Sign in to start your session")) {
			System.out.println("Login msg- Sign in to start your session is displayed correctly.");
			return true;
		}
		System.out.println("Loginmsg should display as- 'Sign in to start your session'");
		return false;
	}

	public boolean checkErrorMessage() {

		if (ErrorMsgForInvalidMail.getText().equals("Please enter email.")) {
			System.out.println("Invalid login Cred entered.");
			return true;
		} else {

			System.out.println("Error msg should be displayed as--'Please enter email.'");
			return false;
		}
	}

}
