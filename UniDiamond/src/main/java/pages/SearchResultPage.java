package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;
import utils.LogUtils;

public class SearchResultPage {
	WebDriver driver;
	ElementUtils eu;

	By listView = By.cssSelector("[for='list-view']");
	By stoneIdLocator = By.cssSelector("[id*='stoneDiv_'] td:nth-of-type(5)");
	By addToCartButtonLocator = By.cssSelector("[title='Add to cart']");
	By addToCartSuccesPopupCloseBtn = By.cssSelector("[id='facebox'] [class='close']");
	By removeCartButtonLocator = By.cssSelector("[onclick*='removeFromCart']");
	By confirmRemoveCart = By.cssSelector("[id='confirmYes']");
	By stoneCount = By.cssSelector("[class='showing-results-target scatter_stone_count']");

	private By finalPPC(String stoneId) {
		return By.xpath("//td[text()='" + stoneId + "']/following-sibling::td[@class='finalColumn'][1]");
	}

	private By totalPrice(String stoneId) {
		return By.xpath("//td[text()='" + stoneId + "']/following-sibling::td[@class='finalColumn'][2]");
	}

	private By media(String stoneId) {
		return By.xpath("//td[text()='" + stoneId + "']/preceding-sibling::td//a[@title='View Media']");
	}

	private By addToFavorite(String stoneId) {
		return By.xpath("//td[text()='" + stoneId + "']/preceding-sibling::td//a[@title='Add to Favorite']");
	}

	By mediaDetailsPopup = By.cssSelector("[class='details-container']");
	By ppcOnmediaDetailsPopup = By.cssSelector("[class='scatter-video-title row'] b");
	By closePopupButton = By.cssSelector("a[class='close']");
	By successMessage = By.cssSelector("[class='alert alert--positioned alert--success-1 alert--info']");
	By mediaIcon = By.cssSelector("[class='circleBtn no-click-cls']");
	By rowLocator = By.cssSelector("tr[id^='stoneDiv_']"); 
	By tableHeader = By.cssSelector("[class='tablesorter-headerRow']");  
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}

	public void selectListView() throws InterruptedException {
		/*
		 * eu.jsWaitForPageLoad(); Thread.sleep(3000);
		 */
		eu.waitForElementClickable(listView);
		eu.waitForElementClickable(listView).click();
		eu.jsWaitForPageLoad();
		/* Thread.sleep(2000); */
	}

	public String getStoneId() {
	    eu.jsWaitForPageLoad();
	    eu.waitForElementVisible(stoneIdLocator);

	    List<WebElement> stoneIdElements = driver.findElements(stoneIdLocator);
	    List<WebElement> addToCartButtons = driver.findElements(addToCartButtonLocator);

	    int count = Math.min(stoneIdElements.size(), addToCartButtons.size());

	    for (int i = 0; i < count; i++) {
	        try {
	            if (addToCartButtons.get(i).isDisplayed() && addToCartButtons.get(i).isEnabled()) {
	                return stoneIdElements.get(i).getText().trim();
	            }
	        } catch (Exception ignored) {
	            // element may go stale, retry next iteration
	        }
	    }
	    return null;
	}


	public void clickOnAddToCartBtn(String stoneId) {
		eu.jsWaitForPageLoad();
		eu.waitForElementVisible(stoneIdLocator);
		List<WebElement> stoneIdElements = driver.findElements(stoneIdLocator);
		List<WebElement> addToCartButtons = driver.findElements(addToCartButtonLocator);

		for (int i = 0; i < stoneIdElements.size(); i++) {
			String currentStoneId = stoneIdElements.get(i).getText().trim();
			if (currentStoneId.equals(stoneId)) {
				WebElement button = addToCartButtons.get(i);
				if (button.isDisplayed() && button.isEnabled()) {
					button.click();
					break;
				}
			}
		}
	}

	public void closeTheAddToCartSuccessPopup() {
		eu.waitForElementVisible(addToCartSuccesPopupCloseBtn);
		eu.waitForElementClickable(addToCartSuccesPopupCloseBtn);
		eu.jsClick(addToCartSuccesPopupCloseBtn);
	}

	public double getTheFinalPpcPrice(String stoneId){
		eu.waitForElementVisible(finalPPC(stoneId));
		String finalPpc = eu.doGetText(finalPPC(stoneId));
		double PpcPrice = Double.parseDouble(finalPpc.replace("$", "").replace(",", "").trim());
		return PpcPrice;
	}

	public double getTheTotalPrice(String stoneId) {
		eu.waitForElementVisible(totalPrice(stoneId));
		String totalPrice = eu.doGetText(totalPrice(stoneId));
		double total = Double.parseDouble(totalPrice.replace("$", "").replace(",", "").trim());
		return total;
	}

	public static double getMultipliedPrice(String totalPrice) {
		double price = Double.parseDouble(totalPrice.replace("$", "").replace(",", "").trim());
		return price * 2;
	}
	
	public String getStoneIdForFirstEnabledMediaIcon() throws InterruptedException {
		eu.jsWaitForPageLoad();
		eu.waitForElementVisible(stoneIdLocator);
		Thread.sleep(5000);
	    List<WebElement> rows = driver.findElements(rowLocator);

	    for (WebElement row : rows) {
	        List<WebElement> icons = row.findElements(mediaIcon);

	        if (!icons.isEmpty()) {  // means enabled icon exists in this row
	            WebElement stoneIdElement = row.findElement(stoneIdLocator);
	            return stoneIdElement.getText().trim();
	        }
	    }

	    // if nothing found
	    return null;
	}

	public void clickOnMediaIcon(String stoneId) {
		eu.waitForElementVisible(media(stoneId));
		eu.waitForElementClickable(media(stoneId)).click();
	}

	public String getPPCfromMediaPopup() {
		eu.waitForElementVisible(mediaDetailsPopup);
		return eu.doGetText(ppcOnmediaDetailsPopup);
	}

	public void clickOnAddToFavIcon(String stoneId) {
		eu.waitForElementVisible(addToFavorite(stoneId));
		eu.waitForElementClickable(addToFavorite(stoneId)).click();
	}

	public boolean isSuccessMessageDisplayed() {
		return eu.waitForElementVisible(successMessage).isDisplayed();
	}

	public void closeThePopup() {
		eu.waitForElementVisible(closePopupButton).click();
		eu.waitForElementInVisible(mediaDetailsPopup);
	}

	public String getStoneCount() {
		eu.waitForElementVisible(stoneCount);
		return eu.doGetText(stoneCount);
	}
	
	public boolean isSearchResultTableDisplayed() {
		return eu.waitForElementVisible(tableHeader).isDisplayed();
	}
}
