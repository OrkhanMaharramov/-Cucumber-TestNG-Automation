package com.app.step_definitions.api;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.app.pages.HRAppDeptEmpPage;
import com.app.pages.HRAppSearchPage;
import com.app.utilities.BrowserUtils;
import com.app.utilities.ConfigurationReader;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Api_5_PostAnEmployee {
	
	RequestSpecification request;
	int employeeId;
	Response response;
	Map requestMap;
	String url = ConfigurationReader.getProperty("hrapp.baseresturl") + "/employees/";
	HRAppDeptEmpPage deptEmpPage= new HRAppDeptEmpPage();
	HRAppSearchPage searchPage=new HRAppSearchPage();
	Map uiEmployeeDataMap;
	@Then("^I search for Employee with \"([^\"]*)\" id$")
	public void i_search_for_Employee_with_id(String id) {
		 if(!id.equals("random")) {
		employeeId=Integer.parseInt(id);	
		    }
		
	   deptEmpPage.query.click();
	   searchPage.empIdSearchField.sendKeys(String.valueOf(employeeId));
	   searchPage.search.click();
	   
	}

	@Then("^UI search results must match API post$")
	public void ui_search_results_must_match_API_post(){
		BrowserUtils.waitFor(2);
		searchPage=new HRAppSearchPage();
		uiEmployeeDataMap=new HashMap<>();
		uiEmployeeDataMap.put("employee_id",Integer.valueOf(searchPage.employeeId.getAttribute("value")));
		uiEmployeeDataMap.put("first_name", searchPage.firstName.getAttribute("value"));
		uiEmployeeDataMap.put("last_name", searchPage.lastName.getAttribute("value"));
		uiEmployeeDataMap.put("email", searchPage.email.getAttribute("value"));
		uiEmployeeDataMap.put("job_id", searchPage.jobId.getAttribute("value"));
		uiEmployeeDataMap.put("salary",Integer.valueOf(searchPage.salary.getAttribute("value")));
		uiEmployeeDataMap.put("department_id",Integer.valueOf(searchPage.departmentId.getText()));
		
		// compare data against Json data used in POST api /Map
		
		 for (Object key : uiEmployeeDataMap.keySet()) {
			 System.out.println(uiEmployeeDataMap.get(key)+" <> "+requestMap.get(key));
			 assertEquals(uiEmployeeDataMap.get(key), requestMap.get(key));
		}
	}
	
	@Given("^Content type and Accept type is Json$")
	public void content_type_and_Accept_type_is_Json() {
		request= given().accept(ContentType.JSON)
				.and().contentType(ContentType.JSON);
	}

	@When("^I post a new Employee with \"([^\"]*)\" id$")
	public void i_post_a_new_Employee_with_id(String id) {
		
	    if(id.equals("random")) {
	    	employeeId=new Random().nextInt(99999);
	    	
	    }else {
	    	employeeId=Integer.parseInt(id);
	    	
	    }
	    
	    requestMap=new HashMap<>();
		requestMap.put("employee_id",employeeId);
		requestMap.put("first_name", "Gee");
		requestMap.put("last_name", "Moff");
		requestMap.put("email", "OM"+employeeId);
		requestMap.put("phone_number", "515.123.4567");
		requestMap.put("hire_date", "2018-04-24T07:35:00Z");
		requestMap.put("job_id", "IT_PROG");
		requestMap.put("salary", 24000);
		requestMap.put("commission_pct",null);
		requestMap.put("manager_id",null);
		requestMap.put("department_id",90);
		
		 response =request.and().body(requestMap)
				.when().post(url);
	}

	@Then("^Status code is (\\d+)$")
	public void status_code_is(int statusCode) {
	  
		assertEquals(response.statusCode(), statusCode);
	}

	@Then("^Response Json should contain Employee info$")
	public void response_Json_should_contain_Employee_info() {
		Map responseMap=response.body().as(Map.class);
		 
		 for (Object key : requestMap.keySet()) {
			 System.out.println(responseMap.get(key)+" <> "+requestMap.get(key));
			 assertEquals(responseMap.get(key), requestMap.get(key));
		}
	}



	@When("^I send a GET request with \"([^\"]*)\" id$")
	public void i_send_a_GET_request_with_id(String id) {
		if(!id.equals("random")) {
			employeeId=Integer.parseInt(id);
		}
		 response=given().accept(ContentType.JSON)
				 .when().get(url+employeeId);
		 
	}

	@Then("^Employee JSON Response Data should match the posted JSON data$")
	public void employee_JSON_Response_Data_should_match_the_posted_JSON_data() {
		Map getresponseMap=response.body().as(Map.class);
		 
		 for (Object key : requestMap.keySet()) {
			 System.out.println(getresponseMap.get(key)+" <> "+requestMap.get(key));
			 assertEquals(getresponseMap.get(key), requestMap.get(key));
		}
	}
}
