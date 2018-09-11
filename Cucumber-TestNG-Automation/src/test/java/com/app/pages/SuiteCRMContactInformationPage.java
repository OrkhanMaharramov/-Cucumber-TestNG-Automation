package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Driver;

public class SuiteCRMContactInformationPage {
	
	private WebDriver driver;
	
	public SuiteCRMContactInformationPage() {
		this.driver=Driver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="first_name")
	public WebElement firstName;
	
	@FindBy(id="last_name")
	public WebElement lastName;
}
