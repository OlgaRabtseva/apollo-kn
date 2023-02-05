package test;

import java.nio.file.Paths;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobject.BasePage;

public class TestBase {

    private static WebDriver driver;
    protected BasePage basePage;
    public static String URL = "https://demoqa.com/";
    public static String LOGIN_URL = URL + "login";
    public static String USER_NAME = "Piola";
    public static String PASS = "bBG5xsH!";

    protected static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void startTest() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/chromedriver_win32/chromedriver.exe").toString());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        basePage = new BasePage(driver);
        driver.get(LOGIN_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
