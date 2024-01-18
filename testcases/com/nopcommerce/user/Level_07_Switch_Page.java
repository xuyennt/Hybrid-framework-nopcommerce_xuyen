package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.AddressPageObject;
import pageObjects.CustomerInforPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.RewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {
	WebDriver driver;
	String firstName, lastName, password, emailAddress;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInforPageObject customerInforPage;
	private AddressPageObject addressPage;
	private MyProductReviewPageObject myProductReviewPage;
	private RewardPointPageObject rewardPointPage;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";// "xuyennt@gmail.com";

//		System.out.println("Register Page - step 05: Click to Logout link");
//		registerPage.clickToLogoutLink();

	}

	@Test
	public void User_01_Register() {

		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

	}

	@Test
	public void User_03_Customer_Infor() {
		customerInforPage = homePage.clickToMyAccountLink();

		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());

	}

	@Test
	public void User_04_Switch_page() {
		//customer infor -> address
		addressPage = customerInforPage.openAddressPage(driver);
		// Address -> My product Review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		
		//My product Review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPoint(driver);
		
		//Reward PointPage-> Reward Point
		addressPage = rewardPointPage.openAddressPage(driver);
		
		//Address -> Reward Point
		
		rewardPointPage = addressPage.openRewardPoint(driver);
		// Reward Point -> My Product Review
		
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		customerInforPage = addressPage.openCustomerInforPage(driver);
		myProductReviewPage= customerInforPage.openMyProductReviewPage(driver);

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
