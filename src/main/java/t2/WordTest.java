package t2;

import java.io.BufferedWriter;  
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.List;  
import java.util.Map;  
import freemarker.template.Configuration;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
  
public class WordTest {  
    private Configuration configuration = null;  
      
    public WordTest(){  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");  
    }  
      
    public static void main(String[] args) throws Exception {  
    }  
      
    public void createWord(){  
        Map<String,Object> dataMap=new HashMap<String,Object>();  
        getData(dataMap);  
        configuration.setClassForTemplateLoading(this.getClass(), "/t2");  //FTL文件所存在的位置  
        Template t=null;  
        try {  
            //t = configuration.getTemplate("wordModel.ftl"); //文件名  
        	t = configuration.getTemplate("235.ftl"); //文件名
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        File outFile = new File("C:\\Users\\ab048704\\Desktop\\a.doc");  
        Writer out = null;  
        try {  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));  
        } catch (Exception e1) {
            e1.printStackTrace();  
        }  
           
        try {  
            t.process(dataMap, out);  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        
    }  
  
    private  void getData(Map<String, Object> dataMap) {  
        dataMap.put("v1", "标题abdkj按打瞌睡id打飞机的is点击发送领导方法分级基金的看看历史的姐姐凯乐科技地方lkkjjkfdsldkfjeiqofj"
        		+ "vain啊vliinn看到士大夫阿三地方阿三地方阿三地方阿三地方");  
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String,Object> map = new HashMap<String,Object>();  
            map.put("number", i);  
            map.put("content", "内容"+i);  
            list.add(map);  
        }  
          
    }  
    
}  