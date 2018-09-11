package com.app.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.omg.SendingContext.RunTime;

public class ExcelWrite {

	public static void main(String[] args) throws Exception {
		String testDataPath="./src/test/resources/testdata/Employees.xlsx";
		
		FileInputStream inStream=new FileInputStream(testDataPath);
		Workbook workbook =WorkbookFactory.create(inStream);
		Sheet worksheet=workbook.getSheet("Sheet1");
		
		Cell job=worksheet.getRow(5).getCell(2);
		job.setCellValue("Automation Tester");
		
		
		FileOutputStream outStream=new FileOutputStream(testDataPath);
		workbook.write(outStream);
		
		outStream.close();
		workbook.close();
		inStream.close();
	}

}
