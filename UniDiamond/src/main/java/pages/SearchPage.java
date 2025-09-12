package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;

public class SearchPage {
	WebDriver driver;
	ElementUtils eu;

	private By stoneType(String type) {
	    return By.xpath("//label[contains(text(),'" + type + "')]");
	}
	
	By stoneType = By.xpath("//label[contains(text(),'"+"')]");
	By labGrown = By.cssSelector("[class*='stone_type'] label[for='tab2']");
	By pageHeader = By.cssSelector("[class*='page-head'] h1");

	private By diamondShape(String shape) {
		return By.cssSelector("[class='shape-selection'] [title='" + shape + "']");
	}

	private By caratSize(String start, String end) {
		return By.cssSelector("input.carat_range[data-start='" + start + "'][data-end='" + end + "']");
	}

	private By diamondColor(String color) {
		return By.cssSelector("[data-name='" + color + "'][name='color[]']");
	}

	private By diamondClarity(String clarity) {
		return By.cssSelector("[data-name='" + clarity + "'][name='clarity[]']");
	}

	private By stonePermissions(String permissions) {
		return By.cssSelector("[data-name='" + permissions + "'][name='stone_permissions[]']");
	}
	
	private By country(String country) {
		return By.cssSelector("[data-name='" + country + "'][name='country_id[]']");
	}

	By btnSearch = By.cssSelector("[class='btn primary-btn btn-search-uni']");
	By recentSearch = By.cssSelector("[class='editPencil span-left']:nth-of-type(1)");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}
	
	public String getPageHeader() {
		eu.waitForElementVisible(pageHeader);
		return eu.doGetText(pageHeader);
	}
	
	public void selectSearchBasicInfo(String type, List<String> shapes, List<String[]> sizeRanges, List<String> colors,
			List<String> clarities) {
		eu.waitForElementVisible(stoneType(type)).click();

		// Select one or more shapes
		for (String shape : shapes) {
			eu.waitForElementVisible(diamondShape(shape)).click();
		}

		// Select one or more size (carat) ranges
		for (String[] range : sizeRanges) {
			if (range.length == 2) {
				String start = range[0];
				String end = range[1];
				eu.jsClick(caratSize(start, end));
			}
		}

		// Select one or more colors
		for (String color : colors) {
			eu.jsClick(diamondColor(color));
		}

		// Select one or more clarities
		for (String clarity : clarities) {
			eu.jsClick(diamondClarity(clarity));
		}
	}
	
	public void selectCountry(String countryName) {
		eu.jsScroll(country(countryName));
		eu.jsClick(country(countryName));
	}

	public void selectStonePermissions(List<String> permissions) {
		
		for (String permission : permissions) {
			eu.jsScroll(stonePermissions(permission));
			eu.jsClick(stonePermissions(permission));
		}
	}

	public void clickOnSearchBtn() {
		eu.waitForElementClickable(btnSearch).click();
	}
	
	public String getRecentSearchValue() {
		eu.jsWaitForPageLoad();
		return eu.doGetText(recentSearch);
	}
}
