package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RewardPointPageUI;

public class RewardPointPageObject extends BasePage{
	private WebDriver driver;

	public RewardPointPageObject(WebDriver driver) {
		this.driver = driver;

	}

	public AddressPageObject openAddressPage() {
		waitForElementClickable(driver, RewardPointPageUI.ADDRESS_PAGE);
		clickToElement(driver, RewardPointPageUI.ADDRESS_PAGE);
		return PageGeneratorManager.getAddressPage(driver);
	}

	public MyProductReviewPageObject openMyProductReview() {
		// TODO Auto-generated method stub
		return null;
	}
}
