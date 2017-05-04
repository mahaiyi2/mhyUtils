package t2;

import java.io.BufferedReader;
import java.io.BufferedWriter;  
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.util.LinkedHashMap;
import java.util.Map;  
import freemarker.template.Configuration;  
public class SelectStringGen {  
    private Configuration configuration = null;  
      
    public SelectStringGen(){  
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

    	File infile= new File(SelectStringGen.class.getResource("/t2/temp.txt").getFile());
    	FileReader fr = new FileReader(infile);
    	BufferedReader br = new BufferedReader(fr);
    	Map<String,String> mapp = new LinkedHashMap<String,String>();
    	File outFile = new File("C:\\Users\\mhy\\Desktop\\text.txt");
    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
    	while(true){
    		String s= br.readLine();
    		if( s==null) break;
    		out.write(s+",");
    		
    	}
    	br.close();
    	out.close();
    	System.out.println("处理完成");
    }
    
}  