package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

public class FileUtil {
	
	public static void map2File(Map<String,String> contentMap,String filePath,String spliter) throws IOException{
		
		File outFile = new File(filePath);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
		for(String key: contentMap.keySet()){
			out.write(key + spliter + contentMap.get(key) );
			out.newLine();
		}
		out.close();
	}
}
