package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.User.UserHomePageObject;
import pageObjects.nopCommerce.User.UserLoginPageObject;
import pageObjects.nopCommerce.User.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager_II extends BaseTest {
	WebDriver driver;
	String firstName, lastName, invalidEmail, notFoundEmail, validEmail, password, existingEmail;
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = new UserHomePageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		existingEmail = "afc" + generateFakeNumber() + "@mail.vn";// "xuyennt@gmail.com";
		invalidEmail = "afc@afc.com@.vn";
		notFoundEmail = "afc" + generateFakeNumber() + "@mail.com";

		System.out.print("Home Page - Step 01: Click to Register link");
		registerPage = homePage.openRegisterPage();

		System.out.print("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.print("Home Page - Step 03: Click to Register link");
		registerPage.clickToRegisterButton();

		System.out.print("Home Page - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//		System.out.println("Register Page - step 05: Click to Logout link");
//		registerPage.clickToLogoutLink();

	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.openLoginPage();
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTextbox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Login_03_Email_Not_Found() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTextbox(notFoundEmail);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		loginPage = homePage.openLoginPage();
	
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("654321");

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Email_Password() {
		loginPage = homePage.openLoginPage();
	
		loginPage.inputToEmailTextbox(existingEmail);
		loginPage.inputToPasswordTextbox("123456");

		loginPage.clickToLoginButton();

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

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
