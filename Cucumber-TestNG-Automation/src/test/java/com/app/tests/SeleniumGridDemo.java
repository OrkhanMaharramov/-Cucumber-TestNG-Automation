package com.app.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SeleniumGridDemo {
WebDriver driver;

	public static final String URL ="http://54.87.165.174:4444/wd/hub";

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setPlatform(Platform.ANY);
		
		driver = new RemoteWebDriver(new URL(URL), caps);
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}

	@Test
	public void testGoogle() throws Exception {
		driver.get("https://www.google.com");
		driver.findElement(By.id("lst-ib")).sendKeys("Java"+Keys.ENTER);
		System.out.println(driver.getTitle());
		Thread.sleep(2356);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
