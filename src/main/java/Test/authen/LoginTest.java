package Test.authen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCredData;
import test_flows.authentication.LoginFlow;
import Test.BaseTest;


public class LoginTest extends BaseTest {
    @Test(dataProvider = "loginCredData")
    public void testLogin(LoginCredData loginCredData) {
            String email = loginCredData.getEmail();
            String password = loginCredData.getPassword();
            LoginFlow loginFlow = new LoginFlow(getDriver(), email, password);
            loginFlow.gotoLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
    }

    @DataProvider
    public LoginCredData[] loginCredData() {
        String filePath = "/src/test/java/test_data/authen/LoginCreds.json";
        return DataObjectBuilder.buildDataObject(filePath, LoginCredData[].class);
    }
}
