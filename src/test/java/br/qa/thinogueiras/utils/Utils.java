package br.qa.thinogueiras.utils;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import br.qa.thinogueiras.core.Movement;

public class Utils {
	
	public static Movement getValidMovement() {
		Movement movement = new Movement();
				
		movement.setConta_id(getAccountIdByName("Conta para movimentacoes"));
		movement.setDescricao("Teste de inserção de movimentação");
		movement.setEnvolvido("Env mov");
		movement.setTipo("REC");
		movement.setData_transacao("13/11/2022");
		movement.setData_pagamento("15/11/2026");
		movement.setValor(5000f);
		movement.setStatus(true);
		
		return movement;
	}

	public static Integer getAccountIdByName(String name) {
		return get("/contas?nome="+name).then().extract().path("id[0]");
	}
	
	public static Integer getMovementIdByDescription(String description) {
		return get("/transacoes?descricao="+description).then().extract().path("id[0]");
	}
	
	public static void resetUserData() {
		given()
		.when()
			.get("/reset")
		.then()
			.statusCode(200);		
	}
}
