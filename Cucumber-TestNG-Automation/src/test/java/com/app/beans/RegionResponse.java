package com.app.beans;

import java.util.ArrayList;
import java.util.Map;

public class RegionResponse {
	//@SerializedName("region_id")
	private int region_id;
	private String region_name;
	private ArrayList<Map<String,String>> links;

	
	public ArrayList<Map<String, String>> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<Map<String, String>> links) {
		this.links = links;
	}
	
	
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	
	
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	
	
}
