package com.qa.hubspot.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.Utils.ExcelUtil;
import com.qa.hubspot.base.BaseTest;

public class ContactsPageTest extends BaseTest {

	@BeforeClass
	public void ContactSetup() {
		hmPage = lgPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = hmPage.clickOnContacts();
	}
	

	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("Contacts Page Title is: " + title);
		Assert.assertEquals(Constants.CONTACTS_PAGE_TITLE, title);
	}
	

	@Test(priority = 2)
	public void verifyContactsHeaderTest() {
		String header = contactsPage.getContactsHeader();
		System.out.println("Contacts Page Header is: " + header);
		Assert.assertEquals(Constants.CONTACTS_PAGE_HEADER, header);
	}
	
	
	@DataProvider
	public Object[][] getContactsTestData() {
		Object data[][]= ExcelUtil.getTestData(Constants.CONTACT_SHEET_NAME);
		return data;
	}
	

	@Test(priority = 3, dataProvider="getContactsTestData")
	public void createContactTest(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.createContact(email, firstName, lastName,  jobTitle);
	}

}
