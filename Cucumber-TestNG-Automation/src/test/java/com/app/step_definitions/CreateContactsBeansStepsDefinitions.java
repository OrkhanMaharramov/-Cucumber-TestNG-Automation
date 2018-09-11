package com.app.step_definitions;

import java.util.List;

import com.app.beans.ContactBean;
import com.app.pages.SuiteCRMContactInformationPage;
import com.app.pages.SuiteCRMCreateContactPage;
import com.app.pages.SuiteCRMDashboardPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.java.en.When;

public class CreateContactsBeansStepsDefinitions {
	

SuiteCRMDashboardPage dashboard= new SuiteCRMDashboardPage();
SuiteCRMCreateContactPage createContact =new SuiteCRMCreateContactPage();
SuiteCRMContactInformationPage contactInformation= new SuiteCRMContactInformationPage();
	
	@When("^I save a new contact:$")
	public void i_save_a_new_contact(List<ContactBean> contacts) {
	
	ContactBean contactBean = contacts.get(0);
	
	BrowserUtils.hover(dashboard.createLink);
	dashboard.createContact.click();
	
	createContact.firstName.sendKeys(contactBean.getFirstName());
	createContact.lastName.sendKeys(contactBean.getLastName());
	createContact.officePhoneNumber.sendKeys(contactBean.getOfficePhone());
	createContact.cellPhoneNumber.sendKeys(contactBean.getCellPhone());
	createContact.department.sendKeys(contactBean.getDepartment());
	
	createContact.save();
	}
}
