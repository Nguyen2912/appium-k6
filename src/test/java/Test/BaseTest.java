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
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;

    private String udid;
    private String systemPort;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @BeforeTest(description = "Init appium session")
    @Parameters({"udid", "systemPort"})
    public void initAppiumSession(String udid, String systemPort) {
        System.out.println("I'm running before test at: " + new GregorianCalendar().getTime().toString());
        System.out.println(udid + " || " + systemPort);
        this.udid = udid;
        this.systemPort = systemPort;
        driverThread = ThreadLocal.withInitial(()->{
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true, description = "Quit the appium session")
    public void quitAppiumSession() {
        driverThread.get().quitAppiumDriver();
    }
    // khoi tao cho con no dung
    protected AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getDriver(Platform.android, udid, systemPort);
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
            File screenshot = driverThread.get().getDriver(Platform.android, udid, systemPort).getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenshot, new File(fileLocation));

                // Get file content then attach to Allure reporter
                Path screenshotContentPath = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(screenshotContentPath);
                Allure.addAttachment(testMethodName + "-" +dateTaken, inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
