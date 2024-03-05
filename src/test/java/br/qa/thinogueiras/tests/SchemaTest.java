package br.qa.thinogueiras.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

@DisplayName("JSON Schema Tests")
public class SchemaTest extends BaseTest {

	@Test
	public void shouldValidateAccountJSONSchema() {
		given()
		.when()
			.get("/contas")
		.then()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath("schemas/account.json"));
	}
	
	@Test
	public void shouldValidateBalanceJSONSchema() {
		given()
		.when()
			.get("/saldo")
		.then()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath("schemas/balance.json"));
	}
	
	@Test
	public void shouldValidateMovementJSONSchema() {
		given()
		.when()
			.get("/transacoes")
		.then()
			.statusCode(200)
			.body(matchesJsonSchemaInClasspath("schemas/movement.json"));
	}
}
