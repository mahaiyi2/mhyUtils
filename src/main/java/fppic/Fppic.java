package fppic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;

public class Fppic {
	public static String cunID="150402003007";
	private static String path="E:\\LC\\记录\\研发任务\\扶贫项目\\图片\\二道井子村移民搬迁照片";
	private static String newBasePath="C:\\Users\\mhy\\Desktop\\newBasePath\\";
	
	public static void main(String[] args) throws Exception {
		execute();
		
	}
	public static void execute() throws Exception{
				Map map = getMap();//得到文件名
				
				JDBC jdbc= new JDBC();
				List list = new ArrayList();
				for(Object key : map.keySet()){
					
					Map rMap = (Map)map.get(key);
					if((Boolean)rMap.get("flag")){
						
						Map result = jdbc.getHidAndSfz((String)key);
						
						String ZJHM = (String)result.get("ZJHM");
						String HID = (String)result.get("HID");
						if(ZJHM == null || "".equals(ZJHM.trim())||HID == null || "".equals(HID.trim())){
							System.out.println("--查询问题： " + key);
							continue;
						}
						
						List<File> fileList = (List<File>)rMap.get("fileList");
						for(File file : fileList){
							Map entity = new HashMap();
							String fileName = file.getName();
							String suffix = fileName.substring(fileName.lastIndexOf("."));
							if(".jpg".equals(suffix.toLowerCase())
							|| ".jpeg".equals(suffix.toLowerCase()) 
							|| ".png".equals(suffix.toLowerCase())
							|| ".gif".equals(suffix.toLowerCase())
							|| ".bmp".equals(suffix.toLowerCase())){
								entity.put("FILE_TYPE","img");
							}else{
								entity.put("FILE_TYPE","other");
							}
							entity.put("SUFFIX",suffix);
							entity.put("ZJHM",ZJHM);
							entity.put("HID",HID);
							entity.put("XM",key);
							entity.put("FILE_URL",ZJHM+ "/" + file.getName());
							entity.put("FILE_NAME",fileName);
							transFile(file,ZJHM);
							list.add(entity);
						}
					}
				}
				for(Object key : map.keySet()){
					Map rMap = (Map)map.get(key);
					if(!(Boolean)rMap.get("flag")){
						System.out.println("----问题文件： "+ key);
					}
				}
				//存入模板 
				temp2sql(list);
				jdbc.close();
				
	}
	public static void transFile(File file,String ZJHM) throws IOException{
		try{
			
			File newFile = new File(newBasePath + ZJHM+ File.separator + file.getName());
			 File p = new File(newFile.getParent());
		        if(!p.exists()){
		            p.mkdirs();
		        }
			FileInputStream input = new FileInputStream(file); 
			FileOutputStream output = new FileOutputStream(newFile); 
			byte[] buf = new byte[1024 * 5]; 
			int len; 
			while ( (len = input.read(buf)) != -1) { 
			output.write(buf, 0, len); 
			} 
			output.flush(); 
			output.close(); 
			input.close(); 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	} 
		
	public static Map getMap(){
		//Path p = Paths.get(inpath);
		//File file = p.toFile();
		File file1 = new File(path);
		File[] files1 = file1.listFiles();
		LinkedHashMap map = new LinkedHashMap<String, Boolean>();
		
		if(files1 !=null && files1.length !=0){
			for (File file2 : files1){
				if(file2.isDirectory()){
					File files2[] = file2.listFiles();
					String file2Nmae = file2.getName().replaceAll("[0-9]","");
					List fileList = new ArrayList();
					Map rMap = new HashMap();
					if(files2.length<=0){
						rMap.put("flag", false);
					}else{
						for(File file3 : files2){
							if(!file3.isFile() || !checkName(file2Nmae) || map.containsKey(file2Nmae)){
								rMap.put("flag", false);	
							}else{
								rMap.put("flag",true);
								fileList.add(file3);
							}
							//System.out.println(subFile.getName());
						}
					}
					rMap.put("fileList",fileList);
					map.put(file2Nmae,rMap);
				}
			}
			
		}
		return map;
	}
	public static boolean checkName(String fileName){
		String regEx = "^[\u4E00-\u9FFF]+$";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    // 忽略大小写的写法
	    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
	
	public static void temp2sql(Object obj) throws Exception, MalformedTemplateNameException, ParseException, IOException{
		
    	Template t=null; 
    	Configuration conf = new Configuration();
//    	conf.setClassForTemplateLoading(DetailGen.class, "/fppic");
    	conf.setDefaultEncoding("UTF-8");
    	//t =conf.getTemplate("text.ftl");
    	t =conf.getTemplate("insert2attach.ftl");
    	
    	Map dataMap = new HashMap();
    	dataMap.put("mapp", obj);
    	File outFile = new File("C:\\Users\\mhy\\Desktop\\text.txt");
    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
    	t.process(dataMap, out);
    	System.out.println("处理完成");
	}
	
}
