package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactPages;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class HomePageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	ContactPages contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	//Every Test case should be independent
	//Login and quit each time
	
	@BeforeMethod
	public void SetUp() {
		initialization();
		contactsPage = new ContactPages();
		loginPage = new LoginPage(); //Creating object so that we can access the loginPage Methods
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password")); //prop is coming by using the super , it will call the constructor of base class		
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Cogmento CRM","Home Page title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		Assert.assertTrue(homePage.verifyCorrectUserName()); 
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		contactsPage = homePage.clickOnConatactsLink();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
