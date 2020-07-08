package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.Utils.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author souad
 *
 */
public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public ElementUtil element;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	//We used Synchronization whenever this thread is using a specific WebDriver other thread should not disturb it
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * @param browserName
	 * @return driver
	 */
	
public WebDriver init_driver(Properties prop) {
	
	String browserName= prop.getProperty("browser");
	
	if (browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();	//we commented this to use the threadcount driver
		 tlDriver.set(new ChromeDriver());
	}
	
	else if(browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		//driver = new FirefoxDriver();	
		tlDriver.set(new FirefoxDriver());	
	}
	
	else if(browserName.equalsIgnoreCase("ie")) {
		WebDriverManager.iedriver().setup();
		//driver = new InternetExplorerDriver();	
		tlDriver.set(new InternetExplorerDriver());
	}
	
	else if(browserName.equalsIgnoreCase("safari")) {
		WebDriverManager.getInstance(SafariDriver.class).setup();
		//driver = new SafariDriver();	
		tlDriver.set(new SafariDriver());
	}
	
	/*driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
	
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	//driver.get(prop.getProperty("url"));
	getDriver().get(prop.getProperty("url"));
	return getDriver();
	
}

/**
 * this method is used to initialize the properties from config.properties file on the basis of given env variable
 * @return
 */

public Properties init_prop() {
	
	//Create a method to read from the config file and return properties that is stored inside the config file
	prop = new Properties();
	String path= null;
	String env= null;

		try {
		env= System.getProperty("env");
		System.out.println("env value is --->"+env);
		if (env==null) {
			path= ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
		} else {
			switch (env) {
			case "qa":
				path= ".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
				break;
			case "dev":
				path= ".\\src\\main\\java\\com\\qa\\hubspot\\config\\dev.config.properties";
				break;
			case "stage":
				path= ".\\src\\main\\java\\com\\qa\\hubspot\\config\\stage.config.properties";
				break;
			default:
				System.out.println("Please pass the correct env valur ---->"+env);
				break;
			}
		}
		
		FileInputStream ip= new FileInputStream(path);
	prop.load(ip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
	}
	return prop;
	
}

/**
 * this method will take the screenshot
 * @return 
 */
	public String getScreenShot() {
		File src= ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir")+"/screenshots/"+ System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	return path;	
		

	}
	
}
