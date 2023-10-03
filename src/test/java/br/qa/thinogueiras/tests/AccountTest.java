package br.qa.thinogueiras.tests;

import static br.qa.thinogueiras.utils.Utils.getAccountIdByName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

@DisplayName("Account Tests")
public class AccountTest extends BaseTest {
	
	@Test
	public void shouldInsertAccount() {		
		given()			
			.body("{\"nome\": \"Conta corrente\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void shouldEditAccount() {		
		Integer ID_ACCOUNT = getAccountIdByName("Conta para alterar");
		
		given()			
			.body("{\"nome\": \"Conta salário\"}")
			.pathParam("id", ID_ACCOUNT)
		.when()
			.put("/contas/{id}")
		.then()
			.statusCode(200)
			.body("nome", is("Conta salário"));
	}	
	
	@Test
	public void shouldNotInsertAccountWithSameName() {
		given()			
			.body("{\"nome\": \"Conta mesmo nome\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(400)
			.body("error", is("Já existe uma conta com esse nome!"));
	}
}
