package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class HomePage extends BasePage {
	WebDriver driver;

	By header = By.xpath("//i18n-string[text()='Dashboard Library']");
	By navavatar= By.id("account-menu");
	By accountName = By.cssSelector("div.user-info-name");

	By primaryContactLink = By.id("nav-primary-contacts-branch");
	By secondaryContactLink = By.id("nav-secondary-contacts");

	// Create Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getHomePageTitle() {
		return driver.getTitle();	
	}
	
	public String getHomePageHeader() {
		if (driver.findElement(header).isDisplayed()){
			return driver.findElement(header).getText();
		}	
		return null;
	}
	
	public String getLoggedInUser() {
		driver.findElement(navavatar).click();
		if (driver.findElement(accountName).isDisplayed()) {
			return driver.findElement(accountName).getText();
		}
		return null;
	}
		
	
	
	
	}

