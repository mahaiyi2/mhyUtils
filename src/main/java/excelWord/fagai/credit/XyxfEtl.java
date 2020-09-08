package excelWord.fagai.credit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

//C:\\Users\\Administrator\\Desktop\\测试\\信用修复类.xlsx
//C:\\Users\\Administrator\\Desktop\\测试\\4.“失信主体主动参加信用修复培训的案例”反馈模板.xlsx
public class XyxfEtl {
	public static final String FOLD_PATH = "F:\\fagai\\20200701\\报送国家\\信用修复\\";
	
	List<Map<String,Object>> list_pxal = new ArrayList(); //4.“失信主体主动参加信用修复培训的案例”反馈模板
	List<Map<String,Object>> list_xycn = new ArrayList(); //6.“失信主体在信用中国或信用网站主动开展信用承诺的案例”反馈模板
	List<Map<String,Object>> list_pxsl = new ArrayList(); //12.“参加信用修复培训企业数”反馈模板
	List<Map<String,Object>> list_xybg = new ArrayList(); //13.“失信主体提交信用报告情况”反馈模板
	List<Map<String,Object>> list_wcxf = new ArrayList(); //15.“完成信用修复企业”反馈模板
	
	private  void doEtl(String path) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(path));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow titleRow = sheet.getRow(0);
		int rowCount = sheet.getLastRowNum();
		int columnCount = titleRow.getLastCellNum();
		List<Map<String,Object>> list = new ArrayList();
		
		for(int i=1;i<=rowCount;i++){//不算标题的每行
			int c = 1;
			XSSFRow row = sheet.getRow(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("qymc",excelValue(row.getCell(c++),false));		//企业名称
			map.put("tyshxydm",excelValue(row.getCell(c++),false));	//统一社会信用代码
			map.put("sxxw",excelValue(row.getCell(c++),false));		//失信行为
			map.put("sxlx",excelValue(row.getCell(c++),false));		//失信类型
			map.put("xyxffs",excelValue(row.getCell(c++),false));		//信用修复方式
			map.put("cfsj",excelValue(row.getCell(c++),true));			//处罚时间
			map.put("wcxyxfsj",excelValue(row.getCell(c++),true));		//完成信用修复时间
			map.put("xycngswz",excelValue(row.getCell(c++),false));		//信用承诺公示网址
			map.put("xyxfpxmc",excelValue(row.getCell(c++),false));		//信用修复培训名称
			map.put("pxsj",excelValue(row.getCell(c++),true));			//培训时间
			map.put("pxjbbm",excelValue(row.getCell(c++),false));		//培训举办部门
			map.put("pxnrhjg",excelValue(row.getCell(c++),false));			//培训内容
			
			map.put("flag",excelValue(row.getCell(0),false));		//模板标记mhy开头
			//判断是否加载如相应的模板
			createParamList(map);
		}
		//生成相应的模板
		createTemplate();
		
	}
	/**
	 * 读取excel的值做类型判断
	 * @param cell
	 * @param date 是否为日期类型
	 * @return
	 */
	private Object excelValue(XSSFCell cell,boolean date){
		
		Object v = null;
		if(cell!=null){
			switch (cell.getCellType()) {
			case NUMERIC:
				v = date ? cell.getDateCellValue():cell.getNumericCellValue();
				break;
			case STRING:
				v = cell.getStringCellValue();
				break;
			case BLANK:
				break;

			default:
				break;
			} cell.getCellType();
		}
		
		return v;
	}
	//生成相应的模板
	private void createTemplate() throws Exception{
		 
		 Map<String,List> map = new HashMap<>();
		 map.put("4.“失信主体主动参加信用修复培训的案例”反馈模板.xlsx", this.list_pxal);
		 map.put("6.“失信主体在信用中国或信用网站主动开展信用承诺的案例”反馈模板.xlsx", this.list_xycn);
		 map.put("12.“参加信用修复培训企业数”反馈模板.xlsx", this.list_pxsl);
		 map.put("13.“失信主体提交信用报告情况”反馈模板.xlsx", this.list_xybg);
		 map.put("15.“完成信用修复企业”反馈模板.xlsx", this.list_wcxf);
		 
		 for(Entry<String,List> e :map.entrySet()){
			 
			 if(e.getValue()==null || e.getValue().size()<1){continue;}
			 
			 Context context = new Context();
			 context.putVar("list", e.getValue());
//			 context.putVar("title", "无标题"); 
			 context.putVar("dateFormat", new SimpleDateFormat("yyyy-MM-dd"));
			//载入模板
			 InputStream is = new FileInputStream(FOLD_PATH+"模板\\"+e.getKey());
			 OutputStream os = new FileOutputStream(FOLD_PATH+"生成\\"+e.getKey());
			 JxlsHelper.getInstance().processTemplate(is, os, context); 
			 os.flush();
			 os.close();
			 is.close();
		 }
		  
		
	}
	/**
	 * 通过标识判断是否将该条数据导入对应的模板中
	 * @param map
	 */
	private void createParamList(Map<String,Object> map){
		
	
		//默认全部完成修复
		this.list_wcxf.add(map);
		//信用承诺
		if(map.get("xycngswz")!=null && ((String)map.get("xycngswz")).trim().length()>0){
			this.list_xycn.add(map);
		}
		//信用修复培训
		if(map.get("xyxfpxmc")!=null && ((String)map.get("xyxfpxmc")).trim().length()>0){
			this.list_pxal.add(map);
			this.list_pxsl.add(map);
		}
				
		String flag = map.get("flag")==null?"":(String)map.get("flag");
		if(flag.contains("#报告")){
			this.list_xybg.add(map);
		}
		
	}
	

	public static void main(String[] args) throws Exception {
		new XyxfEtl().doEtl(FOLD_PATH+"信用修复类.xlsx");
	} 
	
}
