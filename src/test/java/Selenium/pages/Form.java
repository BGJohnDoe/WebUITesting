package Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class Form implements Page {

	public Form(WebDriver driver){
		init(driver);
	}

	@FindBy(xpath="//*[@id='userName']")
	WebElement usernameInput;

	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="currentAddress")
	WebElement currentAddress;

	@FindBy(id="permanentAddress")
	WebElement permanentAddress;

	@FindBy(id="submit")
	WebElement submit;

	public void init(final WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	public Form setUserName(String name){
		usernameInput.sendKeys(name);
		return this;
	}

	public Form setEmail(String email){
		userEmail.sendKeys(email);
		return this;
	}

	public Form setCurrentAddress(String address) {
		currentAddress.sendKeys(address);
		return this;
	}

	public Form setPermanentAddress(String address) {
		permanentAddress.sendKeys(address);
		return this;
	}

	public Form clickSubmit(){
		submit.click();
		return this;
	}

	//Methods for validation of input values
	public String getCurrentAddress() {
		return currentAddress.getAttribute("value");
	}

	public String getUsername() {
		return usernameInput.getAttribute("value");
	}

	public String getUserEmail() {
		return userEmail.getAttribute("value");
	}

	public String getPermanentAddress() {
		return permanentAddress.getAttribute("value");
	}
}
