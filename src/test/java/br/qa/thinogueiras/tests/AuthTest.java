package br.qa.thinogueiras.tests;

import static br.qa.thinogueiras.core.TokenFactory.removeAuthorization;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

@DisplayName("Authorization Tests")
public class AuthTest extends BaseTest {
	
	@Test
	public void shouldNotAccessWithoutToken() {
		
		removeAuthorization();
		
		given()
		.when()
			.get("/contas")
		.then()
			.statusCode(401);
	}
}
