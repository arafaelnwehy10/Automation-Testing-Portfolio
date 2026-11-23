package Utilities;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {

        String username = JsonDataReader.getTestData(
                "testdata.json", "validLogin", "username"
        );

        String password = JsonDataReader.getTestData(
                "testdata.json", "validLogin", "password"
        );

        return new Object[][]{
                {username, password}
        };
    }
    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {

        String username = JsonDataReader.getTestData(
                "testdata.json", "invalidLogin", "username"
        );

        String password = JsonDataReader.getTestData(
                "testdata.json", "invalidLogin", "password"
        );

        return new Object[][]{
                {username, password}
        };
    }
}
