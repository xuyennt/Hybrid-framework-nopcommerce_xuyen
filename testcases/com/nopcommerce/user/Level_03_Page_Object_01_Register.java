package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.User.UserHomePageObject;
import pageObjects.nopCommerce.User.UserRegisterPageObject;

public class Level_03_Page_Object_01_Register extends BasePage {
	WebDriver driver;
	String emailAddress;
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		homePage = new UserHomePageObject(driver);
		registerPage = new UserRegisterPageObject(driver);

	}

	@Test
	public void Register_01_Empty_Data() {

		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();

		System.out.print("Home Page - Step 02: Click to Register link");
		registerPage.clickToRegisterButton();

		System.out.print("Home Page - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {

		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();

		System.out.print("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox("123@3456#");
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		System.out.print("Home Page - Step 03: Click to Register link");
		registerPage.clickToRegisterButton();

		System.out.print("Home Page - Step 04: Verify error message displayed");

		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Register_03_Success() {

		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();

		System.out.print("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		System.out.print("Home Page - Step 03: Click to Register link");
		registerPage.clickToRegisterButton();

		System.out.print("Home Page - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//		System.out.println("Register Page - step 05: Click to Logout link");
//		registerPage.clickToLogoutLink();

	}

	@Test
	public void Register_04_Existing_Email() {
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();

		System.out.print("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.print("Register Page - Step 04: Verify success message displayed");

		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();

		System.out.print("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.print("Register Page - Step 04: Verify success message displayed");

		Assert.assertEquals(registerPage.getErrorMessageAtPassordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.print("Home Page - Step 01: Click to Register link");
		homePage.openRegisterPage();

		System.out.print("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox("Automation");
		registerPage.inputToLastnameTextbox("FC");
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("654321");

		System.out.print("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");

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
