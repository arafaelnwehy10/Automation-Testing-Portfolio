package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PIMPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By pimMenuLocator = By.xpath("//span[text()='PIM']");
    private By employeeNameSearchLocator = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private By employeeSuggestionLocator = By.xpath("//div[@role='option']//span");
    private By searchButtonLocator = By.xpath("//button[@type='submit']");
    private By employeeResultLocator = By.cssSelector(".oxd-table-card");
    private By noRecordsFoundLocator = By.xpath("//span[text()='No Records Found']");
    private By addButtonLocator = By.xpath("//button[normalize-space()='Add']");
    public void clickPIMMenu() {
        wait.until(
                ExpectedConditions.elementToBeClickable(pimMenuLocator)
        ).click();
    }
    public void searchEmployee(String employeeName) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(employeeNameSearchLocator)
        ).sendKeys(employeeName);
    }
    public void selectEmployeeSuggestion() {
        wait.until(
                ExpectedConditions.elementToBeClickable(employeeSuggestionLocator)
        ).click();
    }
    public void clickSearchButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(searchButtonLocator)
        ).click();
    }
    public List<String> getEmployeeResultsText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeResultLocator));

        return driver.findElements(employeeResultLocator)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
    public String getNoRecordsFoundMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(noRecordsFoundLocator)
        ).getText();
    }
    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButtonLocator)).click();
    }
}
