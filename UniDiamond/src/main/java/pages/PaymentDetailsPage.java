package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.ElementUtils;

public class PaymentDetailsPage {
	WebDriver driver;
	ElementUtils eu;

	By firstTermsConditions = By.cssSelector("[class='checkboxArea'] [for='term1']");
	By secondTermsConditions = By.cssSelector("[class='checkboxArea'] [for='term2']");
	By placeOrderBtn = By.cssSelector("[name*='_submit']");
	By termsConditionForMemo = By.cssSelector("[class='checkbox-term-conditions']");
	By returnShipmentAgreement = By.cssSelector("[class='return-shipment-agreement']");
	By bidTermsElements = By.cssSelector("[data-field-caption='Terms And Conditions']:nth-of-type(1)");
	By buyDetails = By.cssSelector("[class=' buy_details']");
	By shippingAddress = By.cssSelector("[data-field-caption='Shipping Address']");
	private By totalPrice(String stoneId) {
		return By.xpath("//td[text()='" + stoneId + "']/following-sibling::td[contains(@id, 'total_price_id_')]");
	}

	public PaymentDetailsPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}
	
	public double getTheTotalPrice(String stoneId) {
		eu.waitForElementVisible(totalPrice(stoneId));
		String totalPrice = eu.doGetText(totalPrice(stoneId));
		double total = Double.parseDouble(totalPrice.replace("$", "").replace(",", "").trim());
		return total;
	}

	public void clickOnTermsConditions() {
		eu.waitForElementVisible(firstTermsConditions);
		eu.jsScroll(secondTermsConditions);
		eu.waitForElementClickable(firstTermsConditions).click();
		eu.waitForElementClickable(secondTermsConditions).click();
	}

	public void clickOnPlaceOrderBtn() {
		eu.jsClick(placeOrderBtn);
		eu.waitForElementInVisible(placeOrderBtn);
	}

	public void selectMemoTermsAndCondition() {
		eu.waitForElementPresence(returnShipmentAgreement);
		eu.jsScroll(returnShipmentAgreement);
		eu.waitForElementClickable(returnShipmentAgreement).click();
		eu.waitForElementClickable(termsConditionForMemo).click();
	}

	public void selectBidTermsAndConditions() {
		eu.waitForElementPresence(bidTermsElements);
		eu.jsScroll(bidTermsElements);
		List<WebElement> termsElements = eu.getElements(bidTermsElements);
		for (WebElement element : termsElements) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);
		}
	}
	
	public String getBuyDetails() {
		return eu.waitForElementVisible(buyDetails).getText();
	}
	
	public void selectShippingAddress() {
		eu.waitForElementVisible(shippingAddress);
		Select drp = new Select(eu.getElement(shippingAddress));
		drp.selectByIndex(1);
	}
}
