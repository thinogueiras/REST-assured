package br.qa.thinogueiras.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.qa.thinogueiras.core.BaseTest;

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
	public void shouldNotBeAccessWithoutToken() {
		given()
			.when()
				.get("/contas")
			.then()
				.statusCode(401);
	}
	
	@Test
	public void shouldBeInsertAccount() {		
		given()
			.header("Authorization", "JWT " + TOKEN)
			.body("{\"nome\": \"Conta básica\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void shouldBeChangeAccount() {
		given()			
			.header("Authorization", "JWT " + TOKEN)
			.body("{\"nome\": \"Conta alterada\"}")
		.when()
			.put("/contas/1477462")
		.then()
			.statusCode(200)
			.body("nome", is("Conta alterada"));
	}
}