package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;

public class OrderConfirmationPage {
	WebDriver driver;
	ElementUtils eu;

	By confirmationMessage = By.cssSelector("[class*=' confirm-area-mid']");

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}
	
	public String getConfimrationMessage() {
		eu.waitForElementVisible(confirmationMessage);
		return eu.doGetText(confirmationMessage);
	};
	
	 public String[] getOrderNumbers() {
	        WebElement confirmArea = driver.findElement(confirmationMessage);
	        String text = confirmArea.getText();

	        String[] orders = text.split("Your order number is:")[1].split(",");
	        return new String[] { orders[0].trim(), orders[1].trim() };
	    }

}
