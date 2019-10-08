package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TempUtil {

	/**
	 * 
	 * @author mhy
	 * @date:   2019年3月17日 下午5:23:32
	 * @param contentObj 内容參數
	 * @param resourceLoaderClass 加載模板class路徑
	 * @param basePackagePath 路徑
	 * @param tempName 模板名稱
	 * @param outPath 輸出文件位置
	 * @return
	 */
	public static boolean genFromTemp(Map<String,Object> contentObj,Class resourceLoaderClass, 
			String basePackagePath,String tempName,String outPath,String outFileName){
		Template t=null; 
    	Configuration conf = new Configuration(Configuration.VERSION_2_3_26);
    	conf.setClassForTemplateLoading(resourceLoaderClass, basePackagePath);
    	conf.setDefaultEncoding("UTF-8");
    	try {
			t =conf.getTemplate(tempName);
			File dir = new File(outPath);
			if(!dir.exists()) {
				dir.mkdirs();
				
			}
			File outFile = new File(outPath+File.separator+outFileName);
        	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
        	t.process(contentObj, out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("执行失败！！");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
