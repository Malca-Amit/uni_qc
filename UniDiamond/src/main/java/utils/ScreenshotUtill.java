package utils;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class ScreenshotUtill {
	/**
	 * This method is used to take the screenshot
	 * @param screenShotName
	 * @return
	 */
    public String captureScreenshot(String screenShotName) {
        try {
            WebDriver driver = BasePage.getDriver();  // ✅ Get it here
            String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + screenShotName + ".png";
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return "./screenshots/" + screenShotName + ".png";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


	/**
	 * This method is used to take the screenshot for full page
	 * @param screenShotName
	 * @return  the screenshot path
	 */
	public String captureScreenshotForFullPage(String screenShotName) {

		Screenshot sh = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500))
				.takeScreenshot(BasePage.getDriver());
		// Copy the element screenshot to desired location
		try {
			String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + screenShotName + ".png";
			ImageIO.write(sh.getImage(), "png", new File(dest));
			return dest;
		} catch (IOException e) {
			LogUtils.debug("An exception occurs while tacking the full page screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

	/**
	 * This method is used to take the screenshot for WebElement
	 * @param screenShotName
	 * @param element
	 * @return the screenshot path
	 */
	public String captureScreenshotForWebElement(String screenShotName, WebElement element) {

		Screenshot sh = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(BasePage.getDriver(),
				element);
		// Copy the element screenshot to desired location
		try {
			String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + screenShotName + ".png";
			ImageIO.write(sh.getImage(), "png", new File(dest));
			return dest;
		} catch (IOException e) {
			LogUtils.debug("An exception occurs while tacking the WebElement screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

}