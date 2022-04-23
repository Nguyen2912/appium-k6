package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements MobileCapabilityTypeEx {

    public static AppiumDriver<MobileElement> getDriver(Platform platform) {

        if (platform == null) {
            throw new IllegalArgumentException("Platform can't be null, you can provide one of these: "
                    + Arrays.toString(Platform.values()));
        }

        AppiumDriver<MobileElement> appiumDriver = null;
        Exception exception = null;

        try {
            // Desired Capabilities
            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            desiredCaps.setCapability(PLATFORM_NAME, "Android");
            desiredCaps.setCapability(AUTOMATION_NAME, "uiautomator2");
            desiredCaps.setCapability(UDID, "R28M804D20E");
            desiredCaps.setCapability(APP_PACKAGE, "com.wdiodemoapp");
            desiredCaps.setCapability(APP_ACTIVITY, ".MainActivity");

            // Init appium session
            URL appiumServer = new URL("http://localhost:4723/wd/hub");

            switch (platform) {
                case android:
                    appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCaps);// connect to APPIUM SERVER and Open APP
                    break;
                case ios:
                    appiumDriver = new IOSDriver<MobileElement>(appiumServer, desiredCaps);
            }

        } catch (Exception e) {
            exception = e;
        }

        if (appiumDriver == null) {
            throw new RuntimeException(exception.getMessage());
        }

        // Add IMPLICIT WAIT HERE - try to do find in the time setup
        appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        return appiumDriver;
    }
}
