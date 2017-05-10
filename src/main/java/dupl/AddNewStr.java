package dupl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fpxuqiu.XuqiuGen;

public class AddNewStr {
	
	public static void main(String[] args) throws Exception {
		List<String> list1 = getList1();
		Map map = new LinkedHashMap();
		int i =0;
		for(String s : list1){
//			map.put(s.replaceAll("[:,\\(,\\)]", "").replaceAll("[0-9]*", ""), "");
			map.put( ++ i , s);
			
		}
		
		List<String> list2 = getList2();
		for(String s : list2){
			if(!map.containsValue(s)){
				map.put(++i,s);
			}
		}
		System.out.println("去重后共有记录数 ：" + i);
		for(Object key : map.keySet()){
			System.out.println(map.get(key));
		}
		
	}
	
	static List getList1() throws Exception{
    	File infile= new File(XuqiuGen.class.getResource("/dupl/1.txt").getFile());
    	FileReader fr = new FileReader(infile);
    	BufferedReader br = new BufferedReader(fr);
    	List<String> list = new ArrayList<String>();
    	int i=0;
    	while(true){
    		String hanzi= br.readLine();
    		if( hanzi==null) break;
    		list.add(hanzi);
    		++ i;
    	}
    	br.close();
    	System.out.println("文件1 记录数: "+i);
    	return list;
    }
	
	static List getList2() throws Exception{
    	File infile= new File(XuqiuGen.class.getResource("/dupl/2.txt").getFile());
    	FileReader fr = new FileReader(infile);
    	BufferedReader br = new BufferedReader(fr);
    	List<String> list = new ArrayList<String>();
    	int i=0;
    	while(true){
    		String hanzi= br.readLine();
    		if( hanzi==null) break;
    		list.add(hanzi);
    		++i;
    	}
    	br.close();
    	System.out.println("文件2 记录数: "+i);
    	return list;
    }
	
}
