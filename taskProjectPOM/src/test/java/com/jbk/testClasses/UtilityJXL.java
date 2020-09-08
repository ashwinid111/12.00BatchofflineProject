package com.jbk.testClasses;

import java.io.FileInputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class UtilityJXL {

	// put RowNo and get the data from this particular rowNo
	public static ArrayList<String> readAnyRowData(String fileName, String sheetName, int rowNo) throws Exception {
		ArrayList<String> expListRow = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		Workbook workbook = Workbook.getWorkbook(fis);
		Sheet sh = workbook.getSheet(sheetName);

		int cols = sh.getColumns();
		System.out.println(cols);
		for (int i = 0; i < cols; i++) {
			expListRow.add(sh.getCell(i, rowNo).getContents());
		}
		System.out.println(expListRow);
		System.out.println(expListRow.size());

		return expListRow;
	}

	// put columnNo and get the data of particular column no
	public static ArrayList<String> readAnyColumnData(String fileName, String sheetName, int colNo) throws Exception {
		ArrayList<String> expListCol = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		Workbook workbook = Workbook.getWorkbook(fis);
		Sheet sh = workbook.getSheet(sheetName);
		int row = sh.getRows();
		System.out.println(row);
		for (int j = 0; j < row; j++) {
			expListCol.add(sh.getCell(colNo, j).getContents());
		}
		System.out.println(expListCol);
		System.out.println(expListCol.size());

		return expListCol;
	}

	// put rowno and column no and get all data

	public static ArrayList<String> getDataFromExcel(String fileName, String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);

		Workbook wb = Workbook.getWorkbook(fis);

		Sheet sh = wb.getSheet(sheetName);

		int row = sh.getRows();

		int col = sh.getColumns();

		System.out.println("rows and cols in sheet with data are: " + row + "  " + col);

		ArrayList<String> alExcepted = new ArrayList<String>();
		for (int i = 0; i < row; i++) {// for rows

			for (int j = 0; j < col; j++) {// for columns

				jxl.Cell cell = sh.getCell(j, i);
				String data = cell.getContents();
				System.out.print(data + "  ");
				alExcepted.add(data);
			}
			System.out.println();
		}
		return alExcepted;
	}
}
