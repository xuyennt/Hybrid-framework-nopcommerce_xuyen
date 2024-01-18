package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Apply_BasePage_III extends BasePage {
	WebDriver driver;
	String emailAddress;

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
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		waitForElementClickable(driver, "//a[@class = 'ico-register']");
		clickToElement(driver, "//a[@class = 'ico-register']");

		waitForElementClickable(driver, "//button[@id = 'register-button']");
		clickToElement(driver, "//button[@id = 'register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"),
				"First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");

		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		waitForElementClickable(driver, "//a[@class = 'ico-register']");
		clickToElement(driver, "//a[@class = 'ico-register']");

		senkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		senkeyToElement(driver, "//input[@id='LastName']", "FC");
		senkeyToElement(driver, "//input[@id='Email']", "123@3456#");
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		waitForElementClickable(driver, "//button[@id = 'register-button']");
		clickToElement(driver, "//button[@id = 'register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		waitForElementClickable(driver, "//a[@class = 'ico-register']");
		clickToElement(driver, "//a[@class = 'ico-register']");

		senkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		senkeyToElement(driver, "//input[@id='LastName']", "FC");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[@id = 'register-button']");
		clickToElement(driver, "//button[@id = 'register-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");

	}

	@Test
	public void TC_04_Register_Existing_Email() {
		waitForElementClickable(driver, "//a[@class = 'ico-register']");
		clickToElement(driver, "//a[@class = 'ico-register']");

		senkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		senkeyToElement(driver, "//input[@id='LastName']", "FC");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[@id = 'register-button']");
		clickToElement(driver, "//button[@id = 'register-button']");

		Assert.assertEquals(getElementText(driver, "//div[contains(@class, 'message-error')]//li"),
				"The specified email already exists");

	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		waitForElementClickable(driver, "//a[@class = 'ico-register']");
		clickToElement(driver, "//a[@class = 'ico-register']");

		senkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		senkeyToElement(driver, "//input[@id='LastName']", "FC");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "123");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		waitForElementClickable(driver, "//button[@id = 'register-button']");
		clickToElement(driver, "//button[@id = 'register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		waitForElementClickable(driver, "//a[@class = 'ico-register']");
		clickToElement(driver, "//a[@class = 'ico-register']");

		senkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		senkeyToElement(driver, "//input[@id='LastName']", "FC");
		senkeyToElement(driver, "//input[@id='Email']", emailAddress);
		senkeyToElement(driver, "//input[@id='Password']", "123456");
		senkeyToElement(driver, "//input[@id='ConfirmPassword']", "654321");
		waitForElementClickable(driver, "//button[@id = 'register-button']");
		clickToElement(driver, "//button[@id = 'register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
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
