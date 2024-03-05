package br.qa.thinogueiras.tests;

import static br.qa.thinogueiras.core.TokenFactory.removeAuthorization;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

@DisplayName("Authorization Tests")
public class AuthTest extends BaseTest {
	
	@BeforeAll
	public static void authorization() {
		removeAuthorization();
	}
	
	@Test
	public void shouldNotAccessContasWithoutToken() {		
		
		given()
		.when()
			.get("/contas")
		.then()
			.statusCode(401);
	}
	
	@Test
	public void shouldNotAccessSaldoWithoutToken() {		
		
		given()
		.when()
			.get("/saldo")
		.then()
			.statusCode(401);
	}
	
	@Test
	public void shouldNotAccessTransacoesWithoutToken() {		
		
		given()
		.when()
			.get("/transacoes")
		.then()
			.statusCode(401);
	}
}
