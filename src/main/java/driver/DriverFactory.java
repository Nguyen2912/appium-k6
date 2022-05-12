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

    private AppiumDriver<MobileElement> appiumDriver;

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
            //desiredCaps.setCapability(UDID, "emulator-5554");
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



    public AppiumDriver<MobileElement> getDriver(Platform platform, String udid, String systemPort, String platformVersion) {

        if (appiumDriver == null) {
            if (platform == null) {
                throw new IllegalArgumentException("Platform can't be null, you can provide one of these: "
                        + Arrays.toString(Platform.values()));
            }

            Exception exception = null;

            try {
                // Desired Capabilities
                DesiredCapabilities desiredCaps = new DesiredCapabilities();
                desiredCaps.setCapability(PLATFORM_NAME, platform);


                // Init appium session
                //URL appiumServer = new URL("http://localhost:4723/wd/hub");// for run in one server
                URL targetServer = new URL("http://192.168.1.14:4444/wd/hub"); //for run in many server

                switch (platform) {
                    case android:
                        desiredCaps.setCapability(AUTOMATION_NAME, "uiautomator2");
                        desiredCaps.setCapability(UDID, udid);
                        desiredCaps.setCapability(APP_PACKAGE, "com.wdiodemoapp");
                        desiredCaps.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
                        desiredCaps.setCapability(SYSTEM_PORT, Integer.parseInt(systemPort));
                        appiumDriver = new AndroidDriver<MobileElement>(targetServer, desiredCaps);
                        break;
                    case ios:
                        desiredCaps.setCapability(AUTOMATION_NAME, "XCUITest");
                        desiredCaps.setCapability(DEVICE_NAME, udid);
                        desiredCaps.setCapability(PLATFORM_VERSION, platformVersion); // 15.1 NOT 15.1.2
                        desiredCaps.setCapability(BUNDLE_ID, "org.wdioNativeDemoApp");
                        desiredCaps.setCapability(WDA_LOCAL_PORT, Integer.parseInt(systemPort));
                        appiumDriver = new IOSDriver<>(targetServer, desiredCaps);
                        appiumDriver = new IOSDriver<MobileElement>(targetServer, desiredCaps);
                }

            } catch (Exception e) {
                exception = e;
            }

            if (appiumDriver == null) {
                throw new RuntimeException(exception.getMessage());
            }

            // Add IMPLICIT WAIT HERE
            appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        }
        return appiumDriver;
    }

    public void quitAppiumDriver(){
        if (appiumDriver != null){
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
