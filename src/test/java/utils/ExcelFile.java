package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import drinks.Drink;

public class ExcelFile {
	
	private static final Comparator<Drink> BY_POPULARITY = (a,b) -> Integer.compare(a.getPopularity(), b.getPopularity());
	private static ArrayList<Drink> temp = new ArrayList<Drink>();
	private static ArrayList<Drink> drinks = new ArrayList<Drink>();
	private static Map<String, ArrayList> overall = new LinkedHashMap<String, ArrayList>();
	
	public static void init() throws IOException{
		String excelPath = System.getProperty("user.dir") + "/data/drinks.xlsx";
		String sheet = "1";
		ExcelUtils excel = new ExcelUtils(excelPath, sheet);
		
		int i = 0, price = 0, popularity = 0; String name = null, type = null, SheetName;
		while (excel.newSheet(Integer.toString(++i)) == true) {
			SheetName = (String) excel.getCellData(0, 0);
			for (int row=1; row<excel.getRowCount(); row++) {
				try {
					name = (String)excel.getCellData(row, 0);
					price = Integer.parseInt((String) excel.getCellData(row, 1));
					type = (String)excel.getCellData(row, 2);
					popularity = Integer.parseInt((String) excel.getCellData(row, 3));
					
				} catch (Exception e) {
					break;
				}
				temp.add(new Drink(name, price, type, popularity));
			}
			drinks = (ArrayList<Drink>) temp.clone();
			Collections.sort(drinks, BY_POPULARITY);
			overall.put(SheetName, drinks);
			temp.clear();
			
		}
		excel.close();
	}
	
	public static Map<String, ArrayList> returnAll() throws IOException{
		init();
		return overall;
	}
}
