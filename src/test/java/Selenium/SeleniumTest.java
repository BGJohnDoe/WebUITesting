package Selenium;

import Selenium.pages.Form;
import Selenium.pages.Output;
import Selenium.steps.Steps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;

public class SeleniumTest {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	private void init() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
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

	@AfterMethod(description = "Completion of test")
	protected void testFailure(ITestResult result) {
		if (!result.isSuccess()) //что не так?
			attachScreenshot(driver);
	}

	@Attachment(value = "Screenshot", type = "image/png")
	public static byte[] attachScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	private void waitUntil(By by) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	@Test
	public void textBoxTest() {
		String name = "Igor";
		String email = "bgjdoe@gmail.com";
		String currentAddress = "current address";
		String permanentAddress = "permanent address";
		By submit = By.id("submit");

		connect("https://demoqa.com/text-box");
		waitUntil(submit);

		Form page = new Form(driver);

		page = page.setUserName(name)
			.setEmail(email)
			.setCurrentAddress(currentAddress)
			.setPermanentAddress(permanentAddress)
			.clickSubmit();

		new Steps(page, new Output(driver))
			.checkUserName(name)
			.checkUserEmail(email)
			.checkCurrentAddress(currentAddress)
			.checkPermanentAddress(permanentAddress)
			.checkOutName(name)
			.checkOutEmail(email)
			.checkOutCurrentAddress(currentAddress)
			.checkOutPermanentAddress(permanentAddress);
	}
}
