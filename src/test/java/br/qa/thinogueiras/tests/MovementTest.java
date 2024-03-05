package br.qa.thinogueiras.tests;

import static br.qa.thinogueiras.utils.Utils.getAccountIdByName;
import static br.qa.thinogueiras.utils.Utils.getMovementIdByDescription;
import static br.qa.thinogueiras.utils.Utils.getValidMovement;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;
import br.qa.thinogueiras.core.Movement;

@DisplayName("Movement Tests")
public class MovementTest extends BaseTest {	
	
	@Test
	public void shouldInsertAccountMovement() {
		Movement movement = getValidMovement();	
		
		given()			
			.body(movement)
		.when()
			.post("/transacoes")
		.then()
			.statusCode(201)
			.body("$", aMapWithSize(13))
			.body("valor", is("5000.00"));
	}
	
	@Test
	public void shouldValidateRequiredFieldsInAccountMovementInsert() {
		given()			
			.body("{}")
		.when()
			.post("/transacoes")
		.then()
			.statusCode(400)
			.body("$", hasSize(8))
			.body("msg", hasItems(
					"Data da Movimentação é obrigatório",
					"Data do pagamento é obrigatório",
					"Descrição é obrigatório",
					"Interessado é obrigatório",
					"Valor é obrigatório",
					"Valor deve ser um número",
					"Conta é obrigatório",
					"Situação é obrigatório"));
	}
	
	@Test
	public void shouldNotRegisterMovementWithFutureDate() {
		Movement movement = getValidMovement();		
		movement.setData_transacao("13/11/2027");
		
		given()			
			.body(movement)
		.when()
			.post("/transacoes")
		.then()
			.statusCode(400)
			.body("$", hasSize(1))
			.body("msg", hasItems("Data da Movimentação deve ser menor ou igual à data atual"));
	}
	
	@Test
	public void shouldNotRemoveAccountWithMovement() {
		Integer CONTA_ID = getAccountIdByName("Conta com movimentacao");
		
		given()
			.pathParam("id", CONTA_ID)
		.when()
			.delete("/contas/{id}")
		.then()
			.statusCode(500)
			.body("constraint", is("transacoes_conta_id_foreign"));
	}
	
	@Test
	public void shouldRemoveAccountMovement() {
		Integer MOV_ID = getMovementIdByDescription("Movimentacao para exclusao");
		
		given()
		.pathParam("id", MOV_ID)
		.when()
			.delete("/transacoes/{id}")
		.then()
			.statusCode(204);			
	}
}
