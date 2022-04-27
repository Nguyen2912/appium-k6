package models.components;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginFormComponent {
    private final AppiumDriver<MobileElement> driver;
    private final static By emailElem = MobileBy.AccessibilityId("input-email");
    private final static By incorrectEmailTxtSel =
            MobileBy.xpath("//*[contains(@text, 'Please enter a valid email address')]");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By incorrectPasswordTxtSel =
            MobileBy.xpath("//*[contains(@text, 'Please enter at least 8 characters')]");

    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponent(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public MobileElement emailElem() {
        return driver.findElement(emailElem);
    }

    public MobileElement passwordElem() {
        return driver.findElement(passwordSel);
    }

    public MobileElement loginBtnElem() {
        return driver.findElement(loginBtnSel);
    }

    public void inputUsername(String username) {
        if (!username.isEmpty()) {
            emailElem().sendKeys(username);
        }
    }

    public void inputPassword(String password) {
        if (!password.isEmpty()) {
            passwordElem().sendKeys(password);
        }
    }

    public String getInvalidEmailStr(){
        return driver.findElement(incorrectEmailTxtSel).getText().trim();
    }

    public String getInvalidPasswordStr(){
        return driver.findElement(incorrectPasswordTxtSel).getText().trim();
    }

       public void clickOnLoginBtn() {
        loginBtnElem().click();
    }
}
