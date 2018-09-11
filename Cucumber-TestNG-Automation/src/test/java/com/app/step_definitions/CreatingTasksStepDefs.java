package com.app.step_definitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.app.pages.SuiteCRMCreateTaskPage;
import com.app.pages.SuiteCRMDashboardPage;
import com.app.pages.SuiteCRMTaskSummaryPage;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreatingTasksStepDefs {
	
	SuiteCRMDashboardPage dahsboardPage=new SuiteCRMDashboardPage();
	SuiteCRMCreateTaskPage createTaskPage=new SuiteCRMCreateTaskPage();
	SuiteCRMTaskSummaryPage summaryPage=new SuiteCRMTaskSummaryPage();
	Map<String,String> createTaskMap=new HashMap<>();
	Map<String,String> overViewTaskMap=new HashMap<>();
	
	@When("^I click on create task$")
	public void i_click_on_create_task() {
		dahsboardPage.clickCreateTask();
	}

	@And("^Set subject as \"([^\"]*)\"$")
	public void set_subject_as(String taskSubject) {
		createTaskPage.subject.sendKeys(taskSubject);
		createTaskMap.put("Subject", taskSubject);
	}

	@When("^Set status as \"([^\"]*)\"$")
	public void set_status_as(String status) {
		Select statusSelect=new Select(createTaskPage.status);
				statusSelect.selectByVisibleText(status);	
				createTaskMap.put("Status", status);

	}

	@When("^Start date is todays date$")
	public void start_date_is_todays_date() {
		LocalDate today=LocalDate.now();
		DateTimeFormatter formatter =DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String todaysDate=today.format(formatter).toString();
		//System.out.println(todaysDate);
		createTaskPage.date_start_date.sendKeys(todaysDate);
		createTaskMap.put("Start Date", todaysDate);
	}

	@When("^Due date is (\\d+) days after todays date$")
	public void due_date_is_days_after_todays_date(int daysAhead) {
		String dueDate = LocalDate.now().plusDays(daysAhead).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString();
		createTaskPage.date_due_date.sendKeys(dueDate);
		createTaskMap.put("Due Date", dueDate);
		
		
	}

	@When("^Set \"([^\"]*)\" priority$")
	public void set_priority(String priorityLevel) {
		Select prioritySelect=new Select(createTaskPage.priority);
		prioritySelect.selectByVisibleText(priorityLevel);
		createTaskMap.put("Priority",priorityLevel);
	}

	@When("^Set description as \"([^\"]*)\"$")
	public void set_description_as(String taskDescription) {
		createTaskPage.description.sendKeys(taskDescription);
		createTaskMap.put("Description", taskDescription);
	}

	@When("^Save the task$")
	public void save_the_task() {
		createTaskPage.SAVE.click();
	}

	@Then("^Task details page should be displayed$")
	public void task_details_page_should_be_displayed() {
		assertTrue(summaryPage.taskOverView.isDisplayed());
		overViewTaskMap.put("Subject",summaryPage.subject.getText());
		overViewTaskMap.put("Status", summaryPage.status.getText());
		overViewTaskMap.put("Start Date", summaryPage.date_start.getText().substring(0,10));
		overViewTaskMap.put("Due Date", summaryPage.date_due.getText().replace(" 12:00am",""));
		overViewTaskMap.put("Priority", summaryPage.priority.getText());
		overViewTaskMap.put("Description", summaryPage.description.getText());
		
		System.out.println(overViewTaskMap);
		System.out.println();
		System.out.println(createTaskMap);
		
	}

	@Then("^Data should match with created task data$")
	public void data_should_match_with_created_task_data() {
	   assertEquals(overViewTaskMap, createTaskMap);
	}
}
