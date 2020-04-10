package excelWord.fagai;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UpdateValidate {
	
	public static final String TMEP_PATH="C:\\Users\\Administrator\\Desktop\\测试\\1.“开展行业信用监管的领域”反馈模板.xlsx";
	public static final String UPLOAD_PATH="C:\\Users\\Administrator\\Desktop\\测试\\上传文件\\1.“开展行业信用监管的领域”反馈模板.xlsx";
	public static final String ERROR_PATH="C:\\Users\\Administrator\\Desktop\\测试\\错误\\错误信息.xlsx";
	
	List<Role> roleList = null;
	boolean flag = true;
	void readRole(String tempPath) throws FileNotFoundException, IOException{
		//模板文件
		XSSFWorkbook ruleWorkbook = null;
		ruleWorkbook = new XSSFWorkbook(new FileInputStream(tempPath));
		//模板sheet
		XSSFSheet ruleSheet = ruleWorkbook.getSheetAt(0);
		XSSFRow ruleRow = ruleSheet.getRow(0);
		if(ruleRow!= null){
			roleList = new ArrayList<UpdateValidate.Role>();
			int count = ruleRow.getLastCellNum();
			for(int i=0;i<count;i++){
				XSSFCell roleCell = ruleRow.getCell(i);
				if(roleCell!=null){
					String roleValue = roleCell.getStringCellValue();
					Role role = new Role();
					role.title = roleValue.replace("*","");
					role.notNull = roleValue.contains("*");
					roleList.add(role);
				}else{
					roleList.add(null);
				}
			}
		}
		ruleWorkbook.close();
	}
	class Role{ String title; boolean notNull;}
	
	boolean doCheck(String uploadPath) throws FileNotFoundException, IOException{
		if(roleList==null||roleList.size()<1) return true;

		//模板文件
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(uploadPath));
		//模板sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow titleRow = sheet.getRow(0);
		
		int rowCount = sheet.getLastRowNum();
		int colCount = titleRow.getLastCellNum();
		
		if(colCount!= roleList.size()){
			formatCell(workbook, sheet, titleRow.getCell(0), "表头与模板不一致");
		}
		
		for(int i=0;i<roleList.size();i++){//验证表头待完善
			XSSFCell cell = titleRow.getCell(i);
			if(roleList.get(i)!=null && !(cell.getStringCellValue().replace("*","").equals(roleList.get(i).title))){
				formatCell(workbook, sheet, titleRow.getCell(i), "表头与模板不一致");
			}
		}
		
		
		for(int j=1;j<=rowCount;j++){//验证内容待完善
			XSSFRow dataRow = sheet.getRow(j);
			if(dataRow==null ){
				sheet.createRow(j).createCell(0);
				dataRow = sheet.createRow(j);
				formatCell(workbook, sheet, dataRow.createCell(0), "本行格式错误");
				flag = false;
			}
			for(int i=0;i<roleList.size();i++){
				Role role = roleList.get(i);
				if(role == null || !role.notNull) continue;
				XSSFCell dataCell = dataRow.getCell(i);
				if(dataCell==null){
					formatCell(workbook, sheet, dataRow.createCell(i), "必填项不可为空");
					flag = false;
				}else if(dataCell.getCellType() ==CellType.BLANK){
					formatCell(workbook, sheet, dataCell, "必填项不可为空");
					flag = false;
				}
				
			}
		}
		
		workbook.write(new FileOutputStream(ERROR_PATH));
		workbook.close();
		return flag;
		
		
	}
	
	void formatCell(XSSFWorkbook workbook,XSSFSheet sheet,XSSFCell cell,String errMsg){
		
		CellStyle redCellStyle = workbook.createCellStyle();
//		redCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
		redCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		redCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(redCellStyle);
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置，详见文档
        XSSFComment comment = drawing.createCellComment(new XSSFClientAnchor(0,0,0,0, (short)4, 2 ,(short) 6, 6));
        XSSFRichTextString rtf = new XSSFRichTextString(errMsg);
        XSSFFont commentFormatter = workbook.createFont();
        commentFormatter.setFontName("宋体");
        //设置字体大小
        commentFormatter.setFontHeightInPoints((short) 16);
        rtf.applyFont(commentFormatter);
        comment.setString(rtf);
        comment.setAuthor("信用赤峰");
        cell.setCellComment(comment);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		UpdateValidate uv = new UpdateValidate();
		uv.readRole(TMEP_PATH);
		uv.doCheck(UPLOAD_PATH);
	}
}
