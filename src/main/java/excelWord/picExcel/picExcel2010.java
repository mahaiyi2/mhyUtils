package excelWord.picExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class picExcel2010 {
	public static void main(String[] args) throws Exception {
		  InputStream inp = new FileInputStream("C:\\Users\\Administrator\\Desktop\\222.xlsx");  
		     XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inp);  
		        //List<XSSFPictureData> pictures = workbook.getAllPictures();  
		        XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);  
		        List<XSSFShape> shapes = null;
		        //poi 3.9写法
		        for (POIXMLDocumentPart dr : sheet.getRelations()) {  
		            if (dr instanceof XSSFDrawing) {  
		                XSSFDrawing drawing = (XSSFDrawing) dr;  
		               shapes = drawing.getShapes();  
		            }
		        }
		        //poi 3.15写法
//		        shapes = sheet.getDrawingPatriarch().getShapes();
		        
		        for (XSSFShape shape : shapes) {  
		            XSSFClientAnchor anchor = (XSSFClientAnchor) shape.getAnchor();  
		         if (shape instanceof XSSFPicture) {  
		                XSSFPicture pic = (XSSFPicture) shape;  
		                //shape.get
		                int row = anchor.getRow2();  
		                int col = anchor.getCol2();
		                //sheet.getRow(row).getCell(col);
		                
		             System.out.println("--->" + anchor.getRow2() + ":"  + anchor.getCol2()); 
		             //map.put(row+":"+col, row+":"+col);
		            // int pictureIndex = pic.getPictureIndex()-1;  
		             XSSFPictureData picData = pic.getPictureData();//pictures.get(pictureIndex);  
		            // System.out.println("--->" + picData); 
		            // map.put(row+":"+col, picData);
		             System.out.println(row +" : " + col + picData);
		             savePic(UUID.randomUUID().toString(), picData);  
		            }  
		      } 
		     
		     //System.out.println(map);
		 }
	
	
	private static void savePic(String i, PictureData pic) throws Exception {  
        String ext = pic.suggestFileExtension();  
        byte[] data = pic.getData();  
        if (ext.equals("jpeg")) {  
            FileOutputStream out = new FileOutputStream("E:\\1\\" + i + ".jpg"); 
            out.write(data);  
            out.close();  
         File file = new File("E:\\1\\" + i + ".jpg");
         //FileInputStream in = new FileInputStream(file);
         //System.out.println("in===>"+in);
         if(file.isFile()){
          //file.delete();
          System.out.println("=============delete");
         }
         
        }  
       /* if (ext.equals("png")) {  
            FileOutputStream out = new FileOutputStream("F:\\" + i + ".jpg");  
            out.write(data);  
            out.close();  
        }  */
 } 
}
