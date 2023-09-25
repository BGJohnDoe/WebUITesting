import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class Rest {
    @DataProvider
    public static Object[][] UIDS() {
        return new Object[][]{{"9781449325862"}, {"9781449325861"}};
    }

    @DataProvider
    public static Object[][] jsons() {
        return new Object[][]{{"src/test/resources/credentials.json"}};
    }

    @Test
    public void checkBook() {
        givenSpec()
                .get("https://demoqa.com/BookStore/v1/Book?ISBN=9781449325862")
                .then()
                .log().all()
                .assertThat()
                .body("pages", equalTo(234))
                .body("publisher", equalTo("O'Reilly Media"));
    }

    private static RequestSpecification givenSpec() {
        return givenSpec().when().log().all();
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
    public void queryParamsTest() {
        given()
                .queryParam("text", "someText")
                .when()
                .get("http://md5.jsontest.com")
                .then()
                .assertThat()
                .body("md5", equalTo("79252c63862212c43dcadddce8f73e5c"));
    }

    @Test(dataProvider = "UIDS")
    public void pathParameterTest(String uuid) {
        given()
                .pathParam("UUID", uuid)
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
                .when()
                .get("https://demoqa.com/Account/v1/Authorized")
                .then()
                .assertThat()
                .statusCode(200);
    }


    @Test
    public void getToken() {
        HashMap<String, String> user = new HashMap<>();
        user.put("username", "admin");
        user.put("password", "password123");

        Token token =
                given()
                        .contentType(ContentType.JSON)
                        .baseUri("https://restful-booker.herokuapp.com")
                    .when()
                        .log().all()
                        .body(user)
                        .post("/auth")
                    .then()
                        .log().all()
                        .extract()
                        .as(Token.class);
        System.out.println(token.getToken());
    }

    @Test (dataProvider = "jsons")
    public void getTokenWithFileCreds(String pathname) {
        Token token =
                given()
                        .spec(getRequestSpecification())
                    .when()
                        .log().all()
                        .body(new File(pathname))
                        .post("/auth")
                    .then()
                        .log().all()
                        .extract()
                        .as(Token.class);
        System.out.println(token.getToken());
    }

    private static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder().setContentType(ContentType.JSON)
                .setBaseUri("https://restful-booker.herokuapp.com").build();
    }

    @Test
    public void oAuthAuthentication() {
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
