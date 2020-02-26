package excelWord.fagai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SuppliesExcelChecker {
	
	public static String TEMPPATH = "C:\\Users\\Administrator\\Desktop\\格式规则.xlsx";
//	public static String TARGETPATH = "C:\\Users\\Administrator\\Desktop\\格式测试.xlsx";
	public static String TARGETPATH = "F:\\fagai\\20200131\\防疫\\旗县区+本级物资\\20200226";
	public static void checkExcel(String tempPath,File targetFile) throws FileNotFoundException, IOException {
		//模板文件
		XSSFWorkbook ruleWorkbook = new XSSFWorkbook(new FileInputStream(tempPath));
		//模板sheet
		XSSFSheet ruleSheet = ruleWorkbook.getSheetAt(0);
		//待检文件
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(targetFile));
		//待检sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 获取sheet1中的总行数
		int rowCount = ruleSheet.getLastRowNum();
		//获取总列数
		int columnCount = ruleSheet.getRow(0).getPhysicalNumberOfCells();
		
//		System.out.println("模板行数为："+rowCount+"列数为："+columnCount);
		boolean checkResult =false;
		for (int i = 0; i <= rowCount; i++) {
			// 获取第i列的row对象
			XSSFRow ruleRow = ruleSheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				if(ruleRow == null) {//行为空，不进行校验
					continue;
				}
				if(ruleRow.getCell(j) == null){//单元格为空，不进行校验
						continue;
					}else{
						try{
							checkResult = targetCheck(sheet,i,j,ruleRow.getCell(j));
						}catch(Exception e){
							System.out.println(j+"-"+j);
							e.printStackTrace();
						}
						
					}
//					if(!checkResult){//有问题则停止检查
//						break;
//					}
				}
 
				
		}
		
		//关闭，释放资源
		ruleWorkbook.close();
		workbook.close(); 
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
		int count = 0;
		File f = new File(TARGETPATH);
		if(f.isFile()&&f.getName().endsWith("xlsx")){
			System.out.println("开始检查文件: "+f.getName());
			checkExcel(TEMPPATH,f);
		}
		if(f.isDirectory()){
			File[] fileList = f.listFiles();
			for(File file:fileList){
				if(file.getName().endsWith("xlsx")){
					System.out.println("开始检查文件: "+file.getName());
					checkExcel(TEMPPATH,file);
					count++;
				}else{
					System.out.println("文件:"+file.getName()+"不进行检查");
				}
			}
		}
		System.out.println("检查完成,共检查 "+count+" 个文件");
//		checkExcel(TEMPPATH,TARGETPATH);
		
//		EgAnalyser.egAnalyse(null);
	} 
	
}
