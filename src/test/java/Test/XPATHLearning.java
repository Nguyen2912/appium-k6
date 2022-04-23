package Test;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class XPATHLearning {
    public static void main(String[] args) {
        // Get appium driver
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platform.android);

        try {
            MobileElement navLoginBtnElem = driver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();
            // Find all matching Elements
            List<MobileElement> credInputFieldElems = driver.findElements(MobileBy.xpath("//android.widget.EditText"));
            final int USER_NAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            credInputFieldElems.get(USER_NAME_INDEX).sendKeys("teso@sth.com");
            credInputFieldElems.get(PASSWORD_INDEX).sendKeys("12345678");

            MobileElement loginInstructionElem =
                    driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));

            System.out.println(loginInstructionElem.getText());


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // Quit appium session
            driver.quit();
        }
    }

}
