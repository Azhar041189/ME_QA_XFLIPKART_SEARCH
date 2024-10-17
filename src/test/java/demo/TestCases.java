package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.concurrent.TimeUnit;
import java.time.Duration;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    static ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @Test
    public void testCase01() {
        try {
            System.out.println("Start testCase01");
            driver.get("http://www.flipkart.com/");
            /*
             * try {
             * WebElement closingPopup =
             * driver.findElement(By.xpath("//span[@class = \"_30XB9F\"]"));
             * closingPopup.click();
             * } catch (Exception e) {
             * // TODO: handle exception
             * System.out.println("Login popup did not appear.");
             * }
             */
            WebElement SearchBar = driver
                    .findElement(By.xpath("//input[@placeholder = \"Search for Products, Brands and More\"]"));
            Wrappers.enterText(SearchBar, "Washing Machine");
            SearchBar.submit();
            WebElement ByPopularity = driver.findElement(By.xpath("//div[contains(text() , \"Popularity\")]"));
            ByPopularity.click();
            Thread.sleep(3000);
            List<WebElement> ratings = driver.findElements(By.xpath("//span[@class= \"Y1HWO0\"]"));
            int count = 0;
            for (WebElement ratingElement : ratings) {
                try {
                    double rating = Double.parseDouble(ratingElement.getText());
                    if (rating <= 4.0) {
                        count++;
                    }
                } catch (NumberFormatException e) {
                    // Ignore elements that do not contain a proper rating
                    e.printStackTrace();
                }
            }

            // Print the count of items with rating <= 4 stars
            System.out.println("Number of items with rating <= 4 stars: " + count);

            System.out.println("End Testcase01");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Test
    public void testCase02() {
        System.out.println("Start testcase02");
        try {
            driver.get("http://www.flipkart.com/");
            WebElement SearchBar = driver
                    .findElement(By.xpath("//input[@placeholder = \"Search for Products, Brands and More\"]"));
            Wrappers.enterText(SearchBar, "iPhone");
            SearchBar.submit();
            List<WebElement> items = driver.findElements(By.xpath("//div[@class = \"_75nlfW\"]"));
            for (WebElement item : items) {
                try {
                    // Get the title of the item
                    WebElement titleElement = item.findElement(By.xpath("//div[@class = \"KzDlHZ\"]"));
                    String title = titleElement.getText();

                    // Get the discount percentage
                    WebElement discountElement = item.findElement(By.xpath(".//div[@class = \"UkUFwK\"]/span"));
                    String discountText = discountElement.getText(); // e.g., "20% off"
                    int discountPercentage = Integer.parseInt(discountText.replaceAll("[^\\d]", ""));

                    // Print the title and discount if discount is more than 17%
                    if (discountPercentage > 17) {
                        System.out.println("Title: " + title + ", Discount: " + discountPercentage + "%");
                    }

                } catch (Exception e) {
                    // Ignore items that do not have the required elements
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("End testcase02");
    }

    
    @Test
    public void testCase03(){
        System.out.println("Start testcase03");
        try {
            driver.get("http://www.flipkart.com/");
            WebElement SearchBar = driver
                    .findElement(By.xpath("//input[@placeholder = \"Search for Products, Brands and More\"]"));
            Wrappers.enterText(SearchBar, "Coffee Mug");
            SearchBar.submit();
            
            WebElement fourStarFilter = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class = \"XqNaEv\"])[1]")));
            fourStarFilter.click();
            /*WebElement fourStarFilter = driver.findElement(By.xpath("(//div[@class = \"XqNaEv\"])[1]"));
            fourStarFilter.click();*/

            // Get the list of items of most review
            List<WebElement> items = driver.findElements(By.xpath("//div[@class='slAVV4']"));
            System.out.println("Top 5 Coffee Mugs with 4star & above:");
            for (int i = 0; i < 5 && i < items.size(); i++) {
                WebElement item = items.get(i);
                String title = item.findElement(By.xpath("//a[@class='wjcEIp']")).getText();
                String imageUrl = item.findElement(By.xpath("//img[@class='DByuf4']")).getAttribute("src");

                System.out.println((i + 1) + ". Title: " + title);
                System.out.println("   Image URL: " + imageUrl);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("End testcase03");
    }

    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}