import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Rest {
	@Test
	public void checkBook() {
		given()
			.when()
			.log().all()
			.get("https://demoqa.com/BookStore/v1/Book?ISBN=9781449325862")
			.then()
			.log().all()
			.assertThat()
			.body("pages", equalTo(234))
			.body("publisher", equalTo("O'Reilly Media"));
	}

	@Test
	public void checkBooks() {
		given()
			.when()
			.log().all()
			.get("https://demoqa.com/BookStore/v1/Books")
			.then()
			.log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.contentType(ContentType.JSON)
			.body("books[0].'pages'", equalTo(234))
			.body("books[0].'publisher'", equalTo("O'Reilly Media"));
	}

	@Test
	public void queryParamsTest(){
		given()
			.queryParam("text", "someText")
			.when()
			.get("http://md5.jsontest.com")
			.then()
			.assertThat()
			.body("md5", equalTo("79252c63862212c43dcadddce8f73e5c"));
	}

	@Test
	public void pathParameterTest(){
		given()
			.pathParam("UUID", "c66189e1-908c-47ce-bdac-cb90574cb67a")
		.when()
			.log().all()
			.get("https://demoqa.com/Account/v1/User/{UUID}")
		.then()
			.log().all()
			.assertThat()
			.statusCode(401);
	}

	@Test
	public void authentication(){
		given()
			.auth()
			.preemptive()
			.basic("user", "Pa$$w0rd")
		.when()
			.get("https://demoqa.com/Account/v1/Authorized")
		.then()
			.assertThat()
			.statusCode(200);
	}

	@Test
	public void oAuthAuthentication(){
		given()
			.auth()
			.oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InVzZXIiLCJwYXNzd29yZCI6IlBhJCR3MHJkIiwiaWF0IjoxNjk1MDQ4NDkyfQ.UWVHkB2qTdnYQKQaqbV4Acq7iZNkhDyHDogK9fVcoOM")
		.when()
			.get("https://demoqa.com/Account/v1/Authorized")
		.then()
			.assertThat()
			.statusCode(200);
	}
}
