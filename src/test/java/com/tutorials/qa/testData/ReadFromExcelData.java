package com.tutorials.qa.testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadFromExcelData {

	@DataProvider( name = "ninja")
	public static Object [][] getninjadata() throws IOException{
		Object [][] data =ReadFromExcelData.readdata("LoginDList");
		
		return data;
		
	}
	
	public static Object [][]readdata(String Sheetname) throws IOException {
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\tutorials\\qa\\testData\\testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(ip);
		XSSFSheet sheet = workbook.getSheet(Sheetname);
		
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for (int i =0; i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1); // because row one does not have data. data starts from row 2
			
			for (int j= 0; j<cols ; j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
		
		
	}
}
