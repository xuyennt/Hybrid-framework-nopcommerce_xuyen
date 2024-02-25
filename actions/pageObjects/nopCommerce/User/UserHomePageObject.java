package pageObjects.nopCommerce.User;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject openRegisterPage() {
		waitForElementClickable(driver, HomePageUI.RESGISTER_LINK);
		clickToElement(driver, HomePageUI.RESGISTER_LINK);
		
		//return new RegisterPageObject(driver);
		return PageGeneratorManager.getUserRegisterPage(driver);

	}

	public UserLoginPageObject openLoginPage() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		//return new LoginPageObject(driver);
		return PageGeneratorManager.getUserLoginPage(driver);
		

	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public UserCustomerInforPageObject openMyAccountPage() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserMyAccountPage(driver);
		
	}

}
