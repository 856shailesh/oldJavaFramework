package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListner;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\shailesh.kumar2\\eclipse-workspace\\JavaFramework\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver); //Create a object of EventListnerHandler to register it with EventFiringWebDriver
		eventListner = new WebEventListener();
		e_driver.register(eventListner);
		driver = e_driver;
			
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	
		driver.get(prop.getProperty("url"));
	
	}
}
