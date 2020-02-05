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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.jexl2.Main;


public class FileUtil extends FileUtils{
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
	public static List<String> file2List(String url,boolean ignoreSpace){
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
	    		if(ignoreSpace){
	    			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		            Matcher m = p.matcher(s);
		            s = m.replaceAll("");

	    		}
	    		
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
	public static List<String> file2List(String url){
		return file2List(url,false);
	}
	/**
	 * 文
	 * @param list
	 * @throws IOException 
	 */
	public static void list2file(Iterable<String> list,String filePath) throws IOException{
		list2file(list, filePath,false);
	}
	/** 
	 * 
	 * @param list
	 * @param filePath
	 * @param append 是否为追加模式
	 * @throws IOException
	 */
	public static void list2file(Iterable<String> list,String filePath,boolean append) throws IOException{
		File outFile = new File(filePath);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile,append),"utf-8"));
		for(String s: list){
			out.write(s);
			out.newLine();
		}
		out.close();
	}
	/**
	 * 逐行将数据写入文件
	 * @param outFile
	 * @param content
	 * @throws IOException
	 */
	public static void appendToFilePerLine(File outFile,String content) throws IOException{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile,true),"utf-8"));
		out.newLine();
		out.write(content);
		out.close();
	}
	
	
}
