package Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class LauchApp {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = null;
        Exception exception = null;
        try {
            //Design Capabilities

            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            desiredCaps.setCapability("platformName", "Android");
            desiredCaps.setCapability("automationName", "uiautomator2");
            desiredCaps.setCapability("udid", "R28M804D20E");
            desiredCaps.setCapability("appPackage", "com.wdiodemoapp");
            desiredCaps.setCapability("appActivity", ".MainActivity");

            //Init appium sesion

            URL appiumServer = new URL("http://localhost:4723/wd/hub");// connect to APPIUM SERVER
            appiumDriver = new AndroidDriver<MobileElement>(appiumServer,desiredCaps);// Open APP

            //DEBUG PURPOSE ONLY

            Thread.sleep(3000);

        }catch (Exception e){
            exception =  e;
        }
        if(appiumDriver == null){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
