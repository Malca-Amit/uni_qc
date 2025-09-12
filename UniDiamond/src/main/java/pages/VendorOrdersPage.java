package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class VendorOrdersPage {
	WebDriver driver;
	ElementUtils eu;

	By pageTitle = By.cssSelector("[class='container']");
	By searchWithStoneIdCertificateNo = By.cssSelector("[placeholder='Search By Cert no. or Stone ID']");
	By searchIcon = By.cssSelector("[onclick='filterSearch()']");
	By processingLoader = By.cssSelector("[class*='alert--process']");
	By acceptBtn = By.cssSelector("[class='btn accept-order-btn']");
	By rejectBtn = By.cssSelector("[class='btn cancel-order-btn']");
	By rejectionReason = By.xpath("//label[contains(text(),'Not Available')]//input");
	By reasonComment = By.cssSelector("[placeholder='Provide A Comment']");
	By selectReasonBtn = By.cssSelector("[value='Select Reason']");
	By successMessage = By.cssSelector("[class*='alert--success']");
	By noRecordFoundPageLoader = By.cssSelector("[id='no-record-found']");
	By statusPendingShipment = By.xpath("//button[contains(text(),'Pending Shipment')]");
	By statusRejected = By.xpath("//button[contains(text(),'Rejected')]");
	By bidConfirmationPopup = By.cssSelector("[class='popup-confirm popup-confirm--vertical']");
	By bidConfirmationYesBtn = By.cssSelector("a[class='btn btn--primary']");
	By pendingVendorPayment = By.xpath("//span[contains(text(),'Pending Vendor Payment')]");
	By successMessageForBidrejection = By.xpath("//div[contains(text(),'Offer rejected.')]");
	By popupConfirmation = By.cssSelector("[id='confirmYes']");
	By tableRow = By.cssSelector("[class*='row-selector']");
	By summaryDetails = By.cssSelector("[class='summary-details']");
	
	public VendorOrdersPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}

	public boolean isVendorOrdersTitleDisplayed() {
		return eu.waitForElementVisible(pageTitle).isDisplayed();
	}

	public void searchWithStoneIdOrCertificateNo(String value) {
		eu.waitForElementVisible(summaryDetails);
		eu.waitForElementInVisible(noRecordFoundPageLoader);
		eu.waitForElementVisible(searchWithStoneIdCertificateNo);
		eu.getElement(searchWithStoneIdCertificateNo).clear();
		eu.doSendKeys(searchWithStoneIdCertificateNo, value);
		eu.doClick(searchIcon);
		eu.waitForElementInVisible(processingLoader, 30);
	}

	public void acceptTheMemo() {
		eu.waitForElementClickable(acceptBtn).click();
		eu.waitForElementClickable(popupConfirmation).click();
	}

	public void rejectTheBidOrMemo() {
		eu.waitForElementClickable(rejectBtn).click();
		eu.waitForElementVisible(selectReasonBtn);
		eu.jsClick(rejectionReason);
		eu.doSendKeys(reasonComment, "Testing");
		eu.doClick(selectReasonBtn);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		eu.waitForElementInVisible(rejectionReason);
	}

	public boolean isSuccessMessageDisplayed() {
		return eu.waitForElementVisible(successMessage).isDisplayed();
	}

	public boolean isPendingShipmentStatusIsDisplayed() {
		return eu.waitForElementVisible(statusPendingShipment).isDisplayed();
	}

	public boolean isRejectedStatusIsDisplayed() {
		return eu.waitForElementVisible(statusRejected).isDisplayed();
	}

	public void acceptTheBid() {
		eu.waitForElementClickable(acceptBtn).click();
		eu.waitForElementVisible(bidConfirmationPopup);
		eu.waitForElementClickable(bidConfirmationYesBtn).click();
		eu.waitForElementInVisible(processingLoader);
		eu.waitForElementInVisible(bidConfirmationPopup);
	}

	public boolean isPendingVendorPaymentStatusIsDisplayed() {
		return eu.waitForElementVisible(pendingVendorPayment).isDisplayed();
	}
	
	public boolean issuccessMessageForBidrejectionDisplayed() {
		return eu.waitForElementVisible(successMessageForBidrejection).isDisplayed();
	}

	public void rejectTheBid() {
		eu.waitForElementClickable(rejectBtn).click();
		eu.waitForElementClickable(popupConfirmation).click();
	}
	public boolean isSingleSearchResultPresent(int expectedCount) {
		return eu.waitForExactNumberOfElements(tableRow, expectedCount);
	}
}
