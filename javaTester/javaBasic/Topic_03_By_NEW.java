package javaBasic;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_By_NEW {

//	public void main(String[] args) {
//		// TODO Auto-generated method stub
//		String locatorValue = "xpath=//input[@id='name']";
//		System.out.println(getByLocatorNew(locatorValue));
//
//		locatorValue = "XPath=//input[@id='name']";
//		System.out.println(getByLocatorNew(locatorValue));
//
//		locatorValue = "XPATH=//input[@id='name']";
//		System.out.println(getByLocatorNew(locatorValue));
//
//	}

	@Test
	public void TC_01_Locator_Lower_Case() {
		String locatorValue = "xpath=//input[@id='name']";

		Assert.assertEquals(getByLocatorNew(locatorValue), By.xpath("//input[@id='name']"));

	}

	@Test
	public void TC_02_Locator_Camel_Case() {
		String locatorValue = "XPath=//input[@id='name']";
		Assert.assertEquals(getByLocatorNew(locatorValue), By.xpath("//input[@id='name']"));

	}

	public By getByLocatorNew(String locatorValue) {
		By by = null;
		if (locatorValue.startsWith("xpath=") || locatorValue.startsWith("XPath=")
				|| locatorValue.startsWith("XPATH=")) {

			by = By.xpath(locatorValue.substring(6));
		} else if (locatorValue.startsWith("css=") || locatorValue.startsWith("Css=")
				|| locatorValue.startsWith("CSS=")) {

			by = By.xpath(locatorValue.substring(4));

		} else if (locatorValue.startsWith("id=") || locatorValue.startsWith("Id=")
				|| locatorValue.startsWith("ID=")) {

			by = By.xpath(locatorValue.substring(3));

		}
		else if (locatorValue.startsWith("name=") || locatorValue.startsWith("Name=")
				|| locatorValue.startsWith("NAME=")) {

			by = By.xpath(locatorValue.substring(5));

		}
		else if (locatorValue.startsWith("class=") || locatorValue.startsWith("Class=")
				|| locatorValue.startsWith("CLASS=")) {

			by = By.xpath(locatorValue.substring(5));

		}
		return by;
	}

}
