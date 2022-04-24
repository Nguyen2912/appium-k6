package Test.lesson_18;
import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreen;
public class LoginScreenPOM_01 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platform.android);

        try {
            LoginScreen loginScreen = new LoginScreen(driver);

            // Find and click on nav login button
            loginScreen.bottomNavComponent().clickOnLoginIcon();

            // Fill the form
            loginScreen.loginFormComponent().inputUsername("nguyenducnguyen0493@gmail.com");
            loginScreen.loginFormComponent().inputPassword("12345678");
            loginScreen.loginFormComponent().clickOnLoginBtn();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
