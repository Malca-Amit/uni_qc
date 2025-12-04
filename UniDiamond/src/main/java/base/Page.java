package base;

import org.openqa.selenium.WebDriver;

import pages.HomePage;
import pages.LoginPage;
import pages.MyFavoritesPage;
import pages.MyOrdersPage;
import pages.OrderConfirmationPage;
import pages.PaymentDetailsPage;
import pages.SearchPage;
import pages.SearchResultPage;
import pages.ShoppingCart;
import pages.StoneDetailsPage;
import pages.VendorOrdersPage;

public class Page {
	public LoginPage loginPage;
	public HomePage homePage;
	public SearchPage searchPage;
	public SearchResultPage searchResultPage;
	public ShoppingCart shoppingCart;
	public PaymentDetailsPage paymentDetailsPage;
	public OrderConfirmationPage orderConfirmationPage;
	public MyOrdersPage myOrdersPage;
	public VendorOrdersPage vendorOrdersPage;
	public MyFavoritesPage myFavoritesPage;
	public StoneDetailsPage stoneDetailsPage;

	public Page(WebDriver driver) {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		searchPage = new SearchPage(driver);
		searchResultPage = new SearchResultPage(driver);
		shoppingCart = new ShoppingCart(driver);
		paymentDetailsPage = new PaymentDetailsPage(driver);
		orderConfirmationPage = new OrderConfirmationPage(driver);
		myOrdersPage = new MyOrdersPage(driver);
		vendorOrdersPage = new VendorOrdersPage(driver);
		myFavoritesPage = new MyFavoritesPage(driver);
		stoneDetailsPage = new StoneDetailsPage(driver);
	}
}
