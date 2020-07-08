package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
/**
 * in this class we keep the before and after method that we use in every test/ 
 * so the other classes inherited from here
 * @author souad
 *
 */
public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	//create an Object to access the method in the pages
	public BasePage basePage;
	public LoginPage lgPage;
	public HomePage hmPage;
	public ContactsPage contactsPage;
	

	@BeforeTest
	public void setUp() throws InterruptedException {
		basePage= new BasePage();
		prop= basePage.init_prop();// once we call this method we can access all the properties
		driver=basePage.init_driver(prop);// initialize the driver
		lgPage= new LoginPage(driver);
		
	}
	
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}
	
	
	
}
