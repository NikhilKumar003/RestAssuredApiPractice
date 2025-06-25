package day01;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class HttpRequests {
	
	int userId;
	
	@Test(priority = 1,enabled = true)
	void getUsers() {
		
		given()
		
		.when()
		 .get("https://reqres.in/api/users?page=2")
		 	
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap<String,String> data = new HashMap<>();
		data.put("name", "nikhil");
		data.put("job", "IT");
		 
		
		userId = given()
			.contentType("application/json")
			.body(data)
			.header("x-api-key", "reqres-free-v1")
		
		.when()
			.post("https://reqres.in/api/users")
			
		.then() 
			.statusCode(201)
			.header("Content-Type",equalTo("application/json; charset=utf-8"))
			.time(lessThan(2000L))
			.body("name",equalTo("nikhil"))
			.body("job",equalTo("IT"))
			.body(containsString("id"))
			.log().all()
			.extract().jsonPath().getInt("id");
	}
	
	@Test(priority = 3,dependsOnMethods = {"createUser"})
	void updateUser() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "venu");
		map.put("job","Civil Engineer");
		
		given()
		.contentType("application/json")
		.body(map)
		.header("x-api-key", "reqres-free-v1")
		
		.when()
		.put("https://reqres.in/api/users/"+userId)
		.then()
		.statusCode(200)
	    .header("Content-Type",equalTo("application/json; charset=utf-8"))
	    .time(lessThan(2000L))
		.body("name",equalTo("venu"))
		.body("job",equalTo("Civil Engineer"))
		.log().all();
	    
		
	}
	
	@Test(priority = 4,dependsOnMethods = {"createUser","updateUser"})
	void deleteUser() {
		given()
		.header("x-api-key", "reqres-free-v1")
		
		.when()
		.delete("https://reqres.in/api/users/"+userId)
		.then()
		.statusCode(204)
		.body(emptyOrNullString())
		.log().all();
	}

}
