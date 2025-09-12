package pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ElementUtils;
import utils.LogUtils;

public class ShoppingCart {
	WebDriver driver;
	ElementUtils eu;

	By search = By.cssSelector("input[placeholder='Search']");
	By searchBtn = By.cssSelector("[class='js-submit searchBtnCls']");
	By searchResults = By.cssSelector("[class='CartTrCls  singleStoneRow  ']");
	By checkbox = By.cssSelector("[class='checkboxArea']");
	By buyNowBtn = By.cssSelector("[id='btnBuy']");
	By popupCloseBtn = By.cssSelector("[class='popup '] [class='close']");
	By memoBtn = By.cssSelector("[id='btnMemo']");
	By bidBtn = By.cssSelector("[class='btn BidBtn bidBtnAll']");
	By confirmYesBtn = By.cssSelector("[id='confirmYes']");
	By bidCalculator = By.cssSelector("[id='contact']");
	By priceSelector = By.cssSelector(".inc_dec_box input:nth-of-type(1)");
	By submitBtn = By.cssSelector("[name='btn_submit']");
	By iWillPass = By.cssSelector("[value*='pass']");
	By finalPPC = By.cssSelector("[id*='buy_per_ct_id']");
	By totalPrice = By.cssSelector("[id*='total_price']");
	By myPpcPrice = By.cssSelector("[name*='stone_ppc']");
	By ppcOfferInput = By.cssSelector("[name='bid_carat']");
	By applyToAllStonesBtn = By.cssSelector("[class='btn BidBtn bidBtnAll calBtnBid']");
	By termsCondition = By.cssSelector("[name='terms_conditions']");
	By addStoneToCart = By.cssSelector("[placeholder='Enter UNI ID to add stones to cart']");
	By addStoneButton = By.cssSelector("[onsubmit*='getStonesToCart()'] button");
	By addQC = By.cssSelector("[title='Add to QC  ']:nth-of-type(1)");
	By qcRequestPopup = By.cssSelector("[class='section qc-request']");
	By qcToggle = By.cssSelector("[id='cartIsQc']");
	By qcInstruction = By.cssSelector("[id='qcrequest_other_text']");
	By qcRequestSavedMessage = By.xpath("//p[contains(text(),'QC Request Saved')]");
	By viewQC = By.cssSelector("[class*=' active already_qc']");
	By qcTooltip = By.cssSelector("[class='ui-tooltip-content']");
	By mediaButton = By.cssSelector("[onclick*='getMediaImage']");
	By mediaDetailsPopup = By.cssSelector("[class='details-container']");
	By ppcOnmediaDetailsPopup = By.cssSelector("[class=' scatter-video-title row'] b");
	By unDiscount = By.cssSelector("[class='uni-disc-column']");
	By singleBid = By.cssSelector("[onclick*='singleBid']");
	By alertDanger = By.cssSelector("[class='alert alert--positioned alert--success-1 alert--danger']");
	
	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}

	public void searchStone(String... stoneIds) throws InterruptedException {
	    String searchQuery = String.join(" ", stoneIds).trim();

	    if (stoneIds.length == 1 && driver.findElements(searchResults).size() == 1) {
	        LogUtils.debug("There is only one single record");
	        return;
	    }

	    eu.waitForAllElementPresence(searchResults);
	    eu.waitForElementVisible(search);
	    eu.doSendKeys(search, searchQuery);
	    eu.jsClick(searchBtn);
	    eu.jsWaitForPageLoad();
	}

	public boolean isSingleSearchResultPresent(int expectedCount) {
		return eu.waitForExactNumberOfElements(searchResults, expectedCount);
	}

	public void selectCheckbox() {
		eu.waitForElementVisible(checkbox);
		eu.waitForElementClickable(checkbox).click();
	}

	public void clickOnBuyNowBtn() {
		eu.doClick(buyNowBtn);
	}

	public void closeInfoPopup() {
		try {
			try {
				eu.waitForElementPresence(iWillPass, 2);
				eu.doClick(iWillPass);
				return;
			} catch (Exception ex) {
			}
			eu.waitForElementVisible(popupCloseBtn, 2);
			eu.doClick(popupCloseBtn);
		} catch (Exception e) {
			LogUtils.debug("Popup elements are not visible or clickable: " + e.getMessage());
		}
	}

	public void clickOnMemoBtn() {
		eu.doClick(memoBtn);
	}

	public void clickOnBidBtn() {
		eu.waitForElementClickable(bidBtn).click();
	}

	public void clickYesOnSimilarStoneConfirmationPopup() throws InterruptedException {
		Thread.sleep(2000);
		if (eu.isElementPresent(confirmYesBtn)) {
			eu.jsClick(confirmYesBtn);
		} else {
			LogUtils.debug("Similar stone confirmation popup is not visible");
		}
	}

	public boolean isBidCalculatorDisplayed() {
		return eu.waitForElementVisible(bidCalculator, 5).isDisplayed();
	}

	public void applyTenPercentDiscount() throws InterruptedException {
		try {
			eu.waitForElementVisible(priceSelector);
			WebElement priceInput = eu.getElement(priceSelector);
			String priceStr = priceInput.getAttribute("value").replaceAll("[^0-9.]", "");
			double actualPrice = Double.parseDouble(priceStr);

			double discountedPrice = actualPrice - (actualPrice * 0.10);
			double finalDiscountedPrice = Math.round(discountedPrice * 100.0) / 100.0;
			priceInput.click();
			priceInput.sendKeys(Keys.CONTROL + "a"); // Select all
			priceInput.sendKeys(Keys.DELETE);
			priceInput.sendKeys(String.valueOf(finalDiscountedPrice));
			priceInput.sendKeys(Keys.ENTER);

		} catch (Exception e) {
			System.out.println("Error applying 10% discount: " + e.getMessage());
		}
	}
	
	public double getTheFinalPpcPrice() {
		eu.waitForElementPresence(finalPPC);
		String finalPpc = eu.doGetText(finalPPC);
		double PpcPrice = Double.parseDouble(finalPpc.replace("$", "").replace(",", "").trim());
		return PpcPrice;
	}
	
	public double getTheTotalPrice() {
		eu.waitForElementPresence(totalPrice);
		String totalStonePrice = eu.doGetText(totalPrice);
		double total = Double.parseDouble(totalStonePrice.replace("$", "").replace(",", "").trim());
		return total;
	}
	
	public void applyTenPercentDiscountForAllStones() {
	    try {
	        eu.waitForAllElementPresence(myPpcPrice);
	        List<WebElement> ppcInputs = driver.findElements(myPpcPrice);

	        for (WebElement ppcInput : ppcInputs) {
	            String priceStr = ppcInput.getAttribute("value").replaceAll("[^0-9.]", "");
	            double actualPrice = Double.parseDouble(priceStr);

	            double discountedPrice = actualPrice - (actualPrice * 0.09);
	            double finalDiscountedPrice = Math.round(discountedPrice * 100.0) / 100.0;

	            ppcInput.clear();
	            ppcInput.sendKeys(String.valueOf(finalDiscountedPrice));
	        }

	        eu.jsClick(termsCondition);
	        eu.jsClick(submitBtn);

	    } catch (Exception e) {
	        System.out.println("Error applying discount to all stones: " + e.getMessage());
	    }
	}
	
	public void addStoneToCart(String... stoneIds) throws InterruptedException {
		String searchQuery = String.join(" ", stoneIds).trim();
		eu.waitForElementVisible(addStoneToCart);
	    eu.doSendKeys(addStoneToCart, searchQuery);
	    eu.jsClick(addStoneButton);
	    Thread.sleep(3000);
	}
	
	public void addToQC() {
	    try {
	        // Try to check if viewQC is displayed – if it is, assume QC is already added
	        if (eu.getElement(viewQC).isDisplayed()) {
	            System.out.println("QC already added. Skipping add to QC.");
	            return;
	        }
	    } catch (Exception e) {
	        // Element not found or not displayed – proceed to add QC
	    }

	    // Proceed to add to QC
	    eu.waitForElementClickable(addQC).click();
	    eu.waitForElementVisible(qcRequestPopup);
	    eu.jsClick(qcToggle);
	    eu.doSendKeys(qcInstruction, "Testing");
	    eu.jsClick(submitBtn);
	}
	
	public boolean isQcRequestSavedSuccesMessageDisplayed() {
		return eu.waitForElementVisible(qcRequestSavedMessage).isDisplayed();
	}
	
	public String getQcTooltipComment() {
		eu.waitForElementVisible(viewQC);
		eu.mouseHoverToElement(viewQC);
	    return eu.doGetText(qcTooltip);	
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
	
	public boolean isUniDiscountDisplayed() {
	    try {
	        return eu.waitForElementVisible(unDiscount, 3).isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public void clickOnSingleBid() {
		eu.waitForElementClickable(singleBid).click();
	}
	
	public boolean isAlertMessageDisplayed() {
		return eu.waitForElementVisible(alertDanger).isDisplayed();
	}
	
	public void acceptAlert() {
	    try {
	        Alert alert = driver.switchTo().alert();
	        System.out.println("Alert Message: " + alert.getText());
	        alert.accept();
	        System.out.println("Alert accepted successfully.");
	    } catch (NoAlertPresentException e) {
	        System.out.println("No alert present. Continuing test execution...");
	    }
	}
}
