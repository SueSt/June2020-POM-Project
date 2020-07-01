package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.PropertiesDocument;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author souad
 *
 */
public class BasePage {
	WebDriver driver;
	Properties prop;
	
	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * @param browserName
	 * @return driver
	 */
	

public WebDriver init_driver(Properties prop) {
	
	String browserName= prop.getProperty("browser");
	
	if (browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();	
	}
	
	else if(browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();	
	}
	
	else if(browserName.equalsIgnoreCase("ie")) {
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();	
	}
	
	else if(browserName.equalsIgnoreCase("safari")) {
		WebDriverManager.getInstance(SafariDriver.class).setup();
		driver = new SafariDriver();	
	}
	
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	driver.get(prop.getProperty("url"));
	return driver;
	
}

/**
 * this method is used to initialize the properties from config.properties file
 * @return
 */

public Properties init_prop() {
	
	//Create a method to read from the config file and return properties that is stored inside the config file
	prop = new Properties();
	try {
		FileInputStream ip= new FileInputStream(".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
	prop.load(ip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
	}
	return prop;
	
}

	
	
}