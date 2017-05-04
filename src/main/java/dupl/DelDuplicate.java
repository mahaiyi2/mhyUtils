package dupl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fpxuqiu.XuqiuGen;

public class DelDuplicate {
	
	public static void main(String[] args) throws Exception {
		List<String> list = getList();
		Map map = new HashMap();
		for(String s : list){
			map.put(s.replaceAll("[:,\\(,\\)]", "").replaceAll("[0-9]*", ""), "");
		}
		for(Object key : map.keySet()){
			
			System.out.println(key);
		}
		
	}
	
	static List getList() throws Exception{
    	File infile= new File(XuqiuGen.class.getResource("/dupl/jss.txt").getFile());
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
}
