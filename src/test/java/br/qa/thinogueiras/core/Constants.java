package br.qa.thinogueiras.core;

import io.restassured.http.ContentType;

public interface Constants {
	
	String BASE_URL = "https://barrigarest.wcaquino.me";
	Integer PORT = 443;
	String BASE_PATH = "";
	
	ContentType CONTENT_TYPE = ContentType.JSON;
	
	Long MAX_TIMEOUT = 15000L;
}
