package com.qa.hubspot.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;



public class LoginPageTest {
	WebDriver driver;
	//create an Object to access the method in the pages
	BasePage basePage;
	LoginPage lgPage;
	Properties prop;

	@BeforeTest
	public void setUp() {
		basePage= new BasePage();
		prop= basePage.init_prop();// once we call this method we can access all the properties
		driver=basePage.init_driver(prop);// initialize the driver
		lgPage= new LoginPage(driver);
	}
	
	@Test(priority=2)
	public void verifyLoginPageTitleTest() {
		String title =lgPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login page title is not matched..." );	
	}
	
	@Test(priority=1)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(lgPage.verifySignUpLink(),"sign up link is not displayed...");
	}
	
	@Test(priority=3)
	public void LoginTest() {
		lgPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}
	

}
