package rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class JSonProbe {

	@Test
	@SuppressWarnings("unchecked")
	public void example() {
		JSONObject j = new JSONObject();
		j.put("firstName", "John");
		j.put("lastName", "Doe");
		JSONArray array = new JSONArray();
		array.add(j);
		array.add(Map.of("firstName", "Anna", "lastName", "Smith"));
		array.add(Map.of("firstName","Peter", "lastName","Jones"));

		JSONObject result = new JSONObject();
		result.put("employees", array);
		System.out.println(result);
		System.out.println(((Map)((JSONArray)result.get("employees")).get(0)).get("lastName"));
	}


@BeforeClass
public static void setupRestAssured() {
	RestAssured.baseURI = "http://api.football-data.org";
	RestAssured.basePath = "/v4/";
	RequestSpecification requestSpecification = new RequestSpecBuilder().
		addHeader("X-Auth-Token", "f8ac080ca58745b99bc0596ce3a486c8").
		addHeader("X-Response-Control", "minified")
		.build();
	RestAssured.requestSpecification = requestSpecification;
}
	@Test
	public void extractListOfMapOfElementsWithMultipleFindAlls_findAllPlayersOfACertainPositionAndNationality() {
		Response response = get("http://api.football-data.org/v4/teams/2061/");
		String position = "Offence";
		String nationality = "Argentina";
		ArrayList<Map<String, ?>> allPlayersOfCertainPositionAndNationality = response.path(
			"squad.findAll { it.position == '%s' }.findAll { it.nationality == '%s' }.name",
			position, nationality);
		System.out.println(allPlayersOfCertainPositionAndNationality);
	}

	@Test
	public void simple(){
		System.out.println(given()
//			.log().all()
			.when()
//			.log().all()
			.get("/teams/")
			.then()
//			.log().all()
		.extract()
			.body()
//			.path("teams.findAll{it.shortName =~ 'Weh'}")
			.path("teams.findAll{it.founded < 1900}.id")//each here to transform
			//collect to provide example as well
			.toString());
	}
}
