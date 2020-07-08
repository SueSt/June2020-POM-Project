package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.Utils.ElementUtil;
import com.qa.hubspot.base.BasePage;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	
// By Locators(OR)
	By emailId = By.id("username");
	By pass = By.id("password");
	By loginButn = By.id("loginBtn");
	By SignUpLink= By.linkText("Sign up");
	
// Create a constructor of page class
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		element= new ElementUtil(driver);
	}
	
// Create page actions
	@Step("get login page title")// allure annotation to get the steps on the report
	public String getLoginPageTitle() {
		//return driver.getTitle();// once we use elementUtil will replace this code wd the one below
		return element.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	@Step("Verify SingUp Link on LoginPage")
	public boolean verifySignUpLink() {
		//return driver.findElement(SignUpLink).isDisplayed();
		return element.doIsDisplayed(SignUpLink);
	}
	
	@Step("Login to App with username: {0} and password: {1}")
	public HomePage doLogin(String username, String password) {
		/*driver.findElement(emailId).sendKeys(username);
		driver.findElement(pass).sendKeys(password);
		driver.findElement(loginButn).click();*/
		element.waitForElementToBeVisible(emailId, 10);
		element.doSendKeys(emailId, username);
		
		element.doSendKeys(pass, password);
		element.doClick(loginButn);
		
		return new HomePage(driver);
	}
	
	
}
