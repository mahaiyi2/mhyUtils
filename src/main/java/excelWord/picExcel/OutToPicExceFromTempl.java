package excelWord.picExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
/**
 * 根据模板生成excel
 * @author Administrator
 *
 */
public class OutToPicExceFromTempl {
	
	 public static void main(String[] args) {  
		 FileOutputStream fileOut = null;    
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray    
        try {  
            HSSFWorkbook wb = new HSSFWorkbook(OutToPicExceFromTempl.class.getResourceAsStream("gbrmspbTemplate.xls"));     
               
            HSSFSheet sheet1 = wb.getSheet("正面");
            sheet1.getRow(1).getCell(1).setCellValue("张三3");
            
            
            HSSFSheet sheet2 = wb.getSheet("反面");
            
            //sheet2.shiftRows(startRow, endRow, n, copyRowHeight, resetOriginalRowHeight);
            sheet2.shiftRows(14, 22, 1, true, true, true);
            sheet2.shiftRows(13, 13, 1, true, false, true);
            
            HSSFRow row = sheet2.createRow(14);
            //row.getCell(1).getp
            //row.createCell(1).setCellValue("66666");
            // 写入excel文件     
            
            fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\ExcelTTT.xls");
             wb.write(fileOut);     
             System.out.println("----Excle文件已生成------");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            if(fileOut != null){  
                 try {  
                    fileOut.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
	 
	 public static byte[] getBytes(String filePath){  
	        File file = new File(filePath);  
	        FileInputStream fi = null;
	        long fileSize = file.length();  
	        byte [] buffer = new byte[(int) fileSize]; 
	        if (fileSize > Integer.MAX_VALUE) {  
	            System.out.println("file too big...");  
	            return null;  
	        }  
	        try{
	        	fi = new FileInputStream(file);  
		         
		        int offset = 0;  
		        int numRead = 0;  
		        while (offset < buffer.length  
		        && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {  
		            offset += numRead;  
		        }
		     // 确保所有数据均被读取  
		        if (offset != buffer.length) {  
		        throw new IOException("Could not completely read file "  
		                    + file.getName());  
		        }  
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally{
	        	if(fi != null){
	        		try {
						fi.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
	        return buffer;  
	    }  
		}
	
	
// 关于HSSFClientAnchor(dx1,dy1,dx2,dy2,col1,row1,col2,row2)的参数，有必要在这里说明一下：
// dx1：起始单元格的x偏移量，
// dy1：起始单元格的y偏移量，
// dx2：终止单元格的x偏移量，
// dy2：终止单元格的y偏移量，
// col1：起始单元格列序号，从0开始计算；
// row1：起始单元格行序号，从0开始计算，
// col2：终止单元格列序号，从0开始计算；
// row2：终止单元格行序号，从0开始计算，
//添加多个图片时:多个pic应该share同一个DrawingPatriarch在同一个sheet里面。