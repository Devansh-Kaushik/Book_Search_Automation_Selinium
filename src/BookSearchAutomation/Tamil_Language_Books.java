package BookSearchAutomation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tamil_Language_Books extends DriverSetup {

    public WebDriver driver;
    public String BaseUrl;

    public WebDriver CreateDriver() {
        driver = getWebDriver();
        BaseUrl = "https://openlibrary.org/subjects";
        driver.get(BaseUrl);
        driver.navigate().to("https://openlibrary.org/languages");
        return driver;
    }

    public void Tamil_books(WebDriver driver) {
        try {
            WebElement ul = driver.findElement(By.className("subjectList"));
            List<WebElement> liList = ul.findElements(By.tagName("li"));

            for (int i = 0; i < liList.size(); i++) {
                WebElement li = liList.get(i);
                String langName = li.findElement(By.tagName("a")).getText();
                String bookCount = li.findElement(By.tagName("b")).getText();
                bookCount = bookCount.replaceAll(",", "");
                bookCount = bookCount.replaceAll("books", "");
                if (langName.equalsIgnoreCase("Tamil") && Integer.parseInt(bookCount.trim()) > 1000) {
                    System.out.println();
                    System.out.println(langName + " language has " + bookCount + " books.");
                    System.out.println("Hence, there are over 1000 books in the " + langName + " language.");
                }
            }
        } catch (NoSuchElementException e) {
            // Handle element not found exception
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle number format exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Tamil_Language_Books tlb = new Tamil_Language_Books();
        WebDriver driver = tlb.CreateDriver();
        tlb.Tamil_books(driver);
        driver.quit();
        System.out.println();
        Automation_test.main(args);
    }
}
