package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }
    private By usernameLocator = By.name("username");
    private By passwordLocator = By.name("password");
    private By loginButtonloctor = By.cssSelector("button[type='submit']");
    private By errorMessageLocator = By.cssSelector(".oxd-alert-content-text");
    private By requiredMessagesLocator = By.cssSelector(".oxd-input-field-error-message");

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator))
                .sendKeys(username);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator))
                .sendKeys(password);
    }
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonloctor))
                .click();
    }
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
        return new DashboardPage(driver);
    }
    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)
        ).getText();
    }
    public int getRequiredMessagesCount() {
        return wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        requiredMessagesLocator, 2
                )
        ).size();
    }
}
