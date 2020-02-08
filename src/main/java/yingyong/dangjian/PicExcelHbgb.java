package yingyong.dangjian;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.JDBC;

public class PicExcelHbgb {

		public static void main(String[] args) throws Exception {
			  ex2003();
			     
			 }
		
		
		private static String savePic( PictureData pic) throws Exception {  
	        String ext = pic.suggestFileExtension();  
	        byte[] data = pic.getData();  
	        String path = "E:\\1\\" ;
	        String photo = "imgs\\photos\\"+UUID.randomUUID().toString()+"."+ext;
	        //if (ext.equals("jpeg")) {  
	            FileOutputStream out = new FileOutputStream(path + photo); 
	            out.write(data);  
	            out.close();  
	        //}  
	        return photo;
	 } 

		
		
		
		/*
		 * 
		 * 
		 */
		public static void ex2003() throws Exception{
			 JDBC jdbc = new JDBC();
			InputStream inp = new FileInputStream("C:\\Users\\Administrator\\Desktop\\2017年7月调整后后备干部名册.xls");  
			 HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(inp);  
			 HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);  
			        int total = 0;
			     for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {  
			    	 HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();  
			         if (shape instanceof HSSFPicture) {  
			        	 HSSFPicture pic = (HSSFPicture) shape;  
			                int rowIdx = anchor.getRow2();  
			                int colIdx = anchor.getCol2();
			                Row row = sheet.getRow(rowIdx);
			                List<String> list = new ArrayList<String>();
			                Map<String,String> map = new LinkedHashMap<String,String>();
			                list.add("XM");
			                list.add("XB");
			                list.add("MZ");
			                list.add("JRFS");
			                list.add("CSNY");
			                list.add("CJGZSJ");
			                list.add("RDSJ");
			                list.add("QRZJY_XL");
			                list.add("QRZJY_XW");
			                list.add("QRZ_ZY");
			                list.add("ZZZGXL");
			                list.add("ZZ_XW");
			                list.add("ZZ_ZY");
			                list.add("GZDW");
			                list.add("GRJL");
			                list.add("PARTY_COMMITTEE_MANAGE_ID");
			                for(int i = 1;i<17; i++){
			                	Cell cell = row.getCell(i);
			                	cell.setCellType(CellType.STRING);
			                	map.put(list.get(i-1), cell.getStringCellValue());
			                } 
			                map.put("GB_LX", "0");
			                HSSFPictureData picData = pic.getPictureData();  
				            String photo = savePic(picData);
			                map.put("PHOTO", photo);
			                map.put("GBGL_ID", UUID.randomUUID().toString());
			                jdbc.insert("TB_GBGL", map);
			            }  
			      }
			     jdbc.close();
		}
}
