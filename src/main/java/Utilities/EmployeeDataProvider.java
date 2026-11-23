package Utilities;

import org.testng.annotations.DataProvider;

public class EmployeeDataProvider {

    @DataProvider(name = "existingEmployeeData")
    public static Object[][] existingEmployeeData() {

        String username = JsonDataReader.getTestData(
                "testdata.json", "validLogin", "username"
        );

        String password = JsonDataReader.getTestData(
                "testdata.json", "validLogin", "password"
        );

        String employeeName = JsonDataReader.getTestData(
                "testdata.json", "employeeData", "existingEmployee"
        );

        return new Object[][]{
                {username, password, employeeName}
        };
    }

    @DataProvider(name = "nonExistingEmployeeData")
    public static Object[][] nonExistingEmployeeData() {

        String username = JsonDataReader.getTestData(
                "testdata.json", "validLogin", "username"
        );

        String password = JsonDataReader.getTestData(
                "testdata.json", "validLogin", "password"
        );

        String employeeName = JsonDataReader.getTestData(
                "testdata.json", "employeeData", "nonExistingEmployee"
        );

        return new Object[][]{
                {username, password, employeeName}
        };
    }
}