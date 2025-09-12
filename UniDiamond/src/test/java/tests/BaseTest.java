package tests;

import base.BasePage;
import base.Page;
import utils.ConfigReader;
import utils.ExtentRepoertListeners;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	public WebDriver driver;
	public Page page;
	public ExtentRepoertListeners er;

	@BeforeMethod
	public void setup() {
		driver = BasePage.initBrowser(ConfigReader.getProperty("browser"));
		BasePage.setDriver(driver);
		page = new Page(driver);
		er = new ExtentRepoertListeners();
	}

	@AfterMethod
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

	public void login(String URL, String userName, String password) {
		driver.get(ConfigReader.getProperty(URL));
		page.loginPage.login(ConfigReader.getProperty(userName), ConfigReader.getProperty(password));
	}
}