package Atlas;

import io.qameta.atlas.webdriver.extension.Param;
import io.qameta.allure.Step;
import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.matchers.webdriver.DisplayedMatcher;

public class BaseSteps {
	protected final WebDriver driver;
	protected final WebDriverWait wait;
	protected final MainPage mainPage;

	public BaseSteps(WebDriver webDriver) {
		driver = webDriver;
		wait = DriversManager.getDriverWait(driver, 10);
		mainPage = createPage(MainPage.class);
	}

	public <T> T createPage(Class<T> type) {
		return new Atlas(new WebDriverConfiguration(driver)).create(driver, type);
	}

	@Step("Input username {username}")
	public void inputUserName(@Param("username") String username) {
		mainPage.usernameInput().sendKeys(username);
	}

	@Step("Input email {email}")
	public void inputUserEmail(@Param("email") String email) {
		mainPage.emailInput().sendKeys(email);
	}

	@Step("Input current address {address}")
	public void inputCurrentAddress(@Param("address") String address) {
		mainPage.currentAddress().sendKeys(address);
	}

	@Step("Input permanent address {address}")
	public void inputPermanentAddress(@Param("name") String address) {
		mainPage.permanentAddress().sendKeys(address);
	}

	@Step("Press submit button")
	public void pressSubmit() {
		mainPage.submitButton().click();
	}

	@Step("Check that submit button is displayed")
	public void checkSubmitDisplayed(){
		mainPage.submitButton().should("Submit is displayed", DisplayedMatcher.displayed(), 10);
	}
}
