package br.qa.thinogueiras.tests;

import static br.qa.thinogueiras.utils.Utils.getAccountIdByName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

@DisplayName("Account Tests")
public class AccountTest extends BaseTest {
	
	Map<String, String> payload = new HashMap<>();
	
	@Test
	public void shouldInsertAccount() {
		
		payload.put("nome", "Conta corrente");

		given()			
			.body(payload)
		.when()
			.post("/contas")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void shouldEditAccount() {		
		Integer ID_ACCOUNT = getAccountIdByName("Conta para alterar");
		
		payload.put("nome", "Conta salário");
		
		given()			
			.body(payload)
			.pathParam("id", ID_ACCOUNT)
		.when()
			.put("/contas/{id}")
		.then()
			.statusCode(200)
			.body("nome", is("Conta salário"));
	}	
	
	@Test
	public void shouldNotInsertAccountWithSameName() {
		payload.put("nome", "Conta mesmo nome");

		given()			
			.body(payload)
		.when()
			.post("/contas")
		.then()
			.statusCode(400)
			.body("error", is("Já existe uma conta com esse nome!"));
	}
}
