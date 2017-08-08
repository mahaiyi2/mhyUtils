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
public class OutToPicExce2 {
	
	 public static void main(String[] args) {  
         FileOutputStream fileOut = null;     
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray    
        try {  
           
            HSSFWorkbook wb = new HSSFWorkbook();     
            HSSFSheet sheet1 = wb.createSheet("test picture");   
            HSSFRow row = sheet1.createRow(0);
            row.setHeightInPoints(85);
            HSSFCell c0 = row.createCell(1);   
            c0.setCellValue(new HSSFRichTextString("姓名"));   
            HSSFCell c2 = row.createCell(2);   
            c2.setCellValue(new HSSFRichTextString("性别"));   
            HSSFCell c3 = row.createCell(3);   
            c3.setCellValue(new HSSFRichTextString("年龄"));   
            HSSFCell c4 = row.createCell(4);   
            c4.setCellValue(new HSSFRichTextString("2015年分数"));   
            HSSFCell c5 = row.createCell(7);   
            c5.setCellValue(new HSSFRichTextString("2014年分数"));   
            HSSFRow row1 = sheet1.createRow(1);   //第二行
            HSSFCell c6 = row1.createCell(4);   
            c6.setCellValue(new HSSFRichTextString("语文"));   
            HSSFCell c7 = row1.createCell(5);   
            c7.setCellValue(new HSSFRichTextString("数学"));   
            HSSFCell c8 = row1.createCell(6);   
            c8.setCellValue(new HSSFRichTextString("外语"));  
            HSSFCell c9 = row1.createCell(7);   
            c9.setCellValue(new HSSFRichTextString("语文"));   
            HSSFCell c10 = row1.createCell(8);   
            c10.setCellValue(new HSSFRichTextString("数学"));   
            HSSFCell c11 = row1.createCell(9);   
            c11.setCellValue(new HSSFRichTextString("外语"));  
            c0.setCellValue(new HSSFRichTextString("学号")); 
            //合并单元格
            sheet1.addMergedRegion(new CellRangeAddress(0, 0, 1, 10));
            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）  
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();     
            //anchor主要用于设置图片的属性  
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255,(short) 0, 0, (short) 0, 0);     
            //anchor.setAnchorType(3);     
            //插入图片    
            patriarch.createPicture(anchor, wb.addPicture( getBytes("C:\\Users\\Administrator\\Desktop\\3.jpg"), HSSFWorkbook.PICTURE_TYPE_JPEG));   
            fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\Excel2.xls");     
            // 写入excel文件     
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