package excelWord.fagai;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SuppliesExcelChecker {

	public static void readExcel(String filePath,int sheetNum) throws FileNotFoundException, IOException {
		// 建需要读取的excel文件写入stream
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
					// 指向sheet下标为0的sheet 即第一个sheet 也可以按在sheet的名称来寻找
		XSSFSheet sheet = workbook.getSheetAt(sheetNum);
					// 获取sheet1中的总行数
					int rowCount = sheet.getLastRowNum();
					//获取总列数
					int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
					
					System.out.println("行数为："+rowCount+"列数为："+columnCount);
					
					for (int i = 0; i <= rowCount; i++) {
						// 获取第i列的row对象
						XSSFRow row = sheet.getRow(i);
						
						ArrayList<String> listRow = new ArrayList<String>();
		 
						for (int j = 0; j < columnCount; j++) {
							//下列步骤为判断cell读取到的数据是否为null 如果不做处理 程序会报错
							String cell = null;
							//如果未null则加上""组装成非null的字符串
							if(row == null) {
								System.out.println("第 "+i+"排 "+"为null");
								continue;
							}
							if(row.getCell(j) == null){
								System.out.println("第 "+i+" 排,第 "+j+" 列为null");
								continue;
							//如果读取到额cell不为null 则直接加入	listRow集合
							}else{
								System.out.println("第 "+i+" 排,第 "+j+" 格式为: "+row.getCell(j).getCellType());
							}
		 
						}
		 
		 
					}
		 
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		readExcel("C:\\Users\\Administrator\\Desktop\\格式测试.xlsx",0);
	}
}
