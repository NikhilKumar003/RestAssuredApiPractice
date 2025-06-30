package day01;


import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersValidate {

	
	@Test
	public void headerValidate() {
		
		Response response =given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.log().headers()
			.statusCode(200)
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.extract().response();
		
//		extract specific header
		System.out.println("==========");
		String header=	response.getHeader("Content-Type");
		System.out.println(header);
		
		System.out.println("=============");
		
//		extract all headers
		Headers heade = response.getHeaders();
		
		for(Header h:heade) {
			System.out.println(h.getName()+" : "+h.getValue());
		}
	}
}
