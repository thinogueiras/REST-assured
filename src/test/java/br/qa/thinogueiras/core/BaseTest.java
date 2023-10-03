package br.qa.thinogueiras.core;

import static br.qa.thinogueiras.core.TokenFactory.getToken;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTest implements Constants {	
	
	@BeforeAll
	public static void setup() {
		baseURI = BASE_URL;
		port = PORT;
		basePath = BASE_PATH;
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setContentType(CONTENT_TYPE);
		requestSpecification = requestSpecBuilder.build();
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectResponseTime(lessThan(MAX_TIMEOUT));
		responseSpecification = responseSpecBuilder.build();
		
		enableLoggingOfRequestAndResponseIfValidationFails();
		
		getToken();
		
		get("/reset").then().statusCode(200);
	}
}
