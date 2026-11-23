package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.DashboardPage;
import Utilities.LoginDataProvider;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SidebarTests extends BaseTest {

    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void verifySidebarMenuItemsTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Login with valid credentials");
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());

        Allure.step("Verify sidebar menu items");

        Assert.assertTrue(
                dashboardPage.getSidebarMenuNames().containsAll(
                        List.of(
                                "Admin",
                                "PIM",
                                "Leave",
                                "Time",
                                "Recruitment",
                                "My Info",
                                "Performance",
                                "Dashboard",
                                "Directory"
                        )
                ),
                "Some sidebar menu items are missing. Found: "

        );
    }
}