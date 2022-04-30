package Test.lesson_20;
import com.google.gson.Gson;
import test_data.models.LoginCredData;

public class TestGSON {
    public static void main(String[] args) {
        // Convert from Object to JSON
        LoginCredData loginCredData = new LoginCredData("Teo", "teo@sth.com");
        Gson gson = new Gson();
        System.out.println(gson.toJson(loginCredData));

        // Convert from JSON to Object
        String loginCredJSONData = "{\"email\":\"Teo\",\"password\":\"teo@sth.com\"}";
        LoginCredData convertedFromJson = gson.fromJson(loginCredJSONData, LoginCredData.class);
        System.out.println(convertedFromJson);
    }
}
