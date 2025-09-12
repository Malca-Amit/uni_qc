package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constant;

public class PurchaseWorkflowForLabGrownStoneTest extends BaseTest {

	@Test(priority = 1, enabled = true)
	public void verifyUserCanPlaceOrderUsingBuyForLabGrownStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.labGrown, Constant.DIAMOND_SHAPES_LAB_GROWN,
				Constant.CARAT_SIZE_RANGES, Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.clickOnSearchBtn();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String stoneId = page.searchResultPage.getStoneId();
		page.searchResultPage.clickOnAddToCartBtn(stoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
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
	public void verifyUserCanPlaceOfferUsingMemoForLabGrownStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.labGrown, Constant.DIAMOND_SHAPES_LAB_GROWN,
				Constant.CARAT_SIZE_RANGES, Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.MEMO_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String stoneId = page.searchResultPage.getStoneId();
		System.out.println("LabGrownSingleMemoStone=" + stoneId);
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

	@Test(priority = 3, enabled = true)
	public void verifyUserCanPlaceOrderUsingBuyWithMultipleLabGrownStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.labGrown, Constant.DIAMOND_SHAPES_LAB_GROWN,
				Constant.CARAT_SIZE_RANGES, Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.clickOnSearchBtn();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
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
		page.shoppingCart.acceptAlert();
		page.shoppingCart.closeInfoPopup();

		page.paymentDetailsPage.clickOnTermsConditions();
		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.buyOrderConfirmationMessage));
	}

	@Test(priority = 4, enabled = true)
	public void verifyUserCanPlaceOfferUsingMemoWithMultipleLabGrownStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.labGrown, Constant.DIAMOND_SHAPES_LAB_GROWN,
				Constant.CARAT_SIZE_RANGES, Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectCountry(Constant.country);
		page.searchPage.selectStonePermissions(Constant.MEMO_PERMISSIONS);
		page.searchPage.clickOnSearchBtn();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		page.searchResultPage.selectListView();
		Assert.assertTrue(page.searchResultPage.isSearchResultTableDisplayed());
		String firstStoneId = page.searchResultPage.getStoneId();
		System.out.println("LabGrownMultipleMemoStone, First ID=" + firstStoneId);
		page.searchResultPage.clickOnAddToCartBtn(firstStoneId);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		String secondStoneId = page.searchResultPage.getStoneId();
		System.out.println("LabGrownMultipleMemoStone, Second ID=" + secondStoneId);
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
}
