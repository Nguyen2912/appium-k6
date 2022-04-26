package Test;
import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Lab_16_3 {
    public static void main(String[] args) {
        // Get appium driver
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platform.android);
        try {
            // Find and click on nav login button
            MobileElement navLoginBtnElem = driver.findElement(MobileBy.AccessibilityId("Forms"));
            navLoginBtnElem.click();

            // Wait until on form screen
            WebDriverWait wait = new WebDriverWait(driver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    MobileBy.xpath("//android.widget.TextView[contains(@text, \"Form components\")]")));

            //SWIPE
            SwipeOverloading.swipeUp(driver);
            SwipeOverloading.swipeDown(driver);
            SwipeOverloading.swipeUp(driver,50);
            SwipeOverloading.swipeDown(driver,50);

            //Input field
            MobileElement inputfield = driver.findElement(MobileBy.AccessibilityId("text-input"));
            inputfield.sendKeys("nguyenducnguyen");
            // Switch
            MobileElement switchButton = driver.findElement(MobileBy.AccessibilityId("switch"));
            String wifiStatusStr = switchButton.getText().trim();
            boolean isSwitchOff = wifiStatusStr.equalsIgnoreCase("off");
            if(isSwitchOff) switchButton.click();
            // Dropdown
            MobileElement dropdown = driver.findElement(MobileBy.AccessibilityId("Dropdown"));
            dropdown.click();

            List<MobileElement> Itemclicks = driver.findElements(MobileBy.id("android:id/text1"));
            final int PASSWORD_INDEX = 1;
            Itemclicks.get(PASSWORD_INDEX).click();

            // Click on active button
            driver.findElement(MobileBy.AccessibilityId("button-Active")).click();
            // Click OK
            driver.findElement(MobileBy.id("android:id/button1")).click();
            //DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
