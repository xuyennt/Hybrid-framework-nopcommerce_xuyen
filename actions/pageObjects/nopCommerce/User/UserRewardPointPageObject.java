package pageObjects.nopCommerce.User;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.RewardPointPageUI;

public class UserRewardPointPageObject extends BasePage{
	private WebDriver driver;

	public UserRewardPointPageObject(WebDriver driver) {
		this.driver = driver;

	}

	public UserAddressPageObject openAddressPage() {
		waitForElementClickable(driver, RewardPointPageUI.ADDRESS_PAGE);
		clickToElement(driver, RewardPointPageUI.ADDRESS_PAGE);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserMyProductReviewPageObject openMyProductReview() {
		// TODO Auto-generated method stub
		return null;
	}
}
