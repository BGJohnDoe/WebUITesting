package Cucumber.steps;

import Cucumber.Context;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

public class MyStepDefinitions {
	@Given("User login in as {string} and {string}")
	public void login(String user, String password){
		System.out.printf("User logged in as '%s'/'%s'", user, password);
		System.out.println();

	}

	@When("User is logged")
	public void user_is_logged() {
		System.out.println("\tuser is logged");
	}
	@Then("do nothing")
	public void do_nothing() {
		System.out.println("\tNothing to do");
	}

	@Before
	public void before(Scenario scenario){
		Context.prepare(scenario);
	}

}
