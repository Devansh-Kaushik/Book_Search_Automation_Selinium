package BookSearchAutomation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Direct_Listing_Category_Count extends DriverSetup {
    public WebDriver driver;
    public String BaseUrl;

    public WebDriver CreateDriver() {
        driver = getWebDriver();
        BaseUrl = "https://www.openlibrary.org/";
        driver.get(BaseUrl);

        return driver;
    }

    public void DirectListingCategoryCount(WebDriver driver) {
        try {
            WebElement carousel = driver.findElement(By.cssSelector("#categories_carousel"));
            WebElement nextButton = driver.findElement(By.cssSelector("#categories_carousel > button.slick-next.slick-arrow"));
            List<WebElement> categoryTitles = carousel.findElements(By.className("category-title"));
            List<WebElement> TotalBooks = carousel.findElements(By.className("category-count"));

            while (nextButton.isEnabled()) {
                nextButton.click();
                for (int i = 0; i < categoryTitles.size(); i++) {
                    String titleText = categoryTitles.get(i).getText().trim();
                    String bookCount = TotalBooks.get(i).getText().trim();
                    if (!titleText.isEmpty()) {
                        System.out.println("Category Title: " + titleText);
                        System.out.println("Total Books: " + bookCount);
                        System.out.println();
                    }
                }
                String ariaDisabled = nextButton.getAttribute("aria-disabled");
                if (ariaDisabled != null && ariaDisabled.equalsIgnoreCase("true")) {
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            // Handle element not found exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Direct_Listing_Category_Count bsa = new Direct_Listing_Category_Count();
        WebDriver driver = bsa.CreateDriver();
        bsa.DirectListingCategoryCount(driver);
        driver.quit();
        Automation_test.main(args);
    }
}
