package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.Utils.ElementUtil;
import com.qa.hubspot.base.BasePage;

public class ContactsPage extends BasePage {

	By header = By.xpath("//i18n-string[text()='Contacts']");
	By createContactPrimary = By.xpath("//span[text()='Create contact']");
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	By createContactSecondary = By.xpath("(//span[text()='Create contact'])[last()]");
	By contactsBackLink = By.xpath("(//*[text()='Contacts'])[position()=1]");

	By primaryContactLink = By.id("nav-primary-contacts-branch");
	By secondaryContactLink = By.id("nav-secondary-contacts");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		element = new ElementUtil(driver);
	}

	public String getContactsPageTitle() {
		return element.waitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 10);
	}

	public String getContactsHeader() {
		element.waitForElementToBeVisible(header, 10);
		return element.doGetText(header);
	}

	public void createContact(String email, String firstName, String lastName, String jobTitle) {

		element.clickWhenReady(createContactPrimary, 10);

		element.doSendKeys(this.email, email);
		element.doSendKeys(this.firstName, firstName);
		element.doSendKeys(this.lastName, lastName);

		element.doSendKeys(this.jobTitle, jobTitle);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

/*		element.waitForElementToBeVisible(createContactSecondary, 10);
		element.doClick(createContactSecondary);*/
		
		element.doActionsClick(createContactSecondary);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		element.doActionsClick(contactsBackLink);
	}

}
