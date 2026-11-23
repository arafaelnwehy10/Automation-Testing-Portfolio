package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.AdminPage;
import Utilities.LoginDataProvider;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminTests extends BaseTest {

    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void openAddUserPageTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        Allure.step("Navigate to Admin menu");

        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.clickAdminMenu();

        Allure.step("Click Add User button");
        adminPage.clickAddButton();

        Allure.step("Verify User Role field is displayed");

        Assert.assertTrue(
                adminPage.isUserRoleDisplayed(),
                "User Role field is not displayed"
        );

        Allure.step("Verify Employee Name field is displayed");

        Assert.assertTrue(
                adminPage.isEmployeeNameDisplayed(),
                "Employee Name field is not displayed"
        );

        Allure.step("Verify Username field is displayed");

        Assert.assertTrue(
                adminPage.isUsernameDisplayed(),
                "Username field is not displayed"
        );

        Allure.step("Verify Password field is displayed");

        Assert.assertTrue(
                adminPage.isPasswordDisplayed(),
                "Password field is not displayed"
        );
    }
}