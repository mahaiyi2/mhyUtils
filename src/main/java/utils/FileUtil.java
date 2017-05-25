package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import t2.DetailGen;

public class FileUtil {
	/**
	 * 把map写道文件中
	 * @param contentMap 内容
	 * @param filePath 文件路径
	 * @param spliter key和value之间的内容
	 * @throws IOException
	 */
	public static void map2File(Map<String,String> contentMap,String filePath,String spliter) throws IOException{
		
		File outFile = new File(filePath);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
		for(String key: contentMap.keySet()){
			out.write(key + spliter + contentMap.get(key) );
			out.newLine();
		}
		out.close();
	}
	/**
	 * 逐行读取文件至list
	 * @param url
	 * @return
	 * 
	 */
	public static List<String> file2List(String url){
		List<String> list = new ArrayList<String>();
		File infile= new File(url);
    	FileReader fr;
    	BufferedReader br = null;
		try {
			fr = new FileReader(infile);
			br = new BufferedReader(fr);
	    	while(true){
	    		String s= br.readLine();
	    		if( s==null) break;
	    		list.add(s);
	    		
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				
				if(br!=null) br.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
		return list;
	}
}
