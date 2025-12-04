package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class StoneDetailsPage {
	WebDriver driver;
	ElementUtils eu;

	By memo = By.cssSelector("[onclick*='memoAction']");
	By popup = By.cssSelector("[class='popup ']");
	By returnShipmentAgreement = By.cssSelector("[class='return-shipment-agreement']");
	By termCondition = By.cssSelector("[class='checkbox-term-conditions']");
	By placeOrder = By.cssSelector("[onclick*='placeMemoOrder']");
	By memoPlacedSuccessMessage = By.cssSelector("[class='-line-title']");
	By stoneDetailsSection = By.cssSelector("[class='stone-details-section ']");

	public StoneDetailsPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
		eu.jsWaitForPageLoad();
	}
	
	public void clickOnMemo() throws InterruptedException {
		//Thread.sleep(5000);
		eu.waitForElementVisible(memo);
		eu.waitForElementClickable(memo).click();
		eu.jsWaitForPageLoad();
	}
	
	public boolean isPopupDisplayed() {
		return eu.waitForElementVisible(popup).isDisplayed();
	}
	
	public void selectTermsCondition() {
		eu.jsScroll(returnShipmentAgreement);
		eu.waitForElementClickable(returnShipmentAgreement).click();
		eu.waitForElementClickable(termCondition).click(); 
	}
	
	public void clickOnPlaceOrder() {
		eu.waitForElementClickable(placeOrder).click();
	}
	
	public String getMemoPlacedSuccessMessage() {
		eu.waitForElementVisible(memoPlacedSuccessMessage);
		return eu.doGetText(memoPlacedSuccessMessage);
	}
	
	public boolean isStoneDetailsSectionDisplayed() {
		return eu.waitForElementVisible(stoneDetailsSection).isDisplayed();
	}
}
