package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By userRoleLocator = By.className("oxd-select-text");
    private By employeeNameLocator = By.cssSelector("input[placeholder='Type for hints...']");
    private By usernameLocator = By.xpath("//label[text()='Username']/../following-sibling::div//input");
    private By passwordLocator = By.cssSelector(".user-password-cell input[type='password']");
    private By adminMenuLocator = By.linkText("Admin");
    private By addButtonLocator = By.xpath("//button[normalize-space()='Add']");

    public boolean isUserRoleDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(userRoleLocator)
        ).isDisplayed();
    }

    public boolean isEmployeeNameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(employeeNameLocator)
        ).isDisplayed();
    }

    public boolean isUsernameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameLocator)
        ).isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordLocator)
        ).isDisplayed();

    }
    public void clickAdminMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(adminMenuLocator)).click();
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButtonLocator)).click();
    }
}
