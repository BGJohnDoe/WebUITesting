package Atlas;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AtlasTest {
	private BaseSteps steps;

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	private void init() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("remote-allow-origins=*");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		steps = new BaseSteps(driver);
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

		driver.get("https://demoqa.com/text-box");
		waitUntil(submit);

		steps.inputUserName(name);
		steps.inputUserEmail(email);
		steps.inputCurrentAddress(currentAddress);
		steps.inputPermanentAddress(permanentAddress);
		steps.pressSubmit();

		//TODO: let's make it  bit clear?
		Assert.assertEquals(driver.findElement(By.id("name")).getText().split(":")[1], name);
		Assert.assertEquals(driver.findElement(By.id("email")).getText().split(":")[1], email);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='currentAddress']")).getText().split(":")[1],
			currentAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText().split(":")[1],
			permanentAddress);
	}
}