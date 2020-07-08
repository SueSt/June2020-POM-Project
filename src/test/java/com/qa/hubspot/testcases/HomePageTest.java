package com.qa.hubspot.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.base.BaseTest;

public class HomePageTest extends BaseTest{


	@BeforeClass
	public void HomeSetUp() {
		hmPage=	lgPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=3)
	public void verifyHomePageTitleTest() {
		String title= hmPage.getHomePageTitle();
		System.out.println("HomePage Title is: "+title);
		Assert.assertEquals(title,Constants.HOME_PAGE_TITLE , "HomePage Title is not matched..." );
	}
	
	
	@Test(priority=1)
	public void verifyHomePageHeaderTest() {
		String header= hmPage.getHomePageHeader();
		System.out.println("HomePage header is: "+header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER, "HomePage header is not matched...");
	}
	
	@Test(priority=2)
	public void verifyLoggedInUserTest() {
		String loggedInUser= hmPage.getLoggedInUser();
		System.out.println("loggedInUser is:" +loggedInUser);
		Assert.assertEquals(loggedInUser, prop.getProperty("accountName"), "loggedInUser is not mached...");
	}
	
	
}
