package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class MyFavoritesPage {

	WebDriver driver;
	ElementUtils eu;

	By searchInput = By.cssSelector("[id='multiple_stone_id']");
	By searchIcon = By.cssSelector("[class='js-submit searchBtnCls']");
	By favTableRow = By.cssSelector("[class='favTrCls']");
	By mediaButton = By.cssSelector("[class='circleIcon mediaBtn ']");
	By mediaDetailsPopup = By.cssSelector("[class='details-container']");
	By ppcOnmediaDetailsPopup = By.cssSelector("[class=' scatter-video-title row'] b");
	By addStoneIntoFavListInput = By.cssSelector("[id='search_stones']");
	By plusIconOnAddStoneInput = By.cssSelector("[class='add-stone-wrapper favourite-search-area'] path");
	
	public MyFavoritesPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}

	public void searchStone( String stoneId) {
		eu.waitForElementVisible(searchInput);
		eu.doSendKeys(searchInput, stoneId);
		eu.waitForElementClickable(searchIcon).click();
		eu.jsWaitForPageLoad();
	}
	
	public boolean isSingleSearchResultPresent(int expectedCount) {
		return eu.waitForExactNumberOfElements(favTableRow, expectedCount);
	}
	
	public void clickOnMediaButton() {
		eu.waitForElementVisible(mediaButton);
		eu.waitForElementClickable(mediaButton).click();
	}
	
	public String getPPCfromMediaPopup() {
		eu.waitForElementVisible(mediaDetailsPopup);
		eu.waitForElementVisible(ppcOnmediaDetailsPopup);
		return eu.doGetText(ppcOnmediaDetailsPopup);
	}
	
	public void addStoneIntoFavList(String stoneId) throws InterruptedException {
		eu.waitForElementVisible(addStoneIntoFavListInput);
		eu.doSendKeys(addStoneIntoFavListInput, stoneId);
		eu.waitForElementClickable(plusIconOnAddStoneInput).click();
		Thread.sleep(2000);
	}
}
