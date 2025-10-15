package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Constant;
import utils.DBUtils;

public class WednesdaySheetTest extends BaseTest {
	private static List<String> stoneIds;

	@BeforeClass
	public void getDBData() {
		String query = Constant.particularCompanyStones;
		stoneIds = DBUtils.getColumnValueFromDB(query, Constant.stoneProductId);
	}

	@Test(priority = 1, enabled = true)
	public void verifyCreateMemoOfferWithSingleStoneAndAcceptFromSeller() throws InterruptedException {
		String stoneId = stoneIds.get(0);
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation("cart");
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnMemoBtn();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.clickYesOnSimilarStoneConfirmationPopup();

		page.paymentDetailsPage.selectMemoTermsAndCondition();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.memoOfferConfirmationMessage));

		page.homePage.logoutTheFrontEndUser();

		login("url", "vendorUsername", "vendorpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.vendorOrdersLeftNav);
		Assert.assertTrue(page.vendorOrdersPage.isVendorOrdersTitleDisplayed());
		page.vendorOrdersPage.searchWithStoneIdOrCertificateNo("01-0" + stoneId);
		page.vendorOrdersPage.isSingleSearchResultPresent(1);
		page.vendorOrdersPage.acceptTheMemo();
		Assert.assertTrue(page.vendorOrdersPage.isSuccessMessageDisplayed());
		Assert.assertTrue(page.vendorOrdersPage.isPendingShipmentStatusIsDisplayed());
	}

	@Test(priority = 2, enabled = true)
	public void verifyCreateMemoOfferWithSingleStoneAndRejectFromSeller() throws InterruptedException {
		String stoneId = stoneIds.get(1);
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation("cart");
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnMemoBtn();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.clickYesOnSimilarStoneConfirmationPopup();

		page.paymentDetailsPage.selectMemoTermsAndCondition();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.memoOfferConfirmationMessage));

		page.homePage.logoutTheFrontEndUser();

		login("url", "vendorUsername", "vendorpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.vendorOrdersLeftNav);
		Assert.assertTrue(page.vendorOrdersPage.isVendorOrdersTitleDisplayed());
		page.vendorOrdersPage.searchWithStoneIdOrCertificateNo("01-0" + stoneId);
		page.vendorOrdersPage.rejectTheBidOrMemo();
		// Assert.assertTrue(page.vendorOrdersPage.issuccessMessageForBidrejectionDisplayed());
		Assert.assertTrue(page.vendorOrdersPage.isRejectedStatusIsDisplayed());
	}

	@Test(priority = 3, enabled = true)
	public void verifyCreateBidOfferWithSingleStoneAndAcceptFromSeller() throws InterruptedException {
		String stoneId = stoneIds.get(2);
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation("cart");
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBidBtn();
		Assert.assertTrue(page.shoppingCart.isBidCalculatorDisplayed(), "Bid Calculator is not visible");
		page.shoppingCart.applyTenPercentDiscount();
		page.paymentDetailsPage.selectBidTermsAndConditions();
		page.paymentDetailsPage.selectShippingAddress();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();
		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.bidOfferConfirmationMessage));

		page.homePage.logoutTheFrontEndUser();

		login("url", "vendorUsername", "vendorpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.vendorOrdersLeftNav);
		Assert.assertTrue(page.vendorOrdersPage.isVendorOrdersTitleDisplayed());
		page.vendorOrdersPage.searchWithStoneIdOrCertificateNo("01-0" + stoneId);
		page.vendorOrdersPage.acceptTheBid();
		Assert.assertTrue(page.vendorOrdersPage.isSuccessMessageDisplayed());
		Assert.assertTrue(page.vendorOrdersPage.isPendingShipmentStatusIsDisplayed());
	}

	@Test(priority = 4, enabled = true)
	public void verifyCreateBidOfferWithSingleStoneAndRejectFromSeller() throws InterruptedException {
		String stoneId = stoneIds.get(3);
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation("cart");
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBidBtn();
		Assert.assertTrue(page.shoppingCart.isBidCalculatorDisplayed(), "Bid Calculator is not visible");
		page.shoppingCart.applyTenPercentDiscount();
		page.paymentDetailsPage.selectBidTermsAndConditions();
		page.paymentDetailsPage.selectShippingAddress();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();
		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.bidOfferConfirmationMessage));

		page.homePage.logoutTheFrontEndUser();

		login("url", "vendorUsername", "vendorpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.vendorOrdersLeftNav);
		Assert.assertTrue(page.vendorOrdersPage.isVendorOrdersTitleDisplayed());
		page.vendorOrdersPage.searchWithStoneIdOrCertificateNo("01-0" + stoneId);
		page.vendorOrdersPage.rejectTheBid();
		Assert.assertTrue(page.vendorOrdersPage.isSuccessMessageDisplayed());
		Assert.assertTrue(page.vendorOrdersPage.isRejectedStatusIsDisplayed());
	}

	@Test(priority = 5, enabled = true)
	public void VerifyAddQcAndCreateOrderWithQCAndNonQCStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.clickOnSearchBtn();
		page.searchResultPage.selectListView();
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
		page.shoppingCart.addToQC();
		Assert.assertTrue(page.shoppingCart.isQcRequestSavedSuccesMessageDisplayed());
		String qcComment = page.shoppingCart.getQcTooltipComment();
		Assert.assertTrue(qcComment.contains("Testing"), "QC comment mismatched");
		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBuyNowBtn();
		page.shoppingCart.closeInfoPopup();

		page.paymentDetailsPage.clickOnTermsConditions();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.buyOrderConfirmationMessage));

		String[] orderNumbers = page.orderConfirmationPage.getOrderNumbers();
		Assert.assertNotEquals(orderNumbers[0], orderNumbers[1], "Order numbers should be different");
	}
}
