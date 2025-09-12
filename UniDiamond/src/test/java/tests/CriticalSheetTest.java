package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Constant;
import utils.DBUtils;

public class CriticalSheetTest extends BaseTest {
	private static List<String> stoneIds;

	@BeforeClass
	public void getDBData() {
		String query = Constant.particularCompanyStones;
		stoneIds = DBUtils.getColumnValueFromDB(query, Constant.stoneProductId);
	}

	@Test(priority = 1, enabled = true)
	public void verifyThePricesWithMarkupForRetailerUser() throws InterruptedException {
		String stone = stoneIds.get(0);
		System.out.println("01-" + stone);
		login("url", "withoutSubsUsername", "withoutSubspassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		page.homePage.searchStone("01-" + stone);
		page.searchResultPage.selectListView();
		double finalPPC = page.searchResultPage.getTheFinalPpcPrice("01-" + stone);
		double totalPrice = page.searchResultPage.getTheTotalPrice("01-" + stone);
		System.out.println(finalPPC);
		System.out.println(totalPrice);

		page.homePage.logoutTheFrontEndUser();

		login("url", "retailerWithSubsUsername", "retailerWithSubspassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.searchStone("01-" + stone);
		page.searchResultPage.selectListView();

		// verify the retailer markup price on search result page
		Assert.assertEquals((int) page.searchResultPage.getTheFinalPpcPrice("01-" + stone), (int) (finalPPC * 2));
		Assert.assertEquals((int) page.searchResultPage.getTheTotalPrice("01-" + stone), (int) (totalPrice * 2));

		page.searchResultPage.clickOnAddToCartBtn("01-" + stone);
		page.searchResultPage.closeTheAddToCartSuccessPopup();
		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.searchStone("01-" + stone);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));

		// verify the retailer markup price on shopping cart page
		Assert.assertEquals((int) page.shoppingCart.getTheFinalPpcPrice(), (int) (finalPPC * 2));
		Assert.assertEquals((int) page.shoppingCart.getTheTotalPrice(), (int) (totalPrice * 2));
	}

	@Test(priority = 2, enabled = true)
	public void VerifyPriceOnAllMediaIconsForEveryPage() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.clickOnSearchBtn();
		page.searchResultPage.selectListView();
		String stoneId = page.searchResultPage.getStoneIdForFirstEnabledMediaIcon();
		System.out.println(stoneId);
		double finalPpcPrice = page.searchResultPage.getTheFinalPpcPrice(stoneId);
		System.out.println(finalPpcPrice);
		page.searchResultPage.clickOnMediaIcon(stoneId);
		String ppcPriceSearchResult = page.searchResultPage.getPPCfromMediaPopup();
		System.out.println(ppcPriceSearchResult);
		Assert.assertTrue(String.valueOf(finalPpcPrice).contains(ppcPriceSearchResult.replaceAll("[^0-9.]", "")));
		page.searchResultPage.closeThePopup();
		page.homePage.clickOnFavorits();
		page.myFavoritesPage.addStoneIntoFavList(stoneId);
		page.myFavoritesPage.searchStone(stoneId);
		Assert.assertTrue(page.myFavoritesPage.isSingleSearchResultPresent(1));
		page.myFavoritesPage.clickOnMediaButton();
		String ppcPriceOnFav = page.myFavoritesPage.getPPCfromMediaPopup();
		System.out.println(ppcPriceOnFav);
		Assert.assertTrue(String.valueOf(finalPpcPrice).contains(ppcPriceOnFav.replaceAll("[^0-9.]", "")));
		page.searchResultPage.closeThePopup();

		page.homePage.clickOnCart();
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart(stoneId);
		page.shoppingCart.searchStone(stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.clickOnMediaButton();
		String ppcPriceOnCart = page.shoppingCart.getPPCfromMediaPopup();
		Assert.assertTrue(String.valueOf(finalPpcPrice).contains(ppcPriceOnCart.replaceAll("[^0-9.]", "")));
		page.searchResultPage.closeThePopup();
	}

	@Test(priority = 3, enabled = true)
	public void verifyUniDiscountShouldNotShowAfterAddTheQC() throws InterruptedException {
		login("url", "uniDiamondsUsername", "uniDiamondspassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation("cart");
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-11479276");
		page.shoppingCart.searchStone("01-11479276");
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));
		page.shoppingCart.addToQC();
		String qcComment = page.shoppingCart.getQcTooltipComment();
		Assert.assertTrue(qcComment.contains("Testing"), "QC comment mismatched");
		page.shoppingCart.selectCheckbox();
		Assert.assertFalse(page.shoppingCart.isUniDiscountDisplayed(), "Uni Discount is unexpectedly displayed.");
		page.shoppingCart.clickOnBuyNowBtn();
		Assert.assertFalse(page.paymentDetailsPage.getBuyDetails().contains("Uni Discount"),
				"'Uni Discount' should not be present, but it was found.");
	}

	@Test(priority = 4, enabled = true)
	public void verifyUserIsNotAbleToPlaceBidOfferForTheQcStone() throws InterruptedException {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.searchLeftNav);

		Assert.assertTrue(page.searchPage.getPageHeader().contains(Constant.pageHeader));
		page.searchPage.selectSearchBasicInfo(Constant.natural, Constant.DIAMOND_SHAPES, Constant.CARAT_SIZE_RANGES,
				Constant.DIAMOND_COLORS, Constant.DIAMOND_CLARITIES);
		page.searchPage.selectStonePermissions(Constant.BID_PERMISSIONS);
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
		page.shoppingCart.addToQC();
		String qcComment = page.shoppingCart.getQcTooltipComment();
		Assert.assertTrue(qcComment.contains("Testing"), "QC comment mismatched");
		page.shoppingCart.clickOnSingleBid();
		Assert.assertTrue(page.shoppingCart.isAlertMessageDisplayed(), "Alert message is not displayed");
	}

	@Test(priority = 5, enabled = true)
	public void verifyAddingMultipleStoneUsingQuickSearch() throws InterruptedException {
		String firstStone = stoneIds.get(1);
		String secondStone = stoneIds.get(2);
		login("url", "withoutSubsUsername", "withoutSubspassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");

		page.homePage.searchStone("01-" + firstStone, "01-" + secondStone);
		Assert.assertTrue(page.searchResultPage.getStoneCount().contains(Constant.stoneFound), "stone not found");
	}
}
