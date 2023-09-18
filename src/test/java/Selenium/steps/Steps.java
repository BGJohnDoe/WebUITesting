package Selenium.steps;

import Selenium.pages.Form;
import Selenium.pages.Output;
import org.testng.Assert;

public class Steps {
	private final Form form;
	private final Output output;

	public Steps(Form form, Output output) {
		this.form = form;
		this.output = output;
	}

	public Steps checkUserName(String userName) {
		Assert.assertEquals(form.getUsername(), userName);
		return this;
	}

	public Steps checkUserEmail(String userEmail) {
		Assert.assertEquals(form.getUserEmail(), userEmail);
		return this;
	}

	public Steps checkCurrentAddress(String address) {
		Assert.assertEquals(form.getCurrentAddress(), address);
		return this;
	}

	public Steps checkPermanentAddress(String address) {
		Assert.assertEquals(form.getPermanentAddress(), address);
		return this;
	}

	public Steps checkOutPermanentAddress(String address) {
		Assert.assertEquals(output.getPermanentAddress(), address);
		return this;
	}

	public Steps checkOutCurrentAddress(String address) {
		Assert.assertEquals(output.getCurrentAddress(), address);
		return this;
	}

	public Steps checkOutName(String name) {
		Assert.assertEquals(output.getName(), name);
		return this;
	}

	public Steps checkOutEmail(String email) {
		Assert.assertEquals(output.getEmail(), email);
		return this;
	}

}
