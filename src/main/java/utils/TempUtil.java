package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class TempUtil {

	public static boolean genFromTemp(Object contentObj,Class resourceLoaderClass, 
			String basePackagePath,String tempName,String outPath){
		Template t=null; 
    	Configuration conf = new Configuration();
    	conf.setClassForTemplateLoading(resourceLoaderClass, basePackagePath);
    	conf.setDefaultEncoding("UTF-8");
    	try {
			t =conf.getTemplate(tempName);
			HashMap dataMap = new HashMap();
			dataMap.put("obj", contentObj);
			File outFile = new File(outPath);
        	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
        	t.process(dataMap, out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("执行失败！！");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
