package Cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(plugin = {"pretty", "summary", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
	monochrome = true, glue = {"Cucumber.steps", "cucumberHooks"},// Packages names
	features = {"src/test/resources"}                             // Folder name
)
public class RunnerCucumber extends AbstractTestNGCucumberTests {
	@DataProvider(parallel = true)
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
}