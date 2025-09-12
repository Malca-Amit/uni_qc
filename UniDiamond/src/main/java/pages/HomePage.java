package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utils.ConfigReader;
import utils.ElementUtils;
import utils.LogUtils;

public class HomePage {
	WebDriver driver;
	ElementUtils eu;

	By loggedInUser = By.cssSelector("[class*='user__info']");
	By priceListCredPopup = By.cssSelector("[onclick='priceListTryLater();']");
	By cart = By.xpath("//a[@href='/shopping-cart']");
	By logoutLink = By.xpath("//li/a[@href='/guest-user/logout']");
	By favorite = By.cssSelector("[title='Favorites']");
	By quickSearch = By.cssSelector("[name='certification_no_or_stone_id']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}

	public boolean isLoggedInUserDisplayed() {
		return eu.waitForElementVisible(loggedInUser).isDisplayed();
	}

	public void goToTheLeftNavigation(String newUrl){
		driver.get(ConfigReader.getProperty("url") + newUrl);
		eu.jsWaitForPageLoad();
	}

	public void closePriceListCredPopup() {
		try {
			eu.waitForElementVisible(priceListCredPopup);
			eu.waitForElementClickable(priceListCredPopup).click();
		} catch (Exception e) {
			LogUtils.debug("Popup is not visible :" + e.getMessage());
		}
	}
	
	public void clickOnCart() {
		eu.waitForElementClickable(cart).click();
		eu.jsWaitForPageLoad();
	}
	
	public void logoutTheFrontEndUser() {
		eu.jsWaitForPageLoad();
		eu.waitForElementClickable(loggedInUser).click();
		eu.waitForElementVisible(logoutLink);
		eu.jsClick(logoutLink);
	}
	
	public void clickOnFavorits() {
		eu.waitForElementClickable(favorite);
		eu.jsClick(favorite);
		eu.jsWaitForPageLoad();
	}
	
	public void searchStone(String... stoneIds) throws InterruptedException {
	    String searchQuery = String.join(" ", stoneIds).trim();
	    eu.waitForElementVisible(quickSearch);
	    eu.doSendKeys(quickSearch, searchQuery);
	    eu.getElement(quickSearch).sendKeys(Keys.ENTER);
	    eu.jsWaitForPageLoad();
	}
}
