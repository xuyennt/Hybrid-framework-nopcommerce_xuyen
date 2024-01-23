package javaBasic;

import org.openqa.selenium.By;

public class Topic_03_By {

	public static void main(String[] args) {
		//Đầu vào là xpath/css/id/name/class/tagname/linktext
		// dau ra laf nos se lay dung loai by tuong ung
		
		String locatorValue = "xpath = //input[@id='name']";
	//	System.out.println(locatorValue.substring(6));
		System.out.println(getByLocator(locatorValue));
		

	}
	public static By getByLocator(String locatorValue) {
		By by = null;
		
		if(locatorValue.startsWith("xpath = ")) {
			by = By.xpath(locatorValue.substring(6));
			
		}else {
			
		}
		
		return by;
	}

}
