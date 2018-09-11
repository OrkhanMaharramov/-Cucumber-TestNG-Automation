package com.app.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.Test;

import com.app.beans.Countries;
import com.app.beans.CountriesResponse;
import com.app.beans.Region;
import com.app.beans.RegionResponse;
import com.app.utilities.ConfigurationReader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIDay4POSTRequests {

	@Test
	public void postNewRegion() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl")+"/regions/";
		
		//String requestJson="{\"region_id\" : 19888,\"region_name\" :\"Your Region\"}";
		
		Map requestMap=new HashMap<>();
		requestMap.put("region_id", new Random().nextInt(55555));
		requestMap.put("region_name", "my region");
	
		Response response = given().accept(ContentType.JSON)
		.and().contentType(ContentType.JSON)
		.and().body(requestMap)
		.when().post(url);
	
	System.out.println(response.statusLine());
	response.prettyPrint();
	
	assertEquals(response.statusCode(), 201);
	
	Map responseMap=response.body().as(Map.class);
	
	//assertEquals(responseMap, requestMap); did not work
	
	assertEquals(responseMap.get("region_id"), requestMap.get("region_id"));
	assertEquals(responseMap.get("region_name"), requestMap.get("region_name"));

	}
	
	@Test
	public void postUsingPOJO() {
		String url = ConfigurationReader.getProperty("hrapp.baseresturl")+"/regions/";
		
		Region region=new Region();
		region.setRegion_id(new Random().nextInt(55555));
		region.setRegion_name("I told you");
		
		Response response= given().accept(ContentType.JSON)
						   .and().contentType(ContentType.JSON)
						   .and().body(region)
						   .when().post(url);
		
		assertEquals(response.statusCode(), 201);
		
		RegionResponse responseRegion= response.body().as(RegionResponse.class);
		
		
		assertEquals(responseRegion.getRegion_id(), region.getRegion_id());
		assertEquals(responseRegion.getRegion_name(), region.getRegion_name());
	}
	
	@Test
	public void postUsingPOJOCountries() {
String url = ConfigurationReader.getProperty("hrapp.baseresturl")+"/countries/";
		
		Countries country=new Countries();
		country.setRegion_id(4);
		country.setCountry_id("GJ");
		country.setCountry_name("KKKKKKK");
		
		Response response= given().log().all()
						   .accept(ContentType.JSON)
						   .and().contentType(ContentType.JSON)
						   .and().body(country)
						   .when().post(url);
		
		assertEquals(response.statusCode(), 201);
		
		CountriesResponse responseCountries= response.body().as(CountriesResponse.class);
		
		
		assertEquals(responseCountries.getRegion_id(), country.getRegion_id());
		assertEquals(responseCountries.getCountry_name(), country.getCountry_name());
		assertEquals(responseCountries.getCountry_id(), country.getCountry_id());
	}

}
