package com.app.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.app.pages.GasMileageCalculatorPage;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;

public class BasicDataDrivenTest {
	WebDriver driver;
	Workbook workbook;
	Sheet worksheet;
	GasMileageCalculatorPage gasMil;
	FileInputStream inStream;
	FileOutputStream outStream;
	public static final int CURRENTOD_COLUMN=1;
	public static final int PREVIOUSOD_COLUMN=2;
	public static final int GAS_COLUMN=3;
	@BeforeClass
	public void setUp() throws Exception {
		inStream= new FileInputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
		workbook=WorkbookFactory.create(inStream);
		worksheet=workbook.getSheetAt(0);
		driver=Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://www.calculator.net/gas-mileage-calculator.html");
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		outStream= new FileOutputStream(ConfigurationReader.getProperty("gasmileage.testdata.path"));
		workbook.write(outStream);
		outStream.close();
		inStream.close();
		workbook.close();
		
	}
	
	@Test
	public void dataDrivenMileageCalculatorTest() {
		
		for (int rownum = 1; rownum <worksheet.getPhysicalNumberOfRows(); rownum++) {
			
			Row currentRow=worksheet.getRow(rownum);
		
			// check the control collumn. If it does not say Y, then skip this row
			if(!currentRow.getCell(0).toString().equalsIgnoreCase("Y")) {
				if(currentRow.getCell(6)==null) {
					currentRow.createCell(6);
				}
				currentRow.getCell(6).setCellValue("Skip Requested");
				continue;
				
			}
		
		double currentOr=currentRow.getCell(CURRENTOD_COLUMN).getNumericCellValue();
		double previousOr=currentRow.getCell(PREVIOUSOD_COLUMN).getNumericCellValue();
		double gas=currentRow.getCell(GAS_COLUMN).getNumericCellValue();
		
		gasMil= new GasMileageCalculatorPage();
		
		gasMil.currentOdometer.clear();
		gasMil.currentOdometer.sendKeys(String.valueOf(currentOr));
		
		gasMil.previousOdometer.clear();
		gasMil.previousOdometer.sendKeys(String.valueOf(previousOr));
		
		gasMil.gas.clear();
		gasMil.gas.sendKeys(String.valueOf(gas));
		
		gasMil.calculate.click();
		String result []=gasMil.result.getText().split(" ");
		System.out.println(result[0]);
		String actualResult =result[0].replace(",","");
		// write result to actual 
		if(currentRow.getCell(5)==null) {
			currentRow.createCell(5);
		}
		currentRow.getCell(5).setCellValue(actualResult);
		
		double calculationResult=(currentOr-previousOr)/gas;
		DecimalFormat format=new DecimalFormat("#0.00");
		
		System.out.println(format.format(calculationResult));
		// write result to expected
		if(currentRow.getCell(4)==null) {
			currentRow.createCell(4);
		}
		currentRow.getCell(4).setCellValue(actualResult);
		
		
		if(currentRow.getCell(6)==null) {
			currentRow.createCell(6);
		}
		if(actualResult.equals(format.format(calculationResult))) {
			System.out.println("Pass");
			currentRow.getCell(6).setCellValue("Pass");
		}else {
			System.out.println("Fail");
			currentRow.getCell(6).setCellValue("Fail");
	
		}
		
		if(currentRow.getCell(7)==null) {
			currentRow.createCell(7);
		}
		currentRow.getCell(7).setCellValue(LocalDateTime.now().toString());
		
		
		
		
		
		}
		
	}

	
	
	
	
	
}
