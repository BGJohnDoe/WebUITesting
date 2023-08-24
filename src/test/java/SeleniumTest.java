import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTest {
	@Test
	public void properConnect() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://yandex.ru");
		driver.quit();
	}

	@Test
	public void simpleConnect() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://yandex.ru");
		driver.quit();
		WebElement element = driver.findElement(By.xpath("//button[text()='Найти']"));
		element.click();

	}

	@Test(description = "Will failed due to captcha")
	public void connectAndSearch() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://yandex.ru");
		closeCaptcha(driver);
		WebElement element = driver.findElement(By.xpath("//button[text()='Найти']"));
		element.click();
		driver.quit();
	}

	private void closeCaptcha(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String captcha = "//input[@aria-labelledby='smartcaptcha-status']";
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(captcha)));
			driver.findElement(By.xpath(captcha)).click();
			//so, what will we do now?
	}

	@Test
	public void connectWithWebDriverManager() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(new ChromeOptions());
		driver.get("https://yandex.ru");
		driver.quit();
	}

}
