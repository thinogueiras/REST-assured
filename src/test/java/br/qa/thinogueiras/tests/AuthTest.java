package br.qa.thinogueiras.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.specification.FilterableRequestSpecification;

@DisplayName("Authorization Tests")
public class AuthTest {
	
	@Test
	public void shouldNotAccessWithoutToken() {
		FilterableRequestSpecification request = (FilterableRequestSpecification) requestSpecification;
		request.removeHeader("Authorization");
		
		given()
		.when()
			.get("/contas")
		.then().statusCode(401);
	}
}
