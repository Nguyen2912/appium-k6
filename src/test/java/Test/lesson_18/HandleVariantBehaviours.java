package Test.lesson_18;
import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.Capabilities;
public class HandleVariantBehaviours {
    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            Capabilities caps = appiumDriver.getCapabilities();

            // Get platformName
            String platformName = CapabilityHelpers.getCapability(caps, "platformName", String.class);
            String udid = CapabilityHelpers.getCapability(caps, "udid", String.class);
            String packageName = CapabilityHelpers.getCapability(caps, "appPackage", String.class);
            System.out.println(platformName);
            System.out.println(udid);
            System.out.println(packageName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }

}
