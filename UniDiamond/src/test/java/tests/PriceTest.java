package tests;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Constant;
import utils.DBUtils;

public class PriceTest extends BaseTest {
	private static List<String> stoneIds;

	@BeforeMethod
	public void getStoneIdsFromDb() {
		String query = Constant.particularCompanyStones;
		stoneIds = DBUtils.getColumnValueFromDB(query, Constant.stoneProductId);
	}

	public double getTotalFromDB(String productId, int compid, int brokrage, int creditCost, int uniMarkup,
			int dataMargin, int retailerMarkup) {
		JSONObject breakdownResponse = DBUtils.getBreakdownResponse(
				Constant.breakdown(productId, compid, brokrage, creditCost, uniMarkup, dataMargin, retailerMarkup));
		double totalFromDB = breakdownResponse.getDouble("total");
		return totalFromDB;
	}

	@Test(priority = 1, enabled = true, description = "uniMarkup no, Credit cost yes, D mergin yes, Regular Stone Invoice Only On Checkout")
	public void verifyThePriceForRegularStoneInvoiceOnlyOnCheckoutWithoutUniMarkup() throws InterruptedException {
		String stoneId = stoneIds.get(0);
		login("url", "frontendUsername1", "frontendpassword1");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.cartPageUrl);
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));

		double buyTotalPrice = page.shoppingCart.getTheTotalPrice();
	    System.out.println("buyTotalPrice Shopigcart=" +buyTotalPrice);

		String productValue = page.shoppingCart.getValueAttribute();

		double DbTotal = getTotalFromDB(productValue, 4927, 0, 1, 0, 1, 0);
		 System.out.println("DB Total=" +DbTotal);

		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBuyNowBtn();
		page.shoppingCart.closeInfoPopup();

		page.paymentDetailsPage.clickOnTermsConditions();

		double additionPercentageOfCreditCostAndDMargin = page.paymentDetailsPage
				.CalculatePercentageOfCreditCostAndDMargin(buyTotalPrice);

		double handlingFee = page.paymentDetailsPage.getHandlingFee();

		Assert.assertEquals((int) additionPercentageOfCreditCostAndDMargin, (int) handlingFee);

		double totalPrice = page.paymentDetailsPage.getTotalprice();
		Assert.assertEquals((int) totalPrice, (int) DbTotal,
				"DB breakdown Total price and checkout UI total price mismatch ");

		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.buyOrderConfirmationMessage));

		page.homePage.goToTheLeftNavigation(Constant.myOrdersLeftNav);
		page.myOrdersPage.searchStoneId("01-0" + stoneId);

		Assert.assertTrue(page.myOrdersPage.isSingleSearchResultPresent());
		double myOrderTotalPrice = page.myOrdersPage.getTotalPrice();

		Assert.assertEquals((int) myOrderTotalPrice, (int) totalPrice, "Price mismatch on my order page");
	}

	@Test(priority = 2, enabled = true, description = "uniMarkup yes, Credit cost yes, D mergin yes, Regular Stone Invoice Only On Checkout")
	public void verifyThePriceForRegularStoneInvoiceOnlyOnCheckoutWithUniMarkup() throws InterruptedException {
		String stoneId = stoneIds.get(0);
		login("url", "frontendUsername2", "frontendpassword2");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.cartPageUrl);
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));

		double buyTotalPrice = page.shoppingCart.getTheTotalPrice();

		String productValue = page.shoppingCart.getValueAttribute();

		double DbTotal = getTotalFromDB(productValue, 4928, 0, 1, 1, 1, 0);

		page.shoppingCart.selectCheckbox();
		page.shoppingCart.clickOnBuyNowBtn();
		page.shoppingCart.closeInfoPopup();

		page.paymentDetailsPage.clickOnTermsConditions();
		Assert.assertEquals((int) buyTotalPrice, (int) page.paymentDetailsPage.getBuyPrice(),
				"Cart page Total price and checkout UI Buy price mismatch ");

		double calculateHandlingFee = page.paymentDetailsPage.getBuyPrice() * 0.1;
		Assert.assertEquals((int) calculateHandlingFee, (int) page.paymentDetailsPage.getHandlingFee(),
				"Calculation of handling fee and payment details page handling fee are mismtached");

		double totalPrice = page.paymentDetailsPage.getTotalprice();
		Assert.assertEquals((int) totalPrice, (int) DbTotal,
				"DB breakdown Total price and payment details page UI total price mismatch");

		page.paymentDetailsPage.clickOnPlaceOrderBtn();

		Assert.assertTrue(
				page.orderConfirmationPage.getConfimrationMessage().contains(Constant.buyOrderConfirmationMessage));

		page.homePage.goToTheLeftNavigation(Constant.myOrdersLeftNav);
		page.myOrdersPage.searchStoneId("01-0" + stoneId);

		Assert.assertTrue(page.myOrdersPage.isSingleSearchResultPresent());
		double myOrderTotalPrice = page.myOrdersPage.getTotalPrice();

		Assert.assertEquals((int) myOrderTotalPrice, (int) totalPrice, "Price mismatch on my order page");
	}
}