package day01;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class Cookies {
	
	@Test
	public void validateCookies() {
		
		Response response = 	
		given()
			
		.when()
			.get("https://www.google.com/")
		.then()
			.log().cookies()
			.statusCode(200)
			.cookie("AEC",notNullValue())
			.extract().response();
		System.out.println("=================");
		
		String cooke = response.getCookie("AEC");
		System.out.println(cooke);
		System.out.println("==================");
		
		Map<String,String> allCookies= response.getCookies();
		for(String cook : allCookies.keySet() ) {
			System.out.println(cook+" : "+ allCookies.get(cook));
		}
		
//		get detaile info about cookie
			Cookie cookie_info=		response.getDetailedCookie("AEC");
			System.out.println(cookie_info.getExpiryDate());
			System.out.println(cookie_info.hasValue());
			System.out.println(cookie_info.getValue());
	}
	

}
