package pageObjects.nopCommerce.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.BasePage;
import pageUIs.nopCommerce.user.LoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;
	private WebDriverWait explicitWait;

	public UserLoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}
public UserLoginPageObject(WebDriver driver, WebDriverWait explicitWait) {
		
		this.driver = driver;
		this.explicitWait = explicitWait;
	}

	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		// return new HomePageObject(driver);
		return PageGeneratorManager.getUserHomePage(driver);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void inputToEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXBOX);
		senkeyToElement(driver, LoginPageUI.EMAIL_TEXBOX, invalidEmail);
	}

	public String getErrorMessageUnsuccessfull() {
		waitForElementVisible(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXBOX);
		senkeyToElement(driver, LoginPageUI.PASSWORD_TEXBOX, password);
		
	}
	
	public UserHomePageObject loginAsUser(String emailAddress, String password) {
		
		inputToEmailTextbox(emailAddress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}
	

}
