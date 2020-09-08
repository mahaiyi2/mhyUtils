package ytlk;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.FileUtil;
import utils.StringUtil;
import utils.TempUtil;

public class CodeCreater {
	public static final String MODEL_NAME = "credit";
//	public static final String sourceFile = "C:\\Users\\Administrator\\git\\mhyUtils\\src\\main\\java\\ytlk\\source\\core\\attachment";//台式机
	public static final String sourceFile = "F:\\biancheng\\workSpaces\\mhyUtils\\mhyUtils\\src\\main\\java\\ytlk\\source\\credit\\dept";//笔记本
	public static final String templatePathSufix = "";
//	public static final String templatePathSufix = "/tree";//树结构专用
	//包名，如果不写则没
	public static final String PACKAGE_NAME = "";
	public static final String baseOutPath="C:\\Users\\Administrator\\Desktop\\ytlkCode\\";
//	public static final String baseOutPath="C:\\Users\\ab048704\\Desktop\\ytlkCode\\";
	public static void main(String[] args) {
		
		List<String> list = FileUtil.file2List(sourceFile);//读取文件到list
		String theName="";//表明
		String theDes="";//说明
		List<Map<String,String>> fieldList = new ArrayList<>();//
		boolean hasNomal = false;
		for(int i=0;i<list.size();i++) {
			String s = list.get(i);
			String[] ss = s.split(":");
			if(ss[0].equals("#")){//关系表,需要写在普通表下面
				genRelation(list.subList(i+1, list.size()));
				break;
			}
			if(i==0) {//第一行为表明+说明
				theName = ss[0];
				theDes = ss[1];
				hasNomal = true;
			}else {//字段名称
				Map<String,String> m = new HashMap<String,String>();
				m.put("fieldName",StringUtil._2hump(ss[0],false));
				m.put("fieldDes",ss[1]);
				m.put("fieldNameFU",StringUtil._2hump(ss[0],true));
				m.put("column", ss[0].toUpperCase());
				fieldList.add(m);
				
			}
			
		}
		 
		if(!hasNomal)return;//如果只有关系信息则不执行以下操作；
		
		String tblName = theName.toUpperCase();
		String entityName=StringUtil._2hump(theName,true);
		String entityNameFl=StringUtil._2hump(theName,false);
		String controllerName = entityName+"Controller";
		String serviceName = entityName+"Service";
		String serviceImplName = entityName+"ServiceImpl";
		String mapperName = entityName+"Mapper";
		String SPName = entityName+"SP";//sql生成器文件名
		String listPageName = theName + "_list";//列表页面名称
		String editPageName = theName + "_edit";//编辑页面名称
		
		//包名
		String packageName = (PACKAGE_NAME == null||PACKAGE_NAME.trim().equals(""))?null: PACKAGE_NAME;
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("theName", theName);
		param.put("theDes", theDes);
		param.put("tblName", tblName);
		param.put("entityName", entityName);
		param.put("entityNameFl", entityNameFl);
		param.put("controllerName", controllerName);
		param.put("serviceName", serviceName);
		param.put("serviceImplName", serviceImplName);
		param.put("mapperName", mapperName);
		param.put("SPName", SPName);
		param.put("listPageName", listPageName);
		param.put("editPageName", editPageName);
		param.put("fieldList",fieldList);
		param.put("nowDate", new Date());
		param.put("packageName", packageName);
		param.put("modelName", MODEL_NAME);
		
		
		String packageFilePath =   packageName == null?"":"\\"+packageName;
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "entityTemplate.ftl", baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\entity"+packageFilePath,entityName+".java");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "controllerTemplate.ftl", baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\controller"+packageFilePath,controllerName+".java");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "serviceTemplate.ftl", baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\service"+packageFilePath,serviceName+".java");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "serviceImplTemplate.ftl", baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\serviceImpl"+packageFilePath,serviceImplName+".java");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "mapperTemplate.ftl", baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\mapper"+packageFilePath,mapperName+".java");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "SPTemplate.ftl",baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\mapper\\sp"+packageFilePath,SPName+".java");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "mysqlScriptTemplate.ftl", baseOutPath,entityNameFl+"Sql.sql");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "listPageTemplate.ftl", baseOutPath+"ytlkLTE\\pages\\"+MODEL_NAME+"\\"+packageFilePath,listPageName+".html");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "editPageTemplate.ftl",baseOutPath+"ytlkLTE\\pages\\"+MODEL_NAME+"\\"+packageFilePath,editPageName+".html");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "listJsTemplate.ftl", baseOutPath+"ytlkLTE\\pages\\"+MODEL_NAME+"\\js"+packageFilePath,listPageName+".js");
		TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template" + templatePathSufix, "editJsTemplate.ftl", baseOutPath+"ytlkLTE\\pages\\"+MODEL_NAME+"\\js"+packageFilePath,editPageName+".js");
	
		
		System.out.println("完成!!!");
	}
	
public static void genRelation(List<String> list) {
	System.out.println("********************");
	String theName="";//表名
	String theDes="";//说明
	List<Map<String,String>> fieldList = new ArrayList<>();//
	for(int i=0;i<list.size();i++) {
		String s = list.get(i);
		String[] ss = s.split(":");
		if(i==0) {//第一行为表明+说明
			theName = ss[0];
			theDes = ss[1];
		}else {//字段名称
			Map<String,String> m = new HashMap<String,String>();
			m.put("fieldName",StringUtil._2hump(ss[0],false));
			m.put("fieldDes",ss[1]);
			m.put("fieldNameFU",StringUtil._2hump(ss[0],true));
			m.put("column", ss[0].toUpperCase());
			fieldList.add(m);
			
		}
		
	}
	 
	
	String tblName = theName.toUpperCase();
	String entityName=StringUtil._2hump(theName,true);
	String entityNameFl=StringUtil._2hump(theName,false);
	String controllerName = entityName+"Controller";
	String serviceName = entityName+"Service";
	String serviceImplName = entityName+"ServiceImpl";
	String mapperName = entityName+"Mapper";
	String SPName = entityName+"SP";//sql生成器文件名
	String listPageName = theName + "_list";//列表页面名称
	String editPageName = theName + "_edit";//编辑页面名称
	
	Map<String,Object> param = new HashMap<String, Object>();
	param.put("theName", theName);
	param.put("theDes", theDes);
	param.put("tblName", tblName);
	param.put("entityName", entityName);
	param.put("entityNameFl", entityNameFl);
	param.put("controllerName", controllerName);
	param.put("serviceName", serviceName);
	param.put("serviceImplName", serviceImplName);
	param.put("mapperName", mapperName);
	param.put("SPName", SPName);
	param.put("listPageName", listPageName);
	param.put("editPageName", editPageName);
	param.put("fieldList",fieldList);
	param.put("nowDate", new Date());
	param.put("modelName", MODEL_NAME);
	
	
	TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template", "entityTemplateR.ftl", baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\entity",entityName+".java");
	TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template", "mapperTemplateR.ftl", baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\mapper",mapperName+".java");
	TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template", "SPTemplateR.ftl",baseOutPath+"src\\main\\java\\com\\ytlk\\"+MODEL_NAME+"\\mapper\\sp",SPName+".java");
	TempUtil.genFromTemp(param, CodeCreater.class, "/ytlk/template", "mysqlScriptTemplateR.ftl", baseOutPath,entityNameFl+"Sql.sql");
}
	

}
