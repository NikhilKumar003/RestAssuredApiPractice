package day01;


import static io.restassured.RestAssured.given;

import java.io.File;

import javax.mail.Multipart;

import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

public class FileUploadDownload {
	
	
	@Test
	public void fileUpload() {
		
		File file = new File("C:\\Users\\user\\Downloads\\mytest.txt");
//		RequestSpecification resp = new 
		
		given()
			.multiPart("file",file)
			
		.when()
			.post(" https://the-internet.herokuapp.com/upload")
		.then()
		.log().body();
	}

	@Test
	public void download() {
		given()
		.pathParam("file1", "mytest.txt")
		.when()
			.get("https://the-internet.herokuapp.com/download/{file1}")
		.then()
		.log().body();
	}
}
