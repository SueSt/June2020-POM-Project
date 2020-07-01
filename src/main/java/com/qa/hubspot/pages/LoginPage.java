package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage{
	WebDriver driver;
	//By Locators(OR)
	By emailId = By.id("username");
	By pass = By.id("password");
	By loginButn = By.id("loginBtn");
	By SignUpLink= By.linkText("Sign up");
	
	// Create a constructor of page class
	public LoginPage(WebDriver driver) {
		this.driver= driver;
	}
	
	// Create page actions
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifySignUpLink() {
		return driver.findElement(SignUpLink).isDisplayed();
	}
	
	public HomePage doLogin(String username, String password) {
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(pass).sendKeys(password);
		driver.findElement(loginButn).click();
		return new HomePage(driver);
	}
	
	
	
	
	
	
}
