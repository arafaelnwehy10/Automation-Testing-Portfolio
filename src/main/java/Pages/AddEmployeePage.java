package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddEmployeePage {

    private WebDriver driver;
    private WebDriverWait wait;
    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By firstNameLocator = By.name("firstName");
    private By lastNameLocator = By.name("lastName");
    private By saveButtonLocator = By.cssSelector("button[type='submit']");
    private By firstNameRequiredLocator = By.cssSelector(".oxd-input-field-error-message");
    private By validationMessagesLocator = By.cssSelector(".oxd-input-field-error-message");
    private By formLoaderLocator = By.cssSelector(".oxd-form-loader");

    public boolean isFirstNameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameLocator)
        ).isDisplayed();
    }

    public boolean isLastNameDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameLocator)
        ).isDisplayed();
    }
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameLocator))
                .sendKeys(lastName);
    }

    public void clickSave() {
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(formLoaderLocator));
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButtonLocator)).click();
    }
    public String getFirstNameRequiredMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameRequiredLocator)
        ).getText();
    }
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameLocator))
                .sendKeys(firstName);
    }
    public List<String> getValidationMessages() {
        return driver.findElements(validationMessagesLocator)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
    public boolean isPersonalDetailsPageDisplayed() {
        return wait.until(
                ExpectedConditions.urlContains("/pim/viewPersonalDetails")
        );
    }
}