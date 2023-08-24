package TestNGProbe;

import org.testng.annotations.Test;

public class MyTest {

	@Test(groups = {"hello"})
	public void myFirstTest(){
		System.out.println("Hello world");
	}

	@Test(description = "Simple description", testName = "SECOND")
	public void mySecondTest(){
		System.out.println("Out of group, second test");
	}

	@Test(groups = {"hello"})
	public void oneMoreTestInGroup(){
		System.out.println("Hello world again");
		throw new RuntimeException("Fail from oneMoreTestInGroup");
	}

	@Test(enabled = false)
	public void disabledTest(){
		System.out.println("Disabled");
	}
}
