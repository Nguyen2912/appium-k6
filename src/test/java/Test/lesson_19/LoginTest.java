package Test.lesson_19;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import test_flows.authentication.LoginFlow;

import java.util.HashMap;
import java.util.Map;

public class LoginTest {
    @Test
    public void TestLogin() {


        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        Map<String, String> credData = new HashMap<>();
        credData.put("teo@", "12345678");
        credData.put("teo@sth", "1234567");
        credData.put("teo@sth.com", "12345678");

        try {
            for (String email : credData.keySet()) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver, email, credData.get(email));
                loginFlow.gotoLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }

}
