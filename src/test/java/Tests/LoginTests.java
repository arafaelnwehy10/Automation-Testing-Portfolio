package Tests;

import Base.BaseTest;
import Pages.DashboardPage;
import Pages.LoginPage;
import Utilities.LoginDataProvider;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void validLoginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Enter valid login credentials");

        DashboardPage dashboardPage = loginPage.login(username, password);

        Allure.step("Verify dashboard is displayed");

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed"
        );

        Allure.step("Verify dashboard URL");

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("/dashboard/index"),
                "URL does not contain /dashboard/index"
        );
    }

    @Test(
            dataProvider = "invalidLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void invalidLoginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Enter invalid username");
        loginPage.enterUsername(username);

        Allure.step("Enter invalid password");
        loginPage.enterPassword(password);

        Allure.step("Click login button");
        loginPage.clickLogin();

        Allure.step("Verify invalid credentials error message");

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials",
                "Error message is incorrect"
        );
    }

    @Test
    public void emptyLoginTest() {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Click login button without entering credentials");
        loginPage.clickLogin();

        Allure.step("Verify required validation messages");

        Assert.assertEquals(
                loginPage.getRequiredMessagesCount(),
                2,
                "Required messages are not displayed for both fields"
        );
    }
}