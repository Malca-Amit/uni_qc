package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ElementUtils;

public class LoginPage {
	WebDriver driver;
	ElementUtils eu;

	By emailAddress = By.cssSelector("[name='email']");
	By password = By.cssSelector("[name='password']");
	By btnLogin = By.cssSelector("[name='btn_submit']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eu = new ElementUtils(driver);
	}

	public void login(String email, String pass) {
		eu.waitForElementClickable(btnLogin);
		eu.doSendKeys(emailAddress, email);
		eu.doSendKeys(password, pass);
		eu.doClick(btnLogin);
	}
}
