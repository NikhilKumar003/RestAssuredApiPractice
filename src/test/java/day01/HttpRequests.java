package day01;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpRequests {
	
//	@Test
	void getUsers() {
		
		given()
		
		.when()
		 .get("https://reqres.in/api/users?page=2")
		 	
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("name", "nikhil");
		data.put("job", "IT");
		
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log().all();
	}

}
