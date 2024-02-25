package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.User.UserAddressPageObject;
import pageObjects.nopCommerce.User.UserCustomerInforPageObject;
import pageObjects.nopCommerce.User.UserHomePageObject;
import pageObjects.nopCommerce.User.UserLoginPageObject;
import pageObjects.nopCommerce.User.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.User.PageGeneratorManager;
import pageObjects.nopCommerce.User.UserRegisterPageObject;
import pageObjects.nopCommerce.User.UserRewardPointPageObject;

public class Level_09_Dynamic_Locator extends BaseTest {
	WebDriver driver;
	String firstName, lastName, password, emailAddress;
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";// "xuyennt@gmail.com";

//		System.out.println("Register Page - step 05: Click to Logout link");
//		registerPage.clickToLogoutLink();

	}

	@Test
	public void User_01_Register_Login() {

		registerPage = homePage.openRegisterPage();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		customerInforPage = homePage.openMyAccountPage();

		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());

	}

	@Test
	public void User_02_Dynamic_page() {
		// customer infor -> address
		addressPage = customerInforPage.openAddressPage(driver);
		// Address -> My product Review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);

		// My product Review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPoint(driver);

		// Reward PointPage-> Reward Point
		addressPage = rewardPointPage.openAddressPage(driver);

		// Address -> Reward Point

		rewardPointPage = addressPage.openRewardPoint(driver);
		// Reward Point -> My Product Review

		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);

	}
	@Test
	public void User_03_Dynamic_page_01() {
		
		// My product Review -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Reward points");

		// Reward PointPage-> Address
		addressPage = (UserAddressPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "Addresses");

		// Address -> Reward Point

		rewardPointPage = (UserRewardPointPageObject) addressPage.openPageAtMyAccountByName(driver, "Reward points");
		// Reward Point -> My Product Review

		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "My product reviews");
        //My Product Review - > customer infor
		customerInforPage = (UserCustomerInforPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Customer info");
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
