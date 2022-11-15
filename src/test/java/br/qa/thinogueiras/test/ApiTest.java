package br.qa.thinogueiras.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;
import br.qa.thinogueiras.core.Movement;

public class ApiTest extends BaseTest{
	
	private static String TOKEN;
	
	@BeforeAll
	public static void setToken() {
		Map<String, String> login = new HashMap<>();
		login.put("email", "thinogueiras@testing.com");
		login.put("senha", "test123");
		
		TOKEN = given()			
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
	}
	
	@Test
	public void shouldNotAccessWithoutToken() {
		given()			
		.when()
			.get("/contas")
		.then()
			.statusCode(401);
	}
	
	@Test
	public void shouldInsertAccount() {		
		given()
			.header("Authorization", "JWT " + TOKEN)
			.body("{\"nome\": \"Conta b�sica\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void shouldEditAccount() {
		given()			
			.header("Authorization", "JWT " + TOKEN)
			.body("{\"nome\": \"Conta alterada\"}")
		.when()
			.put("/contas/1477462")
		.then()
			.statusCode(200)
			.body("nome", is("Conta alterada"));
	}	
	
	@Test
	public void shouldNotInsertAccountWithSameName() {
		given()			
			.header("Authorization", "JWT " + TOKEN)
			.body("{\"nome\": \"Conta b�sica\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(400)
			.body("error", is("J� existe uma conta com esse nome!"));
	}
	
	@Test
	public void shouldInsertAccountMovement() {
		Movement movement = new Movement();
		
		movement.setConta_id(1477481);
		movement.setDescricao("Teste inser��o movimenta��o");
		movement.setEnvolvido("Env mov");
		movement.setTipo("REC");
		movement.setData_transacao("02/01/2022");
		movement.setData_pagamento("02/01/2026");
		movement.setValor(5000f);
		movement.setStatus(true);
		
		given()			
			.header("Authorization", "JWT " + TOKEN)
			.body(movement)
		.when()
			.post("/transacoes")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void shouldValidateRequiredFieldsInAccountMovementInsert() {
		given()			
			.header("Authorization", "JWT " + TOKEN)
			.body("{}")
		.when()
			.post("/transacoes")
		.then()
			.statusCode(400)
			.body("$", hasSize(8))
			.body("msg", hasItems(
					"Data da Movimenta��o � obrigat�rio",
					"Data do pagamento � obrigat�rio",
					"Descri��o � obrigat�rio",
					"Interessado � obrigat�rio",
					"Valor � obrigat�rio",
					"Valor deve ser um n�mero",
					"Conta � obrigat�rio",
					"Situa��o � obrigat�rio"));
	}
}