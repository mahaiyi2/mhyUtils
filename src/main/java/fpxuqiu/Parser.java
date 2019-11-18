package fpxuqiu;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


import fpxuqiu.XuqiuGen;
//import us.codecraft.webmagic.selector.Html;

public class Parser {
	public static void main(String[] args) {
		
		execute();
		
	}
	
	
	static String getXML(){
		File file = new File(Parser.class.getResource("/fpxuqiu/xml.txt").getFile());
		  BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(file));
			String content = "";
			  StringBuilder sb = new StringBuilder();
			  
			  while(content != null){
			   content = bf.readLine();
			   if(content == null){
			    break;
			   }
			   content.replaceAll("\"", "\\\"");
			   sb.append(content.trim());
			  }
			  
			bf.close();
			 return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	 static void execute(){
		String xml=getXML();
//		Html h = Html.create(xml);
    	
		List<String> list = null;//h.xpath("//label/text()").all();//解析内容
    	
    	List<String> hzList = new ArrayList<String>();
    	int i=0;
    	for(String s : list){
    		s=s.replaceAll("[:,' ',' ',\\r,\\n]","");
    		if("".equals(s.trim()))continue;
    		System.out.println(s);
    		hzList.add(s);
    		i++;
    		
    	}
    	System.out.println("解析完成,共 "+i+" 条记录");
    	//生成汉字 拼音首字母
    	try {
			XuqiuGen.fupinxuqiu(hzList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
