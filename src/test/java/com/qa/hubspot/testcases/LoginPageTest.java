package com.qa.hubspot.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.Utils.Constants;
import com.qa.hubspot.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

//@Listeners(ExtentReportListener.class)// we add this just to debug if we want to see the report only of this test case
@Epic("Epic- 101: Design Login page with different features...")
@Story("US- 102: Design basic login page with sign up title and login form...")
public class LoginPageTest extends BaseTest{
	
	@Test(priority=2)
	@Description("Verify Login Page Title Test...")// allure annotation to give the description on the report
	@Severity(SeverityLevel.NORMAL)//allure annotation to say the severity level
	public void verifyLoginPageTitleTest() {
		String title =lgPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login page title is not matched..." );	
	}
	
	@Test(priority=1)
	@Description("Verify Login Page SignUp Link Test...")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(lgPage.verifySignUpLink(),"sign up link is not displayed...");
	}
	
	@Test(priority=3)
	@Description("Verify Login Page Test...")
	@Severity(SeverityLevel.BLOCKER)
	public void LoginTest() {
		lgPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	

}
