package atlas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriversManager {

	public static WebDriverWait getDriverWait(WebDriver webDriver, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeOutInSeconds));
		wait.ignoring(org.openqa.selenium.NoSuchElementException.class);
		return wait;
	}

}
