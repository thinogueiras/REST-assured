package br.qa.thinogueiras.core;

import static io.restassured.http.ContentType.JSON;

import io.restassured.http.ContentType;

public interface Constants {
	
	String BASE_URL = "https://barrigarest.wcaquino.me";
	Integer PORT = 443;
	String BASE_PATH = "";
	
	ContentType CONTENT_TYPE = JSON;
	
	Long MAX_TIMEOUT = 15000L;
}
