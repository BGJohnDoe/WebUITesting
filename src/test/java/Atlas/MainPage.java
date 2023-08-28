package Atlas;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;
import org.openqa.selenium.*;

@SuppressWarnings("rawtypes")
public interface MainPage extends WrapsDriver, SearchContext {
	@Name("Username input")
	@FindBy("//*[@id='userName']")
	AtlasWebElement usernameInput();

	@Name("Email input")
	@FindBy("//*[@id='userEmail']")
	AtlasWebElement emailInput();

	@Name("Current address input")
	@FindBy("//*[@id='currentAddress']")
	AtlasWebElement currentAddress();

	@Name("Permanent address input")
	@FindBy("//*[@id='permanentAddress']")
	AtlasWebElement permanentAddress();

	@Name("submit button")
	@FindBy("//*[@id='submit']")
	WebElement submitButton();
}
