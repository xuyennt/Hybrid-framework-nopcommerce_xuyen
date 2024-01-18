package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	private WebDriverWait explicitWait;

	public LoginPageObject(WebDriver driver) {
		
		this.driver = driver;
	}
public LoginPageObject(WebDriver driver, WebDriverWait explicitWait) {
		
		this.driver = driver;
		this.explicitWait = explicitWait;
	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		// return new HomePageObject(driver);
		return PageGeneratorManager.getHomePage(driver);
		
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
	

}
