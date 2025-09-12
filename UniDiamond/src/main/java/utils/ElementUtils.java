package utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	private WebDriver driver;
	private static final int DEFAULT_TIMEOUT = 30;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriverWait getWait(int timeoutInSeconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	}

	private int resolveTimeout(Integer... timeout) {
		return (timeout.length > 0) ? timeout[0] : DEFAULT_TIMEOUT;
	}

	public String getPageTitle() {
		String value = null;
		try {
			value = driver.getTitle();
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return value;
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return element;
	}

	public WebElement getElementByText(String value) {
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//*[text()='" + value + "']"));
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> element = null;
		try {
			element = driver.findElements(locator);
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return element;
	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
	}

	public void doSendKeys(By locator, String value) {
		try {
			WebElement ele = getElement(locator);
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
	}

	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();

		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return null;
	}

	public boolean isDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return false;
	}

	public void selectFromDropdownUsingText(By locator, String value) {
		try {
			Select drp = new Select(getElement(locator));
			drp.selectByVisibleText(value);
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
	}

	public void selectByLoop(By locator, String value) {
		List<WebElement> elements = getElements(locator);
		/*
		 * for(int i=0;i<elements.size(); i++) { WebElement ele=elements.get(i); String
		 * eleValue = ele.getText(); }
		 */
		for (WebElement ele : elements) {
			String eleValue = ele.getText();
			if (eleValue.equalsIgnoreCase(value)) {
				ele.click();
				break;
			}
		}
	}

	public WebElement selectByLinkedText(String value) {
		try {
			return driver.findElement(By.linkText(value));
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return null;
	}

	public String getFirstSelectedOption(By locator) {

		try {
			Select drp = new Select(getElement(locator));
			WebElement drpEle = drp.getFirstSelectedOption();
			return drpEle.getText();
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return null;
	}

	public String getFirstSelectedOption(WebElement locator) {
		try {
			Select drp = new Select(locator);
			WebElement drpEle = drp.getFirstSelectedOption();
			return drpEle.getText();
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
		return null;
	}

	public void jsWaitForPageLoad() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("return document.readyState").equals("complete");
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
	}

	public void jsClick(By locator) {
		try {
			WebElement ele = getElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", ele);
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}

	}

	public void jsScroll(By locator) {
		try {
			WebElement ele = getElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
	}

	public void ScrollToRight(By locator) {
		try {
			WebElement ele = getElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(2000,0)", ele);
		} catch (Exception e) {
			LogUtils.debug("An exception occurs while getting element :" + e.getMessage());
		}
	}
	
	public void actionClick(By locator) {
		WebElement ele = getElement(locator);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}

	public boolean isElementPresent(By locator) {
		return !driver.findElements(locator).isEmpty();
	}

	public void mouseHoverToElement(By locator) {
		WebElement ele = getElement(locator);
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();
	}

	public void switchNewTab() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	public WebElement waitForElementPresence(By locator, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public List<WebElement> waitForAllElementPresence(By locator, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForElementVisible(By locator, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForAllElementsVisible(By locator, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public Boolean waitForElementInVisible(By locator, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public WebElement waitForElementClickable(By locator, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementClickable(WebElement element, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.elementToBeClickable(element));
	}

	public Boolean waitForTextPresent(By locator, String text, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	public boolean waitForExactNumberOfElements(By locator, int expectedCount, Integer... timeout) {
		return getWait(resolveTimeout(timeout)).until(driver -> driver.findElements(locator).size() == expectedCount);
	}
}