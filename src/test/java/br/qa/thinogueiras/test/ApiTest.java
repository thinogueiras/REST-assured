package br.qa.thinogueiras.test;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

public class ApiTest extends BaseTest{
	
	@Test
	public void shouldNotBeAccessWithoutToken() {
		given()
			.when()
				.get("/contas")
			.then()
				.statusCode(401);
	}
}
