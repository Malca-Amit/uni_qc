package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.Constant;

public class SearchPageTest extends BaseTest {
	@Test(priority = 1, enabled = false)
	public void verifyReturnTheMemoStone() {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		
	}

	@Test(priority = 2, enabled = true)
	public void verifyBuyTheMemoStone() {
		login("url", "frontendUsername", "frontendpassword");
		Assert.assertTrue(page.homePage.isLoggedInUserDisplayed(), "User does not login");
		page.homePage.goToTheLeftNavigation(Constant.myOrdersLeftNav);
		page.myOrdersPage.searchStoneId(Constant.buyStoneId);
		Assert.assertTrue(page.myOrdersPage.isSingleSearchResultPresent());
		page.myOrdersPage.clickOnBuyBtn();
		Assert.assertTrue(page.myOrdersPage.isBuyStonePopupDisplayed());
		page.myOrdersPage.checkTermsAndCondition();
		page.myOrdersPage.clickOnBuyThiStoneBtn();
		Assert.assertTrue(page.myOrdersPage.isSuccessMessageDisplayed());
	}
}
