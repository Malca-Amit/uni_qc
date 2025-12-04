package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Constant;
import utils.DBUtils;

public class PriceTest extends BaseTest {
	private static List<String> stoneIds;
	private static List<String> stoneTotalprices;

	@BeforeClass
	public void getStoneIdsFromDb() {
		String query = Constant.particularCompanyStones;
		stoneIds = DBUtils.getColumnValueFromDB(query, Constant.stoneProductId);
	}

	@BeforeClass
	public void getStoneTotalPrice() {
		String query = Constant.particularCompanyStones;
		stoneTotalprices = DBUtils.getColumnValueFromDB(query, "stone_total_price");
	}

	@Test(priority = 1, enabled = true)
	public void verifyTheFinalTotalPrice() throws InterruptedException {
		String stoneId = stoneIds.get(0);
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.cartPageUrl);
		page.shoppingCart.closeInfoPopup();
		page.shoppingCart.addStoneToCart("01-0" + stoneId);
		page.shoppingCart.searchStone("01-0" + stoneId);
		Assert.assertTrue(page.shoppingCart.isSingleSearchResultPresent(1));

		Assert.assertEquals(String.valueOf(page.paymentDetailsPage.getTheTotalPrice("01-" + stoneId)),
				stoneTotalprices.get(0));
	}
}