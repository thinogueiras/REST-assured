package br.qa.thinogueiras.tests;

import static br.qa.thinogueiras.utils.Utils.getAccountIdByName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

@DisplayName("Balance Tests")
public class BalanceTest extends BaseTest {
	
	@Test
	public void shouldCalculateTheAccountBalance() {
		Integer ID_ACCOUNT = getAccountIdByName("Conta para saldo");
		
		given()			
		.when()
			.get("/saldo")
		.then()
			.statusCode(200)
			.body("find{it.conta_id=="+ID_ACCOUNT+"}.saldo", is("534.00"));			
	}
}
