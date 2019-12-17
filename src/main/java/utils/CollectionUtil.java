package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.poifs.filesystem.Entry;

public class CollectionUtil {

	/**
	 * 返回重复的字符串List
	 * @param sList
	 * @return
	 */
	public static List<String> duplicateString(List<String> sList){
		List<String> dupStrList = new ArrayList<String>();
		
		
		LinkedHashMap<String,Integer> map = duplicateCount(sList);
		for(Map.Entry<String,Integer> e : map.entrySet()){
			if(e.getValue() > 1){
				dupStrList.add(e.getKey());
			}
		}
		
		return dupStrList;
	}
	/**
	 * 获取list中各项的数量
	 * @param sList
	 * @return
	 */
	public static LinkedHashMap<String,Integer> duplicateCount(List<String> sList){
		LinkedHashMap<String,Integer> map = new LinkedHashMap<String,Integer>();
		for(String s : sList){
			int count = map.get(s) == null?0:map.get(s);
			map.put(s,++count);
		}
		return map;
	}
	/**
	 * 去除list中的重复项
	 * @param sList
	 * @return
	 */
	public static List<String> removeDuplicateStr(List<String> sList){
		Set set = new TreeSet<String>();
		for(String s :sList){
			set.add(s);
		}
		List<String> destList = new ArrayList<>(set);
		return destList;
	}
	public static void main(String[] args) {
		List<String> strList = FileUtil.file2List("C:\\Users\\Administrator\\Desktop\\文档整理临时.txt");
		LinkedHashMap<String,Integer> map = duplicateCount(strList);
		for(Map.Entry<String,Integer> e : map.entrySet()){
			if(e.getValue() > 1){
				System.out.println(e.getKey() +"  "+ e.getValue());
			}
		}
	}
}
