package UIAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class handlingExcelFile {

	@Test(priority = 1, enabled = false)
	public void createExcelWorkBook() throws Exception {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("Trial");
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Hello");
		sheet.getRow(0).createCell(1).setCellValue("World");

		sheet.createRow(1);
		sheet.getRow(1).createCell(0).setCellValue("JY HELLO");
		sheet.getRow(1).createCell(1).setCellValue("Happy New Year");

		File file = new File("./ExcelFile/PracticeTest1.xlsx");
		file.getParentFile().mkdir();
//		workBook.write(file);
		FileOutputStream stream = new FileOutputStream(file);
		workBook.write(stream);
		workBook.close();
	}

	@Test(priority = 2, enabled = false)
	public void readExcelFile() throws IOException {
		File file = new File("./ExcelFile/PracticeTest1.xlsx");
		FileInputStream fi = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workBook.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		for(int i=0;i<rowNum;i++) {
			int cell = sheet.getRow(i).getLastCellNum();
			System.out.println("capacity of cell "+i +"is "+cell);
			for(int j=0;j<cell;j++) {
				String cellValues = sheet.getRow(i).getCell(j).getStringCellValue();
				System.out.println("cellValue of "+j+"is "+cellValues);
			}
		}
		workBook.close();
		fi.close();
	}
	
	@Test(priority = 1)
	public void readExcelDynamically() throws Exception {
		File file = new File("./ExcelFile/PracticeTest1.xlsx");
		FileInputStream fi = new FileInputStream(file);
		XSSFWorkbook workBook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workBook.getSheetAt(0);
		int countRows = sheet.getPhysicalNumberOfRows();
		for(int i=0; i<countRows;i++) {
			Row row = sheet.getRow(i);
			int countCell = row.getPhysicalNumberOfCells();
			for(int j=0;j<countCell;j++) {
				String cellValue = row.getCell(j).getStringCellValue();
				System.out.print("||"+cellValue);
			}
			System.out.println();
		}
		workBook.close();
		fi.close();
	}

}
