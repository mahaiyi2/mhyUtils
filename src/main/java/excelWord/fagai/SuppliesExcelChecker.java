package excelWord.fagai;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SuppliesExcelChecker {
	
	public static String TEMPPATH = "C:\\Users\\Administrator\\Desktop\\格式规则.xlsx";
	public static String DESTPATH = "C:\\Users\\Administrator\\Desktop\\格式测试.xlsx";
	public static void checkExcel(String tempPath,String destPath) throws FileNotFoundException, IOException {
		//模板文件
		XSSFWorkbook ruleWorkbook = new XSSFWorkbook(new FileInputStream(tempPath));
		//模板sheet
		XSSFSheet ruleSheet = ruleWorkbook.getSheetAt(0);
		//待检文件
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(DESTPATH));
		//待检sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 获取sheet1中的总行数
		int rowCount = ruleSheet.getLastRowNum();
		//获取总列数
		int columnCount = ruleSheet.getRow(0).getPhysicalNumberOfCells();
		
		System.out.println("模板行数为："+rowCount+"列数为："+columnCount);
		
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
						targetCheck(sheet,i,j,ruleRow.getCell(j));
					}
 
				}
 
				System.out.println("检查完成");
		}
		//关闭，释放资源
		ruleWorkbook.close();
		workbook.close(); 
	}
	public static boolean targetCheck(XSSFSheet sheet,int rowNum,int colNum,XSSFCell cellChecker) throws FileNotFoundException, IOException{
		
		XSSFRow row = sheet.getRow(rowNum);
		
		if(cellChecker != null){//如果规则不为空才进行检查
			cellChecker.getStringCellValue();
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
		//cell为空直接返回失败
		if(cell == null){
			if( eg.getRule().equals(MhyRule.STRING_NULL)||eg.getRule().equals(MhyRule.NUM_NULL)){
				return true;
			}else{
				printError(rowNum,colNum,"必填项为空");
				return false;
			}
		}
		
		return true;
	}
	private static void printError(int rowNum,int colNum,String msg){
		System.out.printf("第%d行，第%d列有不符合格式: "+msg);
 	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
//		checkExcel(TEMPPATH,"");
		EgAnalyser.egAnalyse(null);
	} 
	
}
