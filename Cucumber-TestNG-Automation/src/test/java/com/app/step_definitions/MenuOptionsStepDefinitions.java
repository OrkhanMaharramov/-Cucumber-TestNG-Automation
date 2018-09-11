package com.app.step_definitions;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.app.pages.SuiteCRMDashboardPage;
import com.app.utilities.BrowserUtils;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MenuOptionsStepDefinitions {
	SuiteCRMDashboardPage dashboard=new SuiteCRMDashboardPage();
	
	@When("^I hover over the (Collaboration|Sales|Marketing|Support|All) menu$")
	public void i_hover_over_the_Collaboration_menu(String menu) {
	 switch (menu) {
	case "Sales":
		 BrowserUtils.hover(dashboard.sales);
		break;

	case "Marketing":
		 BrowserUtils.hover(dashboard.marketing);
		break;
		
	case "Support":
		 BrowserUtils.hover(dashboard.support);
		break;
	case "Collaboration":
		 BrowserUtils.hover(dashboard.collaboration);
		break;
		
	case "All":
		 BrowserUtils.hover(dashboard.all);
		break;
		
	case "Activities":
		 BrowserUtils.hover(dashboard.activities);
		break;
	default:
	

	}
	   
	}

	@Then("^followwing menu options should be visible for (Collaboration|Sales|Marketing|Support|All):$")
	public void followwing_menu_options_should_be_visible_for_Collaboration(String menu,List<String> options) {
	 List<WebElement> topMenuOptions = dashboard.topMenuOptions(menu);
	 List<String> topMenuOptionsString=BrowserUtils.getElementsText(topMenuOptions);
	 
	 assertEquals(topMenuOptionsString.size(), options.size(),
			 "Number of expected menu options did not match");
	 
	 for (int i = 0; i < options.size(); i++) {
		
		 assertEquals(topMenuOptionsString.get(i), options.get(i));
	}
	 
	 
	 
	}
}
