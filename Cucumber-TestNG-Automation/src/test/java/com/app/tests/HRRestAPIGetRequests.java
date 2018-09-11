package com.app.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
public class HRRestAPIGetRequests {
	
	
  @Test
  public void simpleGet() {
	  when().get("http://34.223.219.142:1212/ords/hr/employees")
	  .then().statusCode(200);
	  
  }
  
  @Test
  public void printResponse() {
	when().get("http://34.223.219.142:1212/ords/hr/countries")
	  .body().prettyPrint();	  
  }
  
  @Test
  public void getWithHeaders() {
	 with().accept(ContentType.JSON)
	 .when()
	  .get("http://34.223.219.142:1212/ords/hr/countries/US")
	  .then().statusCode(200); 
  }
  @Test
  public void negativeGet() {
	
//	 when()
//	  .get("http://34.223.219.142:1212/ords/hr/employees/1234")
//	  .then().statusCode(404); 
	  
	  Response response = when().get("http://34.223.219.142:1212/ords/hr/employees/1234");
	  assertEquals(response.statusCode(), 404);
	  assertTrue(response.asString().contains("Not Found"));
	  response.prettyPrint();
  }
  
  /*
   * When I send Get request to Rest URL:
   * http://34.223.219.142:1212/ords/hr/employees/100
   * And Accept type is json
   * Then status code is 200
   * And Response content should be json
   */
  
  @Test
  public void VerifyContentTypeWithAssertThat() {
	  String url = "http://34.223.219.142:1212/ords/hr/employees/100";
	  
	  given().accept(ContentType.JSON)
	  .when()
	  
	  .get(url)
	  
	  .then().assertThat().statusCode(200)
	  .and().contentType(ContentType.JSON);
  }
  
  /*
   * Given Accept type is Json
   * When I send Get request to Rest URL:
   * http://34.223.219.142:1212/ords/hr/employees/100
   * Then status code is 200
   * And Response content should be json
   * And first name should be Steven
   * And employee id is 100
   */
  
  
  @Test
  public void VerifyFirstName() throws URISyntaxException {

	  
	  URI uri =new URI("http://34.223.219.142:1212/ords/hr/employees/100");
	  given().accept(ContentType.JSON)
	  .when()
	  
	  .get(uri)
	  
	  .then().assertThat().statusCode(200)
	  .and().contentType(ContentType.JSON)
	  .and().assertThat().body("first_name",Matchers.equalTo("Steven"))
	  .and().assertThat().body("employee_id",Matchers.equalTo(100));;
  }
  
  
  
  
  
}
