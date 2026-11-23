package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By dashboardHeaderLocator = By.xpath("//h6[normalize-space()='Dashboard']");
    private By footerLinkLocator = By.cssSelector("a[href='http://www.orangehrm.com']");
    private By sidebarMenuLocator = By.className("oxd-main-menu-item");

    public boolean isDashboardDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dashboardHeaderLocator)
        ).isDisplayed();
    }
    public OrangeHRMPage clickFooterLink() {
        String originalWindow = driver.getWindowHandle();

        WebElement footerLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(footerLinkLocator)
        );

        new Actions(driver)
                .moveToElement(footerLink)
                .click()
                .perform();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        return new OrangeHRMPage(driver);
    }
    public List<String> getSidebarMenuNames() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sidebarMenuLocator));
        List<String> menuNames = new ArrayList<>();

        for (WebElement item : driver.findElements(sidebarMenuLocator)) {
            menuNames.add(item.getText());
        }

        return menuNames;
    }
}
