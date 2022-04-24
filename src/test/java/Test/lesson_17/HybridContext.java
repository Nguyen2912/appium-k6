package Test.lesson_17;
import context.WaitMoreThanOneContext;
import context.Contexts;
import driver.DriverFactory;
import driver.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class HybridContext {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {

            // Navigate to webview screen
            By webviewNavBtnSel = MobileBy.AccessibilityId("Webview");
            MobileElement webviewNavBtnElem = appiumDriver.findElement(webviewNavBtnSel);
            webviewNavBtnElem.click();

            // Wait until we have more than one contexts
            WebDriverWait wait = new WebDriverWait(appiumDriver, 15L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            // Get all context names
//            for (String context : appiumDriver.getContextHandles()) {
//                System.out.println(context);
//            }
            // Switch to web view context
            appiumDriver.context(Contexts.WEB_VIEW);

            // Interact on webview elements
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();
            List<MobileElement> menuItemElems = appiumDriver.findElementsByCssSelector(".menu__list li a");
            List<MenuItemData> menuItemDataList = new ArrayList<MenuItemData>();
            Map<String, String> menuItemDataMap = new HashMap<String, String>();
            if(menuItemElems.isEmpty()){
                throw new RuntimeException("[ERR] There is no list item!!");
            }

            for (MobileElement menuItemElem : menuItemElems) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");
                if(itemText.isEmpty()) {
                    menuItemDataList.add(new MenuItemData("Github", itemHref));
                    menuItemDataMap.put("Github", itemHref);
                }
                else {
                    menuItemDataList.add(new MenuItemData(itemText, itemHref));
                    menuItemDataMap.put(itemText, itemHref);
                }

            }

            menuItemDataList.forEach(menuItemData -> {
                System.out.println(menuItemData.getName() + ": " + menuItemData.getHref());
            });
            System.out.println("============");
            menuItemDataMap.keySet().forEach(key -> {
                System.out.println(key + ": " + menuItemDataMap.get(key));
            });


            // Switch back to NATIVE context
            appiumDriver.context(Contexts.NATIVE);

            // Find and click on nav login button
            MobileElement navLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();

            // Fill the form
            MobileElement usernameElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            usernameElem.sendKeys("nguyenducnguyen0493@gmail.com");
            passwordElem.sendKeys("12345678");
            loginBtnElem.click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
    public static class MenuItemData {
        private String name;
        private String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }
    }
}

