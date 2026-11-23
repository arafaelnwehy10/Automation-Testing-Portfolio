package Tests;

import Base.BaseTest;
import Pages.AddEmployeePage;
import Pages.LoginPage;
import Pages.PIMPage;
import Utilities.EmployeeDataProvider;
import Utilities.LoginDataProvider;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PIMTests extends BaseTest {

    @Test(
            dataProvider = "existingEmployeeData",
            dataProviderClass = EmployeeDataProvider.class
    )
    public void searchExistingEmployeeTest(String username, String password, String employeeName) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Login with valid credentials");
        loginPage.login(username, password);

        PIMPage pimPage = new PIMPage(getDriver());

        Allure.step("Navigate to PIM menu");
        pimPage.clickPIMMenu();

        Allure.step("Search for existing employee");
        pimPage.searchEmployee(employeeName);

        Allure.step("Select employee suggestion");
        pimPage.selectEmployeeSuggestion();

        Allure.step("Click search button");
        pimPage.clickSearchButton();

        System.out.println("Employee Result: " + pimPage.getEmployeeResultsText());

        Allure.step("Verify employee search result");
        Assert.assertTrue(
                pimPage.getEmployeeResultsText().toString().contains("Charles"),
                "Employee was not found"
        );
    }

    @Test(
            dataProvider = "nonExistingEmployeeData",
            dataProviderClass = EmployeeDataProvider.class
    )
    public void searchNonExistingEmployeeTest(String username, String password, String employeeName) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Login with valid credentials");
        loginPage.login(username, password);

        PIMPage pimPage = new PIMPage(getDriver());

        Allure.step("Navigate to PIM menu");
        pimPage.clickPIMMenu();

        Allure.step("Search for non-existing employee");
        pimPage.searchEmployee(employeeName);

        Allure.step("Click search button");
        pimPage.clickSearchButton();

        Allure.step("Verify No Records Found message");
        Assert.assertEquals(
                pimPage.getNoRecordsFoundMessage(),
                "No Records Found",
                "No Records Found message is not displayed"
        );
    }

    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void openAddEmployeePageTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Login with valid credentials");
        loginPage.login(username, password);

        PIMPage pimPage = new PIMPage(getDriver());

        Allure.step("Navigate to PIM menu");
        pimPage.clickPIMMenu();

        Allure.step("Click Add Employee button");
        pimPage.clickAddButton();

        Allure.step("Verify Add Employee page URL");
        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("/pim/addEmployee"),
                "Add Employee page URL is incorrect"
        );

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());

        Allure.step("Verify First Name field is displayed");
        Assert.assertTrue(
                addEmployeePage.isFirstNameDisplayed(),
                "First Name field is not displayed"
        );

        Allure.step("Verify Last Name field is displayed");
        Assert.assertTrue(
                addEmployeePage.isLastNameDisplayed(),
                "Last Name field is not displayed"
        );
    }

    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void addEmployeeWithEmptyFirstNameTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Login with valid credentials");
        loginPage.login(username, password);

        PIMPage pimPage = new PIMPage(getDriver());

        Allure.step("Navigate to PIM menu");
        pimPage.clickPIMMenu();

        Allure.step("Click Add Employee button");
        pimPage.clickAddButton();

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());

        Allure.step("Enter last name");
        addEmployeePage.enterLastName("TestLastName");

        Allure.step("Click Save button");
        addEmployeePage.clickSave();

        Allure.step("Verify First Name required message");
        Assert.assertEquals(
                addEmployeePage.getFirstNameRequiredMessage(),
                "Required",
                "Required message is not displayed"
        );
    }

    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void addEmployeeSuccessfullyTest(String username, String password) {

        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Login with valid credentials");
        loginPage.login(username, password);

        PIMPage pimPage = new PIMPage(getDriver());

        Allure.step("Navigate to PIM menu");
        pimPage.clickPIMMenu();

        Allure.step("Click Add Employee button");
        pimPage.clickAddButton();

        String firstName = "mohamed";
        String lastName = "mohamed";

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());

        Allure.step("Enter employee first name");
        addEmployeePage.enterFirstName(firstName);

        Allure.step("Enter employee last name");
        addEmployeePage.enterLastName(lastName);

        Allure.step("Save new employee");
        addEmployeePage.clickSave();

        Allure.step("Verify Personal Details page is displayed");
        Assert.assertTrue(
                addEmployeePage.isPersonalDetailsPageDisplayed(),
                "Personal Details page was not displayed"
        );

        Allure.step("Navigate to PIM menu");
        pimPage.clickPIMMenu();

        Allure.step("Search for the newly added employee");
        pimPage.searchEmployee(firstName + " " + lastName);

        Allure.step("Select employee suggestion");
        pimPage.selectEmployeeSuggestion();

        Allure.step("Click search button");
        pimPage.clickSearchButton();

        Allure.step("Verify new employee is found");
        Assert.assertTrue(
                pimPage.getEmployeeResultsText().toString().contains(firstName),
                "New employee was not found"
        );
    }
}