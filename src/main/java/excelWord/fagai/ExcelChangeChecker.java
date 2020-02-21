package excelWord.fagai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelChangeChecker {
	
	public static String PATH_OLD =  "F:\\fagai\\20200131\\防疫\\旗县区+本级物资\\20200221";
//	public static String TARGETPATH = "C:\\Users\\Administrator\\Desktop\\格式测试.xlsx";
	public static String PATH_NEW = "F:\\fagai\\20200131\\防疫\\旗县区+本级物资\\20200222";
	public static String RESULT_FOLDER = PATH_NEW+"\\对比结果";
	static int[][] iii={//起始行，起始列，结束行，结束列
			{4,4,20,4},
			{4,5,20,5},
			{4,6,20,6}
	};
	
	public static void checkExcel(File oldF,File newF) throws FileNotFoundException, IOException {
		XSSFWorkbook oldWorkbook = new XSSFWorkbook(new FileInputStream(oldF));
		XSSFSheet oldSheet = oldWorkbook.getSheetAt(0);
		XSSFWorkbook newWorkbook = new XSSFWorkbook(new FileInputStream(newF));
		XSSFSheet newSheet = newWorkbook.getSheetAt(0);
		//红色字
		XSSFCellStyle redStyle = newWorkbook.createCellStyle();
		XSSFFont redFont = newWorkbook.createFont();
		redFont.setFontHeightInPoints((short)18);
		redStyle.setFont(redFont);
		redFont.setColor(Font.COLOR_RED);
		redStyle.setFont(redFont);
		//绿色字
		XSSFCellStyle greenStyle = newWorkbook.createCellStyle();
		XSSFFont greenFont = newWorkbook.createFont();
		greenFont.setColor((short)3);
		greenFont.setFontHeightInPoints((short)18);
		greenStyle.setFont(greenFont);
		
		for(int[]ii:iii){//{4,4,20,4},
			for(int i = ii[0];i<ii[2];i++){
				int rowNum = i-1,colNum = ii[1]-1;
				XSSFRow oldRow = oldSheet.getRow(rowNum);
				XSSFCell oldCell =  oldRow.getCell(colNum);
				XSSFRow newRow = newSheet.getRow(rowNum);
				XSSFCell newCell =  newRow.getCell(colNum);
				
				double oldV = 0;
				double newV = 0;
				if(oldCell != null && oldCell.getCellType().equals(CellType.NUMERIC)){
					oldV = oldCell.getNumericCellValue();
				}
				if(newCell != null && newCell.getCellType().equals(CellType.NUMERIC)){
					newV = newCell.getNumericCellValue();
				}
				if(newV!=oldV){//添加注释
					String zjs = newV>oldV?"\r\n增加: ":"\r\n减少: ";
					
					XSSFDrawing drawing = newSheet.createDrawingPatriarch();
			        // 定义注释的大小和位置，详见文档
			        XSSFComment comment = drawing.createCellComment(new XSSFClientAnchor(0,0,0,0, (short)4, 2 ,(short) 6, 5));
			        XSSFRichTextString rtf = new XSSFRichTextString("原值: " + oldV+zjs+Math.abs(newV-oldV));
			        XSSFFont commentFormatter = newWorkbook.createFont();
			        commentFormatter.setFontName("宋体");
			        //设置字体大小
			        commentFormatter.setFontHeightInPoints((short) 16);
			        rtf.applyFont(commentFormatter);
			        comment.setString(rtf);
			        comment.setAuthor("mhy");
			        newCell.setCellComment(comment);
				}
				if(newV > oldV){//字体颜色
					newCell.setCellStyle(redStyle);
				}else if(newV<oldV){//字体颜色
					newCell.setCellStyle(greenStyle);
				}
				
			}
		}
		File resultFolder = new File(RESULT_FOLDER);
		if(!resultFolder.exists()){
			resultFolder.mkdirs();
		}
		File resultFile = new File(RESULT_FOLDER,"对比结果-"+newF.getName());
		if(!resultFile.exists()){
			resultFile.createNewFile();
		}
		newWorkbook.write(new FileOutputStream(resultFile));
		//关闭，释放资源
		oldWorkbook.close();
		newWorkbook.close(); 
	}
	public static boolean targetCheck(XSSFSheet sheet,int rowNum,int colNum,XSSFCell cellChecker) throws FileNotFoundException, IOException{
		
		XSSFRow row = sheet.getRow(rowNum);
		
		if(cellChecker == null){//如果规则不为空才进行检查
			return true;
		}
		//解析单元格中的表达式
		EgAnalyser eg = EgAnalyser.egAnalyse(cellChecker.getStringCellValue());
		if(eg.noRule){//如不需要验证直接返回
			return true;
			}
		
		if(row ==null){//空行直接返回失败
			printError(rowNum,colNum,"该行为空");
			return false;
		}
		
		XSSFCell cell = row.getCell(colNum);
		//数字或者空值
		if(eg.getRule().equals(MhyRule.NUM_NULL.toString())){
			if(cell==null ||cell.getCellType().equals(CellType.BLANK)|| cell.getCellType().equals(CellType.NUMERIC)){
				return true;
			}else{
				printError(rowNum, colNum, "格式错误");
				return false;
			}
		}
		//字符串或者空值
		if(eg.getRule().equals(MhyRule.STRING_NULL.toString())){
			if(cell==null || cell.getCellType().equals(CellType.STRING)){
				return true;
			}else{
				printError(rowNum, colNum, "格式错误");
				return false;
			}
		}
		//非空数字
		if(eg.getRule().equals(MhyRule.NUMBER.toString())){
			if(cell!=null && cell.getCellType().equals(CellType.NUMERIC)){
				return true;
			}else{
				printError(rowNum, colNum, "格式错误");
				return false;
			}
		}
		//非空字符串
		if(eg.getRule().equals(MhyRule.STRING.toString())){
			if(cell!=null && cell.getCellType().equals(CellType.STRING)){
				return true;
			}else{
				printError(rowNum, colNum, "格式错误");
				return false;
			}
		}
		//非空字符串
		if(eg.getRule().equals(MhyRule.STRING.toString())){
			if(cell!=null && cell.getCellType().equals(CellType.STRING)){
				return true;
			}else{
				printError(rowNum, colNum, "格式错误");
				return false;
			}
		}
		//非空字符串
		if(eg.getRule().equals(MhyRule.EQ.toString())){
			if(cell!=null && eg.getTarget().equals(cell.getStringCellValue())){
				return true;
			}else{
				printError(rowNum, colNum, "格式错误");
				return false;
			}
		}
		return true;
	}
	
	
	private static void printError(int rowNum,int colNum,String msg){
		System.out.printf("第%d行，第%d列 : "+msg,rowNum+1,colNum+1);
		System.out.println();
 	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File dirOld = new File(PATH_OLD);
		File dirNew = new File(PATH_NEW);
		int i = 0;
		if(dirOld.isDirectory()){
			for(File oldF:dirOld.listFiles()){
				for(File newF:dirNew.listFiles()){
					if(oldF.isFile() && oldF.getName().endsWith("xlsx") && oldF.getName().equals(newF.getName())){
						
						checkExcel(oldF,newF);
						i++;
						
					}
				}
				
			}
		}
		System.out.println("检查完成");
		System.out.println("共对比文件:" +i+" 个");
//		checkExcel(TEMPPATH,TARGETPATH);
		
//		EgAnalyser.egAnalyse(null);
	} 
	
}
