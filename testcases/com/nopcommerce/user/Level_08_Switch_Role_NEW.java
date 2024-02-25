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
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.User.PageGeneratorManager;
import pageObjects.nopCommerce.User.UserCustomerInforPageObject;
import pageObjects.nopCommerce.User.UserHomePageObject;
import pageObjects.nopCommerce.User.UserLoginPageObject;
import pageObjects.nopCommerce.User.UserRegisterPageObject;

public class Level_08_Switch_Role_NEW extends BaseTest {
	WebDriver driver;
	String password, emailAddress, adminEmailAddress, adminPassword;
	UserHomePageObject userHomePage;
	UserRegisterPageObject registerPage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboard;
	private UserCustomerInforPageObject userCustomerInforPage;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		emailAddress = "Xuyennguyen@gmail.com";
		password = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";

	}

	@Test
	public void User_01_Register() {
		userLoginPage = userHomePage.openLoginPage();

		userHomePage = userLoginPage.loginAsUser(emailAddress, password);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		//home page -> customer infor
		userCustomerInforPage=userHomePage.openMyAccountPage();

	//	loginPage.clickToLoginButton();
		//customer info click logout -> home page
		userHomePage = userCustomerInforPage.clickToLogoutLinkAtUserPage(driver);
		
		//User Home Page -> Open Admin page
		userHomePage.openPageUrl(driver, GlobalConstants.PROTAL_ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		//Login as Admin role
		adminDashboard = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboard.isDashboardHeaderDisplayed());
		
		//Dashboard Page -> Click Logout -> Login Page(Admin)
		
		//adminLoginPage = adminDashboard.clickToLogoutLinkAtAdminPage(driver);

	}

	@Test
	public void User_02_Login() {
		//Login Page(Admin) -> Open User url -> Home Page(User)
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		//home page ->Login Page
		userLoginPage = userHomePage.openLoginPage();

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
