package com.jbk.testClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

import com.jbk.testClasses.*;

public class Utility extends TestBase {

	public static ArrayList<String> readExcel(String fileName, String sheetName) throws IOException {

		FileInputStream inputStream = new FileInputStream(fileName);
		Workbook workbook = null;

		// Find the file extension by splitting file name in substring and
		// getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		workbook = new XSSFWorkbook(inputStream);
		// Read sheet inside the workbook by its name
		Sheet sheet = workbook.getSheet(sheetName);
		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		ArrayList<String> excelMenulist = new ArrayList<String>();
		// Create a loop over all the rows of excel file to read it
		for (int i = 0; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < row.getLastCellNum(); j++) {
				// Print Excel data in console
				String listOfMenu = row.getCell(j).getStringCellValue();
				excelMenulist.add(listOfMenu);
			}
		}
		return excelMenulist;
	}
	
	public static ArrayList ReadExcelDataWithRowNCol(String filename, String sheetname, int rowcount, int colcount) {
		ArrayList value = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(filename);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetname);

			for (int i = 0; i < rowcount; i++) {
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < colcount; j++) {
					XSSFCell cell = row.getCell(j);
					try {
						cell.setCellType(CellType.STRING);
						value.add(cell.getStringCellValue());
					} catch (Exception e) {
						cell.setCellType(CellType.NUMERIC);
						value.add(cell.getNumericCellValue());
					}
				//	value.add(cell);
					
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}
	

	public static ArrayList readExcelDataWithColno(String filename, String sheetname, int colno) {
		ArrayList value = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(filename);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetname);
			
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

			for (int i = 0; i < rowCount+1; i++) {
				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.getCell(colno);
				try {
					cell.setCellType(CellType.STRING);
					value.add(cell.getStringCellValue());
				} catch (Exception e) {
					cell.setCellType(CellType.NUMERIC);
					value.add(cell.getNumericCellValue());
				}
	//			value.add(cell);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}
	

	public ArrayList<String> readWebSiteTable() {

		ArrayList<String> websiteList = new ArrayList<String>();
		for (int i = 2; i < websiteList.size(); i++) {
			ArrayList<String> tableList = new ArrayList<String>();

			for (int j = 1; j < tableList.size(); j++) {
				WebElement cell = driver
						.findElement(By.xpath("//table[@class='table table-hover']//tr[" + i + "]//td[" + j + "]"));
				tableList.add(cell.getText());
			}
			websiteList.addAll(tableList);
		}
		System.out.println(websiteList);
		return websiteList;
	}
	

	@AfterTest
	public void closeDriver() {
		driver.close();
	}
}
