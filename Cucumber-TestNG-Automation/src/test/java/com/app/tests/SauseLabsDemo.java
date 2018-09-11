package com.app.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.*;

public class SauseLabsDemo {
	WebDriver driver;
	
	public static final String USERNAME ="JohnDoe55";
	public static final String ACCESS_KEY = "92912d9d-23b5-491b-aa39-50f0265c1135";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setPlatform(Platform.WIN10);
		caps.setCapability("version", "latest");

		driver = new RemoteWebDriver(new URL(URL), caps);
	}

	@Test
	public void testGoogle() throws Exception {
		driver.get("https://google.com");
		driver.findElement(By.id("lst-ib")).sendKeys("Java"+Keys.ENTER);
		System.out.println(driver.getTitle());
		Thread.sleep(2356);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
