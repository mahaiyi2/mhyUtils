package fpxuqiu;

import java.io.BufferedReader;
import java.io.BufferedWriter;  
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;  
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import freemarker.template.Configuration;  
import freemarker.template.Template;  
  
public class XuqiuGen {  
    private static boolean mapDup=false;  
    private Configuration configuration = null;  
      
    public XuqiuGen(){  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");  
    }  
      
    public static void main(String[] args) throws Exception {  
    	List list = getList();
    	fupinxuqiu(list);
    }  
    /**
     * 扶贫需求生成
     */
    public static void fupinxuqiu (List<String> list) throws Exception{
    	Map<String,String> mapp = new LinkedHashMap<String,String>();
    	int count=0;
    	for(String hanzi:list){
    		String pinyin=getFirstSpell(hanzi).toUpperCase();
    		mapDuplicate(hanzi,pinyin,mapp);
    		mapp.put(hanzi, pinyin);
    		count++;
    	}
    	if(mapDup){
        	System.out.println("执行失败：存在重复数据！！");
    	}else{
    		Template t=null; 
        	Configuration conf = new Configuration();
        	conf.setClassForTemplateLoading(XuqiuGen.class, "/fpxuqiu");
        	conf.setDefaultEncoding("UTF-8");
        	//t =conf.getTemplate("text.ftl");
        	t =conf.getTemplate("fupinxuqiu.ftl");
        	HashMap dataMap = new HashMap();
        	dataMap.put("mapp", mapp);
        	File outFile = new File("C:\\Users\\mhy\\Desktop\\text.txt");
        	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
        	t.process(dataMap, out);
        	System.out.println("执行成功,共 " + count + " 条记录");
    	}
    	
    }
    static List getList() throws Exception{
    	File infile= new File(XuqiuGen.class.getResource("/fpxuqiu/xuqiu.txt").getFile());
    	FileReader fr = new FileReader(infile);
    	BufferedReader br = new BufferedReader(fr);
    	List<String> list = new ArrayList<String>();
    	while(true){
    		String hanzi= br.readLine();
    		if( hanzi==null) break;
    		list.add(hanzi);
    	}
    	br.close();
    	return list;
    }
    /**
     * 
     *  确定map是否有重复
     * @return
     */
    public static void mapDuplicate(String key,String value,Map map){
    	if(map.containsKey(key)){
    		System.out.println("字段重复--- "+key+" : " +value);
    		mapDup=true;
    	}
    	else if(map.containsValue(value)){
    		mapDup=true;
    		System.out.println("拼音重复--- "+key+" ： " +value);
    	}
    }
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
 
        char[] input = inputString.trim().toCharArray();
        String output = "";
 
        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }
    /**  
     * 获取汉字串拼音首字母，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音首字母  
     */  
    public static String getFirstSpell(String chinese) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                    if (arr[i] > 128) {   
                            try {   
                                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                                    if (temp != null) {   
                                            pybf.append(temp[0].charAt(0));   
                                    }   
                            } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                    e.printStackTrace();   
                            }   
                    } else {   
                            pybf.append(arr[i]);   
                    }   
            }   
            return pybf.toString().replaceAll("\\W", "").trim();   
    }   
    /**  
     * 获取汉字串拼音，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音  
     */  
    public static String getFullSpell(String chinese) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                    if (arr[i] > 128) {   
                            try {   
                                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);   
                            } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                    e.printStackTrace();   
                            }   
                    } else {   
                            pybf.append(arr[i]);   
                    }   
            }   
            return pybf.toString();   
    }  
}  