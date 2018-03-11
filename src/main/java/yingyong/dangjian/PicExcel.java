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

public class PicExcel {

		public static void main(String[] args) {
			try{
				ex2003();
			}catch(Exception e){
				e.printStackTrace();
			} 
			
			  System.out.println("导入完成");   
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

		
		public static void ex2010() throws Exception{
			 InputStream inp = new FileInputStream("C:\\Users\\Administrator\\Desktop\\红山区机关单位干部名册201706(不包括处级单位)(1).xls");  
			  XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inp);  
			  XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);  
			        
			     for (XSSFShape shape : sheet.getDrawingPatriarch().getShapes()) {  
			    	 XSSFClientAnchor anchor = (XSSFClientAnchor) shape.getAnchor();  
			         if (shape instanceof XSSFPicture) {  
			        	 XSSFPicture pic = (XSSFPicture) shape;  
			                int rowIdx = anchor.getRow2();  
			                int colIdx = anchor.getCol2();
			                Row row = sheet.getRow(rowIdx);
			                List<String> list = new ArrayList<String>();
			                Map<String,String> map = new HashMap<String,String> ();
			                
			                list.add("XM");
			                list.add("XB");
			                list.add("MZ");
			                list.add("CSNY");
			                list.add("NL");
			                list.add("CJGZSJ");
			                list.add("RDSJ");
			                list.add("FKSJ");
			                list.add("ZKSJ");
			                list.add("XZSJ");
			                list.add("TYJBSJ");
			                list.add("TYGWSJ");
			                list.add("QRZJY_XL");
			                list.add("QRZ_ZY");
			                list.add("ZZZGXL");
			                list.add("ZZ_ZY");
			                list.add("GZDW");
			                list.add("ZW");
			                list.add("GRJL");
			                list.add("JG");
			                list.add("CSD");
			                list.add("BZ");
			                list.add("BIANZHI");
			                list.add("NAME");
			                list.add("NAME_JC");
			                
			                for(int i = 1;i<24 ; i++){
			                	Cell cell = row.getCell(i);
			                	cell.setCellType(Cell.CELL_TYPE_STRING);
			                	map.put(list.get(i-1), cell.getStringCellValue());
			                } 
			                XSSFPictureData picData = pic.getPictureData();  
				            String photo = savePic(picData);
			                map.put("PHOTO", photo);
			                map.put("GBGL_ID", UUID.randomUUID().toString());
			                new JDBC().insert("TB_GBGL", map);
			              
			            }  
			      }
		}
		
		/*
		 * 
		 * 
		 */
		public static void ex2003() throws Exception{
			
			JDBC jdbc = new JDBC();
			InputStream inp = new FileInputStream("C:\\Users\\Administrator\\Desktop\\dangjian.xls");  
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
			                list.add("CSNY");
			                list.add("NL");
			                list.add("CJGZSJ");
			                list.add("RDSJ");
			                list.add("FKSJ");
			                list.add("ZKSJ");
			                list.add("XZSJ");
			                list.add("TYJBSJ");
			                list.add("TYGWSJ");
			                list.add("QRZJY_XL");
			                list.add("QRZ_ZY");
			                list.add("ZZZGXL");
			                list.add("ZZ_ZY");
			                list.add("GZDW");
			                list.add("ZW");
			                list.add("GRJL");
			                list.add("JG");
			                list.add("CSD");
			                list.add("BZ");
			                list.add("BIANZHI");
			                list.add("NAME");
//			                list.add("NAME_JC");
			                list.add("ORDER_BY");
			                for(int i = 1;i<26; i++){
			                	Cell cell = row.getCell(i);
			                	cell.setCellType(Cell.CELL_TYPE_STRING);
			                	map.put(list.get(i-1), cell.getStringCellValue());
			                } 
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
