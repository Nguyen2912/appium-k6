package Test.lesson_19;

import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.models.LoginCredData;
import test_flows.authentication.LoginFlow;



public class LoginTest {
    @Test(dataProvider = "loginCredData")
    public void testLogin(LoginCredData loginCredData) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            String email = loginCredData.getEmail();
            String password = loginCredData.getPassword();
            LoginFlow loginFlow = new LoginFlow(appiumDriver, email, password);
            loginFlow.gotoLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }

    @DataProvider
    public LoginCredData[] loginCredData() {
        // Build support method to convert from JSON -> Array of Object

        // Return an array of objects
        LoginCredData loginCredData01 = new LoginCredData("teo@", "12345678");
        LoginCredData loginCredData02 = new LoginCredData("teo@sth.xyz", "1234567");
        LoginCredData loginCredData03 = new LoginCredData("teo@sth.com", "12345678");
        return new LoginCredData[]{loginCredData01, loginCredData02, loginCredData03};
    }

}
