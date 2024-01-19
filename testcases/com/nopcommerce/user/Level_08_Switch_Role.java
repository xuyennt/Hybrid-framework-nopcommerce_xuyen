package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;

public class Level_08_Switch_Role extends BaseTest {
	WebDriver driver;
	String firstName, lastName, password, emailAddress;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	LoginPageObject userHomePage;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);
		password = "123456";
		emailAddress = "Xuyennguyen@gmail.com";
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

	}

	@Test
	public void Role_01_User() {
		loginPage = homePage.clickToLoginLink();
		
		//login as User role
		homePage = loginPage.loginAsUser(emailAddress,password);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}

	@Test
	public void Role_02_Admin() {
		
		homePage.openPageUrl(driver, GlobalConstants.PROTAL_ADMIN_PAGE_URL);
	}

	@Test
	public void User_05_Switch_Role() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
