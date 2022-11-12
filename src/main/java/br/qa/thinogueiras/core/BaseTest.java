package br.qa.thinogueiras.core;

import static io.restassured.RestAssured.*;
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
	}
}
