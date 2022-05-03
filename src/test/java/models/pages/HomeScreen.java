package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.BottomNavComponent;
import models.components.LoginFormComponent;

public class HomeScreen {
    private final AppiumDriver<MobileElement> driver;

    public HomeScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public BottomNavComponent bottomNavComponent(){
        return new BottomNavComponent(driver);
    }

}
