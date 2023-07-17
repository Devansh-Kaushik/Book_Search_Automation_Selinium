package BookSearchAutomation;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Listing_Category_count extends DriverSetup {

    public WebDriver driver;
    public String BaseUrl;
    public int Total_count = 0;
    public String sub_topics;
    public TreeMap<String, Map<String, Integer>> bookdata;
    public TreeMap<String, Integer> inner;

    public WebDriver CreateDriver() {
        driver = getWebDriver();
        BaseUrl = "https://www.openlibrary.org/";
        driver.get(BaseUrl);
        driver.navigate().to("https://openlibrary.org/subjects");
        return driver;
    }

    public Map<String, Map<String, Integer>> ListingCategory(WebDriver driver) {
        bookdata = new TreeMap<>();
        WebElement parentElement = driver.findElement(By.id("subjectsPage"));
        List<WebElement> listElements = parentElement.findElements(By.tagName("ul"));
        System.out.println();
        System.out.print("Fetching data: ");
        for (int i = 0; i < 1; i++) {
            System.out.print((i + 1) + " ");
            inner = new TreeMap<>();
            WebElement list = listElements.get(i);
            List<WebElement> liElements = list.findElements(By.tagName("li"));
            String category = parentElement.findElement(By.xpath("//*[@id=\"subjectsPage\"]/h3[" + (i + 1) + "]"))
                    .getText();
            Total_count = 0;
            for (int j = 0; j < liElements.size(); j++) {
                WebElement item = liElements.get(j);
                try {
                    String booksurl = BaseUrl + "/search?subject=" + item.getText().toString();
                    driver.navigate().to(booksurl);
                    WebElement countcss = driver.findElement(By.className("search-results-stats"));
                    String count = countcss.getText().trim().split(" ")[0];
                    count = count.replace(",", "");
                    Total_count += Integer.parseInt(count);
                    driver.navigate().back();
                    inner.put(item.getText(), Total_count);
                } catch (NoSuchElementException e) {
                    // Handle element not found exception
                    e.printStackTrace();
                    // Additional error handling or recovery steps
                }
            }
            bookdata.put(category, inner);
        }
        return bookdata;
    }

    public static void main(String[] args) {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        Listing_Category_count bsa = new Listing_Category_count();
        WebDriver driver = bsa.CreateDriver();
        TreeMap<String, Map<String, Integer>> book = (TreeMap<String, Map<String, Integer>>) bsa.ListingCategory(driver);

        System.out.println();
        for (Map.Entry<String, Map<String, Integer>> entry : book.entrySet()) {
            String category = entry.getKey();
            Map<String, Integer> innerMap = entry.getValue();
            System.out.println("Category: " + category);
            System.out.println(innerMap);
            System.out.println();
        }
        
        driver.quit();
        Automation_test.main(args);
    }
}
