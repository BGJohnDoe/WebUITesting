package Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Output implements Page {

	@FindBy(xpath="//*[@id='name']")
	WebElement name;

	@FindBy(id="email")
	WebElement email;

	@FindBy(xpath="//p[@id='currentAddress']")
	WebElement currentAddress;

	@FindBy(xpath="//p[@id='permanentAddress']")
	WebElement permanentAddress;

	public Output(WebDriver driver) {
		init(driver);
	}

	public void init(final WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getCurrentAddress() {
		return currentAddress.getText().split(":")[1];
	}

	public String getPermanentAddress() {
		return permanentAddress.getText().split(":")[1];
	}

	public String getEmail() {
		return email.getText().split(":")[1];
	}

	public String getName() {
		return name.getText().split(":")[1];
	}
}
