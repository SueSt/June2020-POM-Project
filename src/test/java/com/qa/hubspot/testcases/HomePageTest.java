package com.qa.hubspot.testcases;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

public class HomePageTest {
	WebDriver driver;
	Properties prop;
	
	BasePage basePage;//create an Object to access the init_driver method and the properties method
	LoginPage lgPage;
	HomePage hmPage;
	
	
	@BeforeTest
	public void setUp() {
		basePage= new BasePage();
		prop= basePage.init_prop();
		driver=basePage.init_driver(prop);
		lgPage= new LoginPage(driver);
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
	
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}
}
