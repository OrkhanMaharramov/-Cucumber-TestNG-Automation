package com.app.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay5_POSTthenGET {

	// //String requestJson="{\"region_id\" : 19888,\"region_name\" :\"Your Region\"}";
	
	@Test
	public void PostEmployeeThenGetEmployee() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/employees/";
//		String requestJson = "{\"employee_id\": 1897,\"first_name\": \"Gee\",\"last_name\": \"Moff\",\"email\": \"JJJJJ\",\"phone_number\": \"515.123.4567\", \"hire_date\":"
//				+ " \"2018-04-24T07:35:00Z\", \"job_id\": \"AD_PRES\", \"salary\": 24000, \"commission_pct\": null,\"manager_id\": null,\"department_id\": 90}";
//		
		int randomID=new Random().nextInt(999999);
		
		Map requestMap=new HashMap<>();
		requestMap.put("employee_id",randomID);
		requestMap.put("first_name", "Gee");
		requestMap.put("last_name", "Moff");
		requestMap.put("email", "OM"+randomID);
		requestMap.put("phone_number", "515.123.4567");
		requestMap.put("hire_date", "2018-04-24T07:35:00Z");
		requestMap.put("job_id", "IT_PROG");
		requestMap.put("salary", 24000);
		requestMap.put("commission_pct",null);
		requestMap.put("manager_id",null);
		requestMap.put("department_id",90);
		
		Response response = given().accept(ContentType.JSON)
				.and().contentType(ContentType.JSON)
				.and().body(requestMap)
				.when().post(url);
		
		
//		System.out.println(response.statusLine());
//		 response.prettyPrint();
		
		 assertEquals(response.statusCode(), 201);
		
		 Map responseMap=response.body().as(Map.class);
		 
		 for (Object key : requestMap.keySet()) {
			 System.out.println(responseMap.get(key)+" <> "+requestMap.get(key));
			 assertEquals(responseMap.get(key), requestMap.get(key));
		}
		 
		 
//		 assertEquals(responseMap.get("employee_id"), requestMap.get("employee_id"));
//		 assertEquals(responseMap.get("first_name"), requestMap.get("first_name"));
//		 assertEquals(responseMap.get("last_name"), requestMap.get("last_name"));
//		 assertEquals(responseMap.get("email"), requestMap.get("email"));
//		 assertEquals(responseMap.get("phone_number"), requestMap.get("phone_number"));
//		 assertEquals(responseMap.get("hire_date"), requestMap.get("hire_date"));
//		 assertEquals(responseMap.get("job_id"), requestMap.get("job_id"));
//		 assertEquals(responseMap.get("salary"), requestMap.get("salary"));
//		 assertEquals(responseMap.get("commission_pct"), requestMap.get("commission_pct"));
//		 assertEquals(responseMap.get("manager_id"), requestMap.get("manager_id"));
//		 assertEquals(responseMap.get("department_id"), requestMap.get("department_id"));
		 
		 response=given().accept(ContentType.JSON)
				 .when().get(url+randomID);
		 
		 assertEquals(response.statusCode(), 200);
		 
		Map getresponseMap=response.body().as(Map.class);
		 
		 for (Object key : requestMap.keySet()) {
			 System.out.println(getresponseMap.get(key)+" <> "+requestMap.get(key));
			 assertEquals(getresponseMap.get(key), requestMap.get(key));
		}
		 
	}
	
	
	
	
	
}
