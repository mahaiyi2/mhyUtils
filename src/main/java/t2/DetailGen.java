package t2;

import java.io.BufferedReader;
import java.io.BufferedWriter;  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;  
import java.util.Map;  
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
  
public class DetailGen {  
    private Configuration configuration = null;  
      
    public DetailGen(){  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");  
    }  
      
    public static void main(String[] args) throws Exception {  
    	toFormatHtml();
    }  
   
    /**
     * 扶贫列表详情生成方法
     * @throws Exception 
     */
    public static void toFormatHtml() throws Exception{

    	File infile= new File(DetailGen.class.getResource("/t2/text.txt").getFile());
    	FileReader fr = new FileReader(infile);
    	BufferedReader br = new BufferedReader(fr);
    	Map<String,String> mapp = new LinkedHashMap<String,String>();
    	while(true){
    		String s= br.readLine();
    		if( s==null) break;
    		String ss[]=s.split("\\t");
    		mapp.put(ss[1],ss[0]);
    		
    	}
    	br.close();
    	Template t=null; 
    	Configuration conf = new Configuration();
    	conf.setClassForTemplateLoading(DetailGen.class, "/t2");
    	conf.setDefaultEncoding("UTF-8");
    	//t =conf.getTemplate("text.ftl");
    	t =conf.getTemplate("detail.ftl");
    	
    	Map dataMap = new HashMap();
    	dataMap.put("mapp", mapp);
    	File outFile = new File("C:\\Users\\mhy\\Desktop\\text.txt");
    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
    	t.process(dataMap, out);
    	System.out.println("处理完成");
    }
    
}  