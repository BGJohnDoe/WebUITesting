import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    private void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterClass
    private void tearDown() {
        driver.quit();
    }


    @Test
    public void properConnect() {
        connect("https://example.org");
    }

    private void connect(String url) {
        driver.get(url);
    }

    @Test
    public void simpleConnect() {
        connect("https://yandex.ru");
        driver.findElement(By.xpath("//button[text()='Найти']")).click();

    }

    @Test(description = "Will failed due to captcha")
    public void connectAndSearch() {
        connect("https://yandex.ru");
        closeCaptcha(driver);
        driver.findElement(By.xpath("//button[text()='Найти']")).click();
    }

    @Step
    @Description("close captcha attempt")
    private void closeCaptcha(WebDriver driver) {
        String captcha = "//input[@aria-labelledby='smartcaptcha-status']";
        waitUntil(By.xpath(captcha));
        driver.findElement(By.xpath(captcha)).click();
        driver.manage().window().maximize();
        //so, what will we do now?
    }

    @AfterMethod(description = "Completion of test")
    protected void testFailure(ITestResult result) {
        if (!result.isSuccess()) //что не так?
            attachScreenshot(driver);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void waitUntil(By xpath) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
    }

    @Test
    public void connectWithWebDriverManager() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(new ChromeOptions());
        driver.get("https://yandex.ru");
    }

}
