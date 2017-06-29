package excelWord.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.xssf.usermodel.XSSFCell;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ReadExcel {
	    
	    /**
	     * read the Excel file
	     * @param path the path of the Excel file
	     * @return
	     * @throws IOException
	     */
	    public List<Map> readExcel(String path) throws IOException {
	        if (path == null || Common.EMPTY.equals(path)) {
	            return null;
	        } else {
	            String postfix = Util.getPostfix(path);
	            if (!Common.EMPTY.equals(postfix)) {
	                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
	                    return readXls(path);
	                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
	                    return readXlsx(path);
	                }
	            } else {
	                System.out.println(path + Common.NOT_EXCEL_FILE);
	            }
	        }
	        return null;
	    }
	
	    /**
	     * Read the Excel 2010
	     * @param path the path of the excel file
	     * @return
	     * @throws IOException
	     */
	    public List<Map> readXlsx(String path) throws IOException {
	        System.out.println(Common.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	        List<Map> list = new ArrayList<Map>();
	        // Read the Sheet
	        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
	            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
	            if (xssfSheet == null) {
	                continue;
	            }
	            
	            // Read the Row
	            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
	                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
	                if (xssfRow != null) {
	                	Map map = new HashMap();
	                    XSSFCell no = xssfRow.getCell(0);
	                    map.put("", getValue(no));
	                    list.add(map);
	                }
	            }
	        }
	        return list;
	    }
	
	    /**
	     * Read the Excel 2003-2007
	     * @param path the path of the Excel
	     * @return
	     * @throws IOException
	     */
	    public List<Map> readXls(String path) throws IOException {
	        System.out.println(Common.PROCESSING + path);
	        InputStream is = new FileInputStream(path);
	        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	       
	        List<Map> list = new ArrayList<Map>();
	        // Read the Sheet
	         for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
	             HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
	             if (hssfSheet == null) {
	                 continue;
	             }
	             // Read the Row
	             for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                 HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                 if (hssfRow != null) {
	                	 Map map = new HashMap();
	                     HSSFCell c1 = hssfRow.getCell(0);
	                     HSSFCell c2 = hssfRow.getCell(1);
	                     map.put("c1", getValue(c1));
	                     map.put("c2", getValue(c2));
	                     list.add(map);
	                 }
	             }
	         }
	         return list;
	     }
	 
	     @SuppressWarnings("static-access")
	     private String getValue(XSSFCell xssfRow) {
	         if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
	             return String.valueOf(xssfRow.getBooleanCellValue());
	         } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
	             return String.valueOf(xssfRow.getNumericCellValue());
	         } else {
	             return String.valueOf(xssfRow.getStringCellValue());
	         }
	     }
	 
	     @SuppressWarnings("static-access")
	     private String getValue(HSSFCell hssfCell) {
	         if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	             return String.valueOf(hssfCell.getBooleanCellValue());
	         } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	             return String.valueOf(hssfCell.getNumericCellValue());
	         } else {
	             return String.valueOf(hssfCell.getStringCellValue());
	         }
	     }
	 }