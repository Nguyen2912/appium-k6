package Test.form;

import org.testng.annotations.Test;
import Test.BaseTest;
import test_flows.form.FormFlow;

public class FormTest extends BaseTest {

    @Test
    public void testFormInput() {
        System.out.println("--> Session ID: " + appiumDriver.getSessionId());
        FormFlow formFlow = new FormFlow(appiumDriver);
        formFlow.gotoFormScreen();
        formFlow.fillTheForm();
        formFlow.verifyFormDisplay();
    }
}
