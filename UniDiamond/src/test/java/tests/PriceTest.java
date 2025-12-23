package tests;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Constant;
import utils.DBUtils;

public class PriceTest extends BaseTest {
	private static List<String> stoneIds;

	@BeforeClass
	public void getStoneIdsFromDb() {
		String query = Constant.particularCompanyStones;
		stoneIds = DBUtils.getColumnValueFromDB(query, Constant.stoneProductId);
	}

	public double getTotalFromDB(String productId) {
		JSONObject breakdownResponse = DBUtils.getBreakdownResponse(Constant.breakdown(productId));
		double totalFromDB = breakdownResponse.getDouble("total");
		return totalFromDB;
	}

	@Test(priority = 1, enabled = true)
	public void verifyThePriceForRegularStoneInvoiceOnlyOnCheckout() throws InterruptedException {
		String stoneId = stoneIds.get(0);
		login("url", "frontendUsername1", "frontendpassword1");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.cartPageUrl);
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));

		double buyTotalPrice = page.shoppingCart.getTheTotalPrice();

		String productValue = page.shoppingCart.getValueAttribute();

		double DbTotal = getTotalFromDB(productValue);

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
}