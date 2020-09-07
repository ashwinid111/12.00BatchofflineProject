package com.jbk.testClasses;


/*
 * Author Sonu
 */


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
			for (int j = 1; j < row; j++) {
				expListCol.add(sh.getCell(colNo, j).getContents());
			}
			System.out.println(expListCol);
			System.out.println(expListCol.size());

			return expListCol;
		}

		// put rowno and column no and get all data
		public static ArrayList<String> getDataFromExcel1(String fileName, String sheetName, int rowCount, int columncount)
				throws Exception {

			FileInputStream fis = new FileInputStream(fileName);
			Workbook workbook = Workbook.getWorkbook(fis);
			Sheet sh = workbook.getSheet(sheetName);
			int rows = sh.getRows();
			System.out.println(rows);
			int cols = sh.getColumns();
			System.out.println(cols);
			String txt = null;

			ArrayList<String> expected = new ArrayList<String>();
			for (int j = 0; j < columncount; j++) {

				for (int i = 0; i < rowCount; i++) {

					txt = sh.getCell(j, i).getContents();
					System.out.print("       " + txt);
				}
				System.out.println("");
			}
			expected.add(txt);
			return expected;

		}

}
