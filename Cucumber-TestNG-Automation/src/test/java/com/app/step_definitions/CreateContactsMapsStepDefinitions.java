package com.app.step_definitions;

import java.util.Map;

import com.app.pages.SuiteCRMContactInformationPage;
import com.app.pages.SuiteCRMCreateContactPage;
import com.app.pages.SuiteCRMDashboardPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.Then;


public class CreateContactsMapsStepDefinitions {
	

SuiteCRMDashboardPage dashboard= new SuiteCRMDashboardPage();
SuiteCRMCreateContactPage createContact =new SuiteCRMCreateContactPage();
SuiteCRMContactInformationPage contactInformation= new SuiteCRMContactInformationPage();

	@Then("^I create a new contact:$")
	public void i_create_a_new_contact(Map<String,String> contact) {
	
		BrowserUtils.hover(dashboard.createLink);
		dashboard.createContact.click();
		
		if (contact.get("first_name")!=null) {
			createContact.firstName.sendKeys(contact.get("first_name"));
		}
		if (contact.get("last_name")!=null) {
			createContact.lastName.sendKeys(contact.get("last_name"));
		}
		if (contact.get("office_phone")!=null) {
			createContact.officePhoneNumber.sendKeys(contact.get("office_phone"));
		}
		if (contact.get("cell_phone")!=null) {
			createContact.cellPhoneNumber.sendKeys(contact.get("cell_phone"));	
			}
		
		createContact.save();
	}
}
