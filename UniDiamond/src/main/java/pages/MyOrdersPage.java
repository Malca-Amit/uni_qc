package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;

public class MyOrdersPage {
	WebDriver driver;
	ElementUtils eu;

	By searchInput = By.cssSelector("[id='search']");
	By searchIcon = By.cssSelector("[onclick='searchOrders()']");
	By tableRow = By.cssSelector("[class='row-selector  ']");
	By returnBtn = By.cssSelector("[onclick*='returnRequest']");
	By returnPopup = By.cssSelector("[class='stoneReturn_popup']");
	By returnSelectedItemBtn = By.cssSelector("[id='btn_return']");
	By innerReturnPopup = By.cssSelector("[class='inner-return-popup']");
	By buyButton = By.cssSelector("[onclick*='buyOrderForm']");
	By buyStonePopup = By.cssSelector("[id='frm_fat_id_buyStoneFrm']");
	By termsAndCondition = By.cssSelector("[name='terms_conditions']");
	By buyThisStoneBtn = By.cssSelector("input[type='submit']");

	private By returnReason(String reason) {
		return By.xpath("//*[contains(text(),'" + reason + "')]");
	}

	By returnStonesBtn = By.cssSelector("[value='Return Stones']");
	By successMessage = By.cssSelector("[class*='alert--success']");

	public MyOrdersPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}

	public void searchStoneId(String stoneId) {
		eu.waitForAllElementPresence(tableRow);
		eu.waitForElementVisible(searchInput);
		eu.doSendKeys(searchInput, stoneId);
		eu.waitForElementClickable(searchIcon).click();
	}

	public void clickOnReturnBtn() {
		eu.waitForElementClickable(returnBtn).click();
	}

	public boolean isReturnPopupDisplayed() {
		return eu.waitForElementVisible(returnPopup).isDisplayed();
	}

	public void clickOnReturnSelectedItemBtn() {
		eu.waitForElementClickable(returnSelectedItemBtn).click();
	}

	public boolean isInnerReturnPopupDisplayed() {
		return eu.waitForElementVisible(innerReturnPopup).isDisplayed();
	}

	public void selectReason(String reason) {
		eu.waitForElementVisible(returnReason(reason));
		eu.jsClick(returnReason(reason));
	}

	public void clickOnReturnStonesBtn() {
		eu.waitForElementClickable(returnStonesBtn).click();
	}

	public boolean isSuccessMessageDisplayed() {
		return eu.waitForElementVisible(successMessage).isDisplayed();
	}
	
	public boolean isSingleSearchResultPresent() {
		return eu.waitForExactNumberOfElements(tableRow, 1);
	}
	
	public void clickOnBuyBtn() {
		eu.waitForElementClickable(buyButton).click();
	}
	
	public boolean isBuyStonePopupDisplayed() {
		return eu.waitForElementVisible(buyStonePopup).isDisplayed();
	}
	
	public void checkTermsAndCondition() {
		eu.jsClick(termsAndCondition);
	}
	
	public void clickOnBuyThiStoneBtn() {
		eu.jsScroll(buyThisStoneBtn);
		eu.waitForElementClickable(buyThisStoneBtn).click();
	}
}
