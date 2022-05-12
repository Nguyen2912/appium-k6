package test_flows.authentication;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test_flows.BaseFlow;

public class LoginFlow extends BaseFlow {

    private final AppiumDriver<MobileElement> appiumDriver;
    private String emailStr;
    private String passwordStr;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String emailStr, String passwordStr) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        this.emailStr = emailStr;
        this.passwordStr = passwordStr;
    }

    public void login(){
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComponent();
        if(!emailStr.isEmpty()){
            MobileElement emailElem = loginFormComp.emailElem();
            emailElem.clear();
            emailElem.sendKeys(emailStr);
        }

        if(!passwordStr.isEmpty()){
            MobileElement passwordElem = loginFormComp.passwordElem();
            passwordElem.clear();
            passwordElem.sendKeys(passwordStr);
        }

        loginFormComp.clickOnLoginBtn();
    }

    public void verifyLogin(){
        boolean isEmailValid = EmailValidator.getInstance().isValid(emailStr);
        boolean isPasswordValid = passwordStr.length() >= 8;

        System.out.printf("Email: %s, %b | Password: %s, %b\n", emailStr, isEmailValid, passwordStr, isPasswordValid);

        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComp = loginScreen.loginFormComponent();

        if(isEmailValid && isPasswordValid){
            verifyCorrectLoginCreds(loginScreen);
        }

        if(!isEmailValid){
            verifyIncorrectEmailStr(loginFormComp);
        }

        if(!isPasswordValid){
            verifyIncorrectPasswordStr(loginFormComp);
        }

        System.out.println("\n=====================");
    }

    private void verifyCorrectLoginCreds(LoginScreen loginScreen) {

        String currentMsgTitle = loginScreen.loginDialogComp().getLoginMsgTitle();
        System.out.println("---> currentMsgTitle: " + currentMsgTitle);
        Assert.assertEquals(currentMsgTitle, "Success", "[ERR] Success Login title is incorrect!");
        loginScreen.loginDialogComp().clickOnOKBtn();
    }

    private void verifyIncorrectEmailStr(LoginFormComponent loginFormComp) {
        String actualInvalidEmailStr = loginFormComp.getInvalidEmailStr();
        String expectedInvalidEmailStr = "Please enter a valid email address.";

        // Verification
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualInvalidEmailStr, expectedInvalidEmailStr, "[ERR] Invalid email format str incorrect");
        System.out.println("----------->>>>>>>> After asserting");
        softAssert.assertTrue(false, "[ERR] true !=false");
        softAssert.assertAll();
    }

    private void verifyIncorrectPasswordStr(LoginFormComponent loginFormComp) {
        String actualInvalidPasswordStr = loginFormComp.getInvalidPasswordStr();
        String expectedInvalidEmailStr = "Please enter at least 8 characters.";

        // Verification
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualInvalidPasswordStr, expectedInvalidEmailStr, "[ERR] Invalid email format str incorrect");
        System.out.println("----------->>>>>>>> After asserting");
        softAssert.assertTrue(false, "[ERR] true !=false");
        softAssert.assertAll();
    }
}
