package utils;

import java.util.Arrays;
import java.util.List;

public class Constant {
	//SearchPage
	public static String searchLeftNav = "search";
	public static String pageHeader = "UNI Smart+";
	public static String natural = "Natural";
	public static String labGrown = "Lab Grown";
	public static final List<String> DIAMOND_SHAPES = Arrays.asList("Round", "Oval");
	public static final List<String> DIAMOND_SHAPES_LAB_GROWN = Arrays.asList("Oval", "Cushion", "Marquise", "Heart", "Emerald");
	public static final List<String[]> CARAT_SIZE_RANGES = Arrays.asList(new String[] { "0.18", "0.22" },
			new String[] { "0.23", "0.29" }, new String[] { "0.30", "0.39" }, new String[] { "0.40", "0.49" },
			new String[] { "1.00", "1.09" });
	public static final List<String> DIAMOND_COLORS = Arrays.asList("F", "H", "I", "J");
	public static final List<String> DIAMOND_CLARITIES = Arrays.asList("VS1", "FL", "IF");
	public static final List<String> MEMO_PERMISSIONS = Arrays.asList("Memo");
	public static final List<String> BID_PERMISSIONS = Arrays.asList("Bid");
	public static String country = "United States";
	
	//ConfirmationPage
	public static String buyOrderConfirmationMessage = "Thank you for your order!";
	public static String memoOfferConfirmationMessage = "Your memo request has been submitted!";
	public static String bidOfferConfirmationMessage = "Your Bid Has Been Submitted";
	
	//searchResultPage
	public static String stoneFound = "2 Stones Found";
	
	//My Order Page
	public static String myOrdersLeftNav = "my-orders";
	public static String reason = "Item damaged/defective";
	public static String returnStoneId = "01-11416275"; 
	public static String buyStoneId = "01-08659452"; 
	
	//payment Details Page
	public static String shippingAddress = "United";
	
	//Vendor Orders Page
	public static String vendorOrdersLeftNav = "vendor-orders";
	
	//homePage
	public static String retailerMarkupPriceStone = "01-11665372"; 
	
	//Shopping Cart
	public static String cartPageUrl = "cart"; 
	public static String memoAcceptFromSeller = "01-01623199"; 
	public static String memoRejectFromSeller = "01-01469338"; 	
	public static String bidAcceptFromSeller = "01-00873117"; 
	public static String bidRejectFromSeller = "01-00851951";
	
	//Data Base
	public static String particularCompanyStones = "Select * from tbl_data_stones Where stone_is_bid_permission=1 and stone_is_memo_permission=1 and stone_company_id=550 and stone_country=223 and stone_size < 1.5 order By stone_product_id desc"; 
	public static String stoneProductId = "stone_product_id";
	
	public static String breakdown(String productId, int compid, int brokrage, int creditCost, int uniMarkup, int dataMargin, int retailerMarkup ) {

	    String breakdownValue =
	            "SELECT STONE_PRICE_WITH_BREAKDOWN(1, " + productId + ", "+compid+", 0, JSON_OBJECT(" +
	            " 'includePriorityDiscount', 1," +
	            " 'includeBrokerage', "+brokrage+"," +
	            " 'includeCreditCost', "+creditCost+"," +
	            " 'includeUniMargin', "+uniMarkup+"," +
	            " 'includeDataMargin', "+dataMargin+"," +
	            " 'includeRetailerMarkup', "+retailerMarkup+"," +
	            " 'includeBreakDown', 1," +
	            " 'includeUSImportTax', 0," +
	            " 'shippingAddressId', 0," +
	            " 'includeQC', 0," +
	            " 'includeOriginDeclaration', 0," +
	            " 'includeLGHandlingFee', 0" +
	            ")) AS prodprice_ask_total_price;";

	    return breakdownValue;
	}
	
	/*
	 * Note 
	 * Companies
	 * name: Automation Company : Lio, Retailer Lio
	 * name: Automation Without Subscription : 	RetailerWithMarkup, userWithoutSubscription
	 */
	
	//PreCondition
	// 01-11479276 Remove QA from Aditya User
	// need to update Buy and return stones from frontend
	
	//Git access token: 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
}
