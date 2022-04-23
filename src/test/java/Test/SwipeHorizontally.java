package Test;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwipeHorizontally {
    public static void main(String[] args) {
        // Get appium driver
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platform.android);
        try {
            // Find and click on nav login button
            MobileElement navLoginBtnElem = driver.findElement(MobileBy.AccessibilityId("Swipe"));
            navLoginBtnElem.click();

            // Wait until on form screen
            WebDriverWait wait = new WebDriverWait(driver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    MobileBy.xpath("//android.widget.TextView[contains(@text, \"Swipe horizontal\")]")));

            // Get Mobile window size
            Dimension windowSize = driver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate Touch points
            int xStartPoint = 70 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;

            int yStartPoint = 70 * screenHeight / 100;
            int yEndPoint = 70 * screenHeight / 100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Using TouchAction to swipe from right to left 5 times
            TouchAction touchAction = new TouchAction(driver);
            for (int i = 0; i < 5; i++) {
                touchAction
                        .longPress(startPoint)
                        .moveTo(endPoint)
                        .release()
                        .perform();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
