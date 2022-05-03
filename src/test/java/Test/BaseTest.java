package Test;
import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;

    @BeforeTest(description = "Init appium session")
    public void initAppiumSession() {
        appiumDriver = DriverFactory.getDriver(Platform.android);
    }

    @AfterTest(alwaysRun = true, description = "Quit the appium session")
    public void quitAppiumSession() {
        appiumDriver.quit();
    }

    @AfterMethod(description = "Capture screenshot")
    public void captureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get the test method name
            String testMethodName = result.getName();

            // 2. Get taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String dateTaken = y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec;

            // 3. File location with file extension
            String fileLocation =
                    System.getProperty("user.dir") + "/screenshots/" + testMethodName + "-" + dateTaken + ".png";


            // 4. Save
            File screenshot = appiumDriver.getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenshot, new File(fileLocation));

                // Get file content then attach to Allure reporter
                Path screenshotContentPath = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(screenshotContentPath);
                Allure.addAttachment(testMethodName + "-" + dateTaken, inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
