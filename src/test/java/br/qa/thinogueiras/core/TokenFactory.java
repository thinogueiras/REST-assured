package br.qa.thinogueiras.core;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import java.util.HashMap;
import java.util.Map;

import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public class TokenFactory {

	private static String TOKEN;
	private static RequestSpecification HEADER;
	private static FilterableRequestSpecification request;

	public static String getToken() {
		if(TOKEN == null || HEADER == null) {
			generateToken();
		}
		
		return TOKEN;
	}	
	
	public static void removeAuthorization() {
		request = (FilterableRequestSpecification) requestSpecification;
		request.removeHeader("Authorization");
		HEADER = null;
	}

	private static void generateToken() {
		Map<String, String> login = new HashMap<>();
		login.put("email", "thinogueiras@testing.com");
		login.put("senha", "test123");

		TOKEN = 
				given()
					.body(login)
				.when()
					.post("/signin")
				.then()
					.statusCode(200)
					.extract().path("token");

		HEADER = requestSpecification.header("Authorization", "JWT " + TOKEN);
	}
}
