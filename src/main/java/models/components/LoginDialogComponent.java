package models.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.time.Duration;

public class LoginDialogComponent {

    private final AppiumDriver<MobileElement> driver;
    private final static By okBtnSel = MobileBy.xpath("//*[contains(@text, 'OK')]");

    ///////////////////////////////////////////////////////////////////////////////
    //    private final static By msgTitleSel = MobileBy.id("android:id/alertTitle");
//    private final static By msgTitleIOSSel = MobileBy.iOSNsPredicateString("label == \"Success\"");
    ///////////////////////////////////////////////////////////////////////////////
    public LoginDialogComponent(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Success\"")
    private MobileElement msgTitleElem;

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"OK\"")
    private MobileElement okBtnElem;



    public void clickOnOKBtn() {
        okBtnElem.click();
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    public String getLoginMsgTitle(){
//        String loginMsgTitle;
//        Capabilities caps = driver.getCapabilities();
//        String platformName = CapabilityHelpers.getCapability(caps, "platformName", String.class);
//        if(platformName.equalsIgnoreCase("android"))
//            loginMsgTitle =  driver.findElement(msgTitleSel).getText();
//        else
//            loginMsgTitle =  driver.findElement(msgTitleIOSSel).getText();
//
//        return loginMsgTitle;

        return msgTitleElem.getText();
    }
}
