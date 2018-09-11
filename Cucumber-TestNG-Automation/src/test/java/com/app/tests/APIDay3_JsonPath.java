package com.app.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.app.utilities.ConfigurationReader;

import   io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class APIDay3_JsonPath {

	/*
	 * Given Accept type is Json
	 *  When I send Get request to Rest URL:
   	 *	http://34.223.219.142:1212/ords/hr/regions
     * Then status code is 200
     * And Response content should be json
     * And 4 regions should be returned
     * And America is one of the region names
	 */
	
	@Test
	public void testItemsCountFromResponseBody() {
		given().accept(ContentType.JSON)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/regions")
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("items.region_id", hasSize(4))
		.and().assertThat().body("items.region_name", hasItem("Americas"))
		.and().assertThat().body("items.region_name", hasItems("Americas","Asia"));
	}
	
	/*
	 * Given Accept type is json
	 * When Params are limit 100
	 *   When I send Get request to Rest URL:
   	 *	http://34.223.219.142:1212/ords/hr/employees
     * Then status code is 200
     * And Response content should be json
     * And 100 employees data should be in json response body
	 */
	
	@Test
	public void testWithQueryParameterAndList() {
		given().accept(ContentType.JSON)
		.and().param("limit", 100)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees")
		.then().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("items.count", hasSize(100));
	}
	
	
	/*
	 * Given Accept type is json
	 * When Params are limit=100
	 * And path param is 110
	 *   When I send Get request to Rest URL:
   	 *	http://34.223.219.142:1212/ords/hr/employees
     * Then status code is 200
     * And Response content should be json
     * And 100 employees data should be in json response body
     *  "employee_id": 110,
    		"first_name": "John",
    		"last_name": "Chen",
    		"email": "JCHEN",
	 */
	
	@Test
	public void testWithPathParameters() {
		given().accept(ContentType.JSON)
		.and().params("limit", 100)
		.and().pathParams("employee_id",110)
		.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees/{employee_id}")
		.then().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().assertThat().body("employee_id", equalTo(110),
								"first_name",equalTo("John"),
								"last_name",equalTo("Chen"));
	}
	
	
	
	/*
	 * Given Accept type is json
	 * When Params are limit=100
	 *   When I send Get request to Rest URL:
   	 *	http://34.223.219.142:1212/ords/hr/employees
     * Then status code is 200
     * And Response content should be json
     * And all employee ids should be returned
    
	 */
	
	@Test
	public void testWithJsonPath() {
		
		Map<String,Integer> rParamMap=new HashMap<>();
		rParamMap.put("limit", 100);
		
		Response response = given().accept(ContentType.JSON)
							.params(rParamMap)
							.and().pathParams("employee_id",177)
							.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees/{employee_id}");
		
		JsonPath json=response.jsonPath();
		
		System.out.println(json.getInt("employee_id"));
		System.out.println(json.getString("last_name"));
		System.out.println(json.getString("job_id"));
		System.out.println(json.getInt("salary"));
		System.out.println(json.getString("links[1].href"));
		
		List<String> hrefs=json.get("links.href");
		
		System.out.println(hrefs);

		//json.prettyPrint();
	}
	
	
	/*
	 * Given Accept type is json
	 * When Params are limit=100
	 *   When I send Get request to Rest URL:
   	 *	http://34.223.219.142:1212/ords/hr/employees
     * Then status code is 200
     * And Response content should be json
     * And all employee data should be returned
    
	 */
	
	
	
	@Test
	public void testJsonPathWithLists() {
		

		Map<String,Integer> rParamMap=new HashMap<>();
		rParamMap.put("limit", 100);
		
		Response response = given().accept(ContentType.JSON)
							.params(rParamMap)
							.when().get(ConfigurationReader.getProperty("hrapp.baseresturl")+"/employees");
		
		assertEquals(response.statusCode(), 200); //check status code
		
		JsonPath json=response.jsonPath();
		
	//	JsonPath json=new JsonPath(new File(FilePath.json));
	//	JsonPath json=new JsonPath(response.asString());
		
		List<Integer> empIds=json.getList("items.employee_id");
		
		System.out.println(empIds);
		
		assertEquals(empIds.size(), 100);

		List<String> emails=json.getList("items.email");
		System.out.println(emails);

		assertEquals(emails.size(), 100);
		
		//get all employee ids that are greater than 150
		List<String> empIdList=json.getList("items.findAll{it.employee_id>150}.employee_id");
		System.out.println(empIdList);
		
		List<String> lastNames=json.getList("items.findAll{it.salary>15000}.last_name");
		System.out.println(lastNames);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
