package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.Utils.ElementUtil;
import com.qa.hubspot.base.BasePage;

public class HomePage extends BasePage {

	// By Locators(OR)
	By header = By.xpath("//i18n-string[text()='Dashboard Library']");
	By navatar = By.id("account-menu");
	By accountName = By.cssSelector("div.user-info-name");

	By primaryContactLink = By.id("nav-primary-contacts-branch");
	By secondaryContactLink = By.id("nav-secondary-contacts");

	// Create a constructor of page class
	public HomePage(WebDriver driver) {
		this.driver = driver;
		element = new ElementUtil(driver);
	}

	// Create page actions
	public String getHomePageTitle() {
		return element.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
	}

	public String getHomePageHeader() {
		if (element.doIsDisplayed(header)) {
			return element.doGetText(header);
		}
		return null;
	}

	public String getLoggedInUser() {
		element.clickWhenReady(navatar, 10);
		if (element.doIsDisplayed(accountName)) {
			return element.doGetText(accountName);
		}
		return null;
	}

	public ContactsPage clickOnContacts() {
		/*element.waitForElementToBeVisible(primaryContactLink, 10);
		element.doClick(primaryContactLink);*/
		element.clickWhenReady(primaryContactLink, 10);
		
		element.waitForElementToBeVisible(secondaryContactLink, 10);
		element.doClick(secondaryContactLink);
		return new ContactsPage(driver);
	}

	
	
	
}
