package ui_tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("UI")
public class UITests {
    WebDriver driver;


    @BeforeAll
    public void setup() {
        String browser = System.getenv("BROWSER");
        if (browser == null) {
            browser = System.getProperty("BROWSER");
        }
        System.out.println("Running tests with browser: " + browser);
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
    }


    @AfterAll
    public void quitDriver() {
        driver.quit();
    }


    @Test
    public void testGoogleHomePageTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assertTrue(title.contains("Google"), "Title should contain 'Google'");
    }

    @Test
    public void testSearchBoxIsDisplayed() {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        assertTrue(searchBox.isDisplayed(), "Search box should be visible");
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("OpenAI");
        searchBox.submit();

        // Wait a bit for results to load (simple wait)
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("search"), "URL should contain 'search' after search");
    }
}
