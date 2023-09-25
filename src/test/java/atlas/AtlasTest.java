package atlas;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

	@Test
	public void textBoxTest() {
		String name = "Igor";
		String email = "bgjdoe@gmail.com";
		String currentAddress = "current address";
		String permanentAddress = "permanent address";

		driver.get("https://demoqa.com/text-box");
		steps.checkSubmitDisplayed();

		steps.inputUserName(name);
		steps.inputUserEmail(email);
		steps.inputCurrentAddress(currentAddress);
		steps.inputPermanentAddress(permanentAddress);
		steps.pressSubmit();

		//TODO: let's make it  bit clear?
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(getName(By.id("name")), name);
		sa.assertEquals(getName(By.id("email")), email+"897789");
		sa.assertEquals(getName(By.xpath("//p[@id='currentAddress']")),
			currentAddress);
		sa.assertEquals(getName(By.xpath("//p[@id='permanentAddress']")),
			permanentAddress);
		sa.assertAll();
	}

	private String getName(By name) {
		return driver.findElement(name).getText().split(":")[1];
	}
}