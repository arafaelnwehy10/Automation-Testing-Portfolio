package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.DashboardPage;
import Pages.OrangeHRMPage;
import Utilities.LoginDataProvider;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterTests extends BaseTest {

    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void verifyFooterLinkTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Login with valid credentials");
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());

        Allure.step("Click footer link");
        OrangeHRMPage orangeHRMPage = dashboardPage.clickFooterLink();

        Allure.step("Verify OrangeHRM website URL");

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("orangehrm.com"),
                "OrangeHRM website URL is incorrect"
        );
    }
}