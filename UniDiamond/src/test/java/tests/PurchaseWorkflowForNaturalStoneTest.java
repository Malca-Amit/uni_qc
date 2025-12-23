package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constant;

public class PurchaseWorkflowForNaturalStoneTest extends BaseTest {
	@Test(priority = 1, enabled = false)
	public void verifyUserCanPlaceOrderUsingBuyForNaturalStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.clickOnSearchBtn();
		//Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String stoneId = page.searchResultPage.getStoneId();
		page.searchResultPage.clickOnAddToCartBtn(stoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		System.out.println("hello =" + stoneId);
		page.shoppingCart.searchStone(stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBuyNowBtn();
		page.shoppingCart.closeInfoPopup();

		page.paymentDetailsPage.clickOnTermsConditions();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.buyOrderConfirmationMessage));
	}

	@Test(priority = 2, enabled = true)
	public void verifyUserCanPlaceOfferUsingMemoForNaturalStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.MEMO_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		//Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String stoneId = page.searchResultPage.getStoneId();
		System.out.println("NaturalSingleMemoStone=" + stoneId);
		page.searchResultPage.clickOnAddToCartBtn(stoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.searchStone(stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnMemoBtn();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.clickYesOnSimilarStoneConfirmationPopup();

		page.paymentDetailsPage.selectMemoTermsAndCondition();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.memoOfferConfirmationMessage));
	}

	@Test(priority = 3, enabled = false)
	public void verifyUserCanPlaceOfferUsingBidForNaturalStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.BID_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		//Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String stoneId = page.searchResultPage.getStoneId();
		System.out.println("NaturalSingleBidStone=" + stoneId);
		page.searchResultPage.clickOnAddToCartBtn(stoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.searchStone(stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBidBtn();
		Assert.assertTrue(page.shoppingCart.isBidCalculatorDisplayed(), "Bid Calculator is not visible");
		page.shoppingCart.applyTenPercentDiscount();
		page.paymentDetailsPage.selectShippingAddress();
		page.paymentDetailsPage.selectBidTermsAndConditions();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();
		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.bidOfferConfirmationMessage));
	}

	@Test(priority = 4, enabled = false)
	public void verifyUserCanPlaceOrderUsingBuyWithMultipleNaturalStones() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.clickOnSearchBtn();
		//Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String firstStoneId = page.searchResultPage.getStoneId();
		page.searchResultPage.clickOnAddToCartBtn(firstStoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		String secondStoneId = page.searchResultPage.getStoneId();
		page.searchResultPage.clickOnAddToCartBtn(secondStoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.searchStone(firstStoneId, secondStoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(2));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBuyNowBtn();
		page.shoppingCart.closeInfoPopup();

		page.paymentDetailsPage.clickOnTermsConditions();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.buyOrderConfirmationMessage));
	}

	@Test(priority = 5, enabled = true)
	public void verifyUserCanPlaceOfferUsingMemoWithMultipleNaturalStones() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.MEMO_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		//Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String firstStoneId = page.searchResultPage.getStoneId();
		System.out.println("NaturalMultipleMemoStone, First ID=" + firstStoneId);
		page.searchResultPage.clickOnAddToCartBtn(firstStoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		String secondStoneId = page.searchResultPage.getStoneId();
		System.out.println("NaturalMultipleMemoStone, Second ID=" + secondStoneId);
		page.searchResultPage.clickOnAddToCartBtn(secondStoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.searchStone(firstStoneId, secondStoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(2));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnMemoBtn();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.clickYesOnSimilarStoneConfirmationPopup();

		page.paymentDetailsPage.selectMemoTermsAndCondition();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.memoOfferConfirmationMessage));
	}

	@Test(priority = 6, enabled = false)
	public void verifyUserCanPlaceOfferUsingBidWithMultipleNaturalStones() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.BID_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		//Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String firstStoneId = page.searchResultPage.getStoneId();
		System.out.println("NaturalMultipleBidStone, First ID=" + firstStoneId);
		page.searchResultPage.clickOnAddToCartBtn(firstStoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		String secondStoneId = page.searchResultPage.getStoneId();
		System.out.println("NaturalMultipleBidStone, Second ID=" + firstStoneId);
		page.searchResultPage.clickOnAddToCartBtn(secondStoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.searchStone(firstStoneId, secondStoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(2));
		page.shoppingCart.selectCheckbox();

		page.shoppingCart.clickOnBidBtn();
		Assert.assertTrue(page.shoppingCart.isBidCalculatorDisplayed(), "Bid Calculator is not visible");
		page.shoppingCart.applyTenPercentDiscountForAllStones();
		page.paymentDetailsPage.selectBidTermsAndConditions();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();
		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.bidOfferConfirmationMessage));
	}

	@Test(priority = 7, enabled = false)
	public void verifyUserCanPlaceMemoOfferFromStoneDetails() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.MEMO_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		page.searchResultPage.selectGridView();
		page.searchResultPage.clickOnDetailsBtn();

		// Get the current window handle (main window)
		String mainWindow = driver.getWindowHandle();

		// Switch to the new window that opens after clicking on Details button
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindow)) {
				driver.switchTo().window(windowHandle);
				break; // Switch to the new window and break out of the loop
			}
		}
		Assert.assertTrue(page.stoneDetailsPage.isStoneDetailsSectionDisplayed(), "Memo popup is not visible");
		page.stoneDetailsPage.clickOnMemo();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.clickYesOnSimilarStoneConfirmationPopup();
		Assert.assertTrue(page.stoneDetailsPage.isPopupDisplayed(), "Memo popup is not visible");
		page.stoneDetailsPage.selectTermsCondition();
		page.stoneDetailsPage.clickOnPlaceOrder();
		Assert.assertTrue(page.stoneDetailsPage.getMemoPlacedSuccessMessage().contains("Your memo has been submitted"));
	}

	@Test(priority = 8, enabled = false)
	public void verifyUserCanPlaceBidOfferFromStoneDetails() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.BID_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		page.searchResultPage.selectGridView();
		page.searchResultPage.clickOnDetailsBtn();

		// Get the current window handle (main window)
		String mainWindow = driver.getWindowHandle();

		// Switch to the new window that opens after clicking on Details button
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindow)) {
				driver.switchTo().window(windowHandle);
				break; // Switch to the new window and break out of the loop
			}
		}
		Assert.assertTrue(page.stoneDetailsPage.isStoneDetailsSectionDisplayed(), "Memo popup is not visible");
		page.stoneDetailsPage.clickOnBid();
		Assert.assertTrue(page.stoneDetailsPage.isPopupDisplayed(), "Bid popup is not visible");
		page.shoppingCart.applyTenPercentDiscount();
		page.stoneDetailsPage.clickOnSubmitBtn();
		page.paymentDetailsPage.selectShippingAddress();
		page.paymentDetailsPage.selectBidTermsAndConditions();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();
		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.bidOfferConfirmationMessage));
	}
}