package br.qa.thinogueiras.core;

import static br.qa.thinogueiras.utils.Utils.resetUserData;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import java.util.HashMap;
import java.util.Map;

import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

public class TokenFactory {

	private static String TOKEN = "";
	private static RequestSpecification HEADER;
	private static FilterableRequestSpecification request;
	
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

	public static String getToken() {
		if(HEADER == null) {
			generateToken();
			
			resetUserData();;
		}
		
		return TOKEN;
	}	
	
	public static void removeAuthorization() {
		request = (FilterableRequestSpecification) requestSpecification;
		request.removeHeader("Authorization");
		HEADER = null;
	}	
}
