package Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

public class SwipeOverloading {
    public static void swipeUp (AppiumDriver<MobileElement> driver){
        // Get Mobile window size
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();
        // Calculate Touch points
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = 50 * screenWidth / 100;

        int yStartPoint = 50 * screenHeight / 100;
        int yEndPoint = 10 * screenHeight / 100;

        // Convert coordinates -> PointOption
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Using TouchAction to swipe
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(startPoint)
                .moveTo(endPoint)
                .release()
                .perform();
    }
    public static void swipeUp (AppiumDriver<MobileElement> driver,int percent){
        // Get Mobile window size
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();
        // Calculate Touch points
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = 50 * screenWidth / 100;

        int yStartPoint = 50 * screenHeight / 100;
        int yEndPoint = (50-(percent/2)) * screenHeight / 100;

        // Convert coordinates -> PointOption
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Using TouchAction to swipe
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(startPoint)
                .moveTo(endPoint)
                .release()
                .perform();
    }
    public static void swipeDown (AppiumDriver<MobileElement> driver){
        // Get Mobile window size
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();
        // Calculate Touch points
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = 50 * screenWidth / 100;

        int yStartPoint = 50 * screenHeight / 100;
        int yEndPoint = 10 * screenHeight / 100;

        // Convert coordinates -> PointOption
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Using TouchAction to swipe
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(endPoint)
                .moveTo(startPoint)
                .release()
                .perform();
    }
    public static void swipeDown (AppiumDriver<MobileElement> driver,int percent){
        // Get Mobile window size
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();
        // Calculate Touch points
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = 50 * screenWidth / 100;

        int yStartPoint = 50 * screenHeight / 100;
        int yEndPoint = (50-(percent/2)) * screenHeight / 100;

        // Convert coordinates -> PointOption
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Using TouchAction to swipe
        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .longPress(endPoint)
                .moveTo(startPoint)
                .release()
                .perform();
    }


}
