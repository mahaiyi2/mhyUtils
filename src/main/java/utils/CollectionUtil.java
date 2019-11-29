package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
}
