package utils;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	
	public ExcelUtils(String excelPath, String sheetName) {		
		try {
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		} catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Object getCellData(int row, int column) throws IOException{
			DataFormatter formatter = new DataFormatter();
			Object value = formatter.formatCellValue(sheet.getRow(row).getCell(column));
			return value;
	}
	
	public static boolean newSheet(String sh) {
		Boolean x = true;
		sheet = workbook.getSheet(sh);
		try {
			Object obj = getCellData(0,0);
		} catch(Exception e) {
			x = false;
		}
		return x;
	}
	
	public static int getRowCount() {
			int rows = sheet.getPhysicalNumberOfRows();
			return rows;
	}
	
	public static void close() throws IOException {
		workbook.close();
	}
}
