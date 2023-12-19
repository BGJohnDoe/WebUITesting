package rest;

import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Rest {

	private final HashMap<String, String> userData = createUserData();
	private UserResponse response;
	private Token token;

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
	public void checkAllBooks() {
		var response = (
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
				.extract()
				.response()
		);
		System.out.println(response.jsonPath().get("books.title").toString());
	}

	@Test
	public void checkBooksToList() {
		List<BookModel> books =
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
				.extract()
				.body()
				.jsonPath()
				.getList("books", BookModel.class);

		System.out.println(books);
	}

	@Test
	public void queryParamsTest() {
		given()
			.queryParam("text", "someText")
			.when()
			.get("http://md5.jsontest.com")
			.then()
			.assertThat()
			.body("md5", equalTo("79252c63862212c43dcadddce8f73e5c"));
	}

	@Test
	public void pathParameterTest() {
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
	public void authentication() {
		given()
			.auth()
			.preemptive()
			.basic("user", "Pa$$w0rd")
			.log().all()
			.when()
			.get("https://demoqa.com/Account/v1/Authorized")
			.then()
			.log().all()
			.assertThat()
			.statusCode(200);
	}

	@Test
	public void oAuthAuthentication() {
		given()
			.auth()
			.oauth2(
				"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
				+ ".eyJ1c2VyTmFtZSI6InVzZXIiLCJwYXNzd29yZCI6IlBhJCR3MHJkIiwiaWF0IjoxNjk1MDQ4NDkyfQ"
				+ ".UWVHkB2qTdnYQKQaqbV4Acq7iZNkhDyHDogK9fVcoOM")
			.log().all()
			.when()
			.get("https://demoqa.com/Account/v1/Authorized")
			.then()
			.log().all()
			.assertThat()
			.statusCode(200);
	}

	@Test
	public void login() {
		response = given()
			.contentType(ContentType.JSON)
			.body(userData)
			.log().all()
			.when()
			.log().all()
			.post("https://demoqa.com/Account/v1/Login")
			.then()
			.assertThat()
			.statusCode(200)
			.extract()
			.as(UserResponse.class);
		System.out.println(response);
	}

	@Test
	public void getProfile() {
		given()
			.log().all()
			.baseUri("https://demoqa.com/Account/v1/User")
			.pathParam("UUID", response.getUserId())
			.contentType(ContentType.JSON)
			.auth()
			.oauth2(response.token)
			.when()
			.get("/{UUID}")
			.then()
			.log().all()
			.assertThat()
			.statusCode(200);
	}

	@Test
	public void registerUser() {
		RegisteredUsesResponse newUser = given()
			.log().all()
			.contentType(ContentType.JSON)
			.body(userData)
			.when()
			.post("https://demoqa.com/Account/v1/User")
			.then()
			.log().all()
			.assertThat()
			.statusCode(201)
			.extract()
			.as(RegisteredUsesResponse.class);

		System.out.println(newUser);
	}

	private HashMap<String, String> createUserData() {
		HashMap<String, String> user = new HashMap<>();
		user.put("userName", "user" + RandomStringUtils.random(5, true, true));
		user.put("password", "Pa$$w0rd");
		return user;
	}

	@Test(enabled = false)
	public void testAuth() {
		HashMap<String, String> user = new HashMap<>();
		user.put("username", "admin");
		user.put("password", "password123");
		System.out.println(
			given()
				.contentType(ContentType.JSON)
				.baseUri("https://restful-booker.herokuapp.com")
				.body(user)
//			.body("{\"username\": \"admin\", \"password\": \"password123\"}")
				.when()
				.log().all()
				.post("/auth")
				.then()
				.log().all()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(Token.class).getToken());
//			.path("token").toString());
	}

	@Test
	public void generateToken() {
		token = given()
			.log().all()
			.contentType(ContentType.JSON)
			.body(userData)
			.post("https://demoqa.com/Account/v1/GenerateToken")
			.then()
			.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
			.extract()
			.as(Token.class);
	}
}
