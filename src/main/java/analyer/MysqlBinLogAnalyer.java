package analyer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.FileUtil;

public class MysqlBinLogAnalyer {

	public static final String S_FILE_PATH = "C:\\Users\\ab048704\\Desktop\\s.txt";
	public static final String D_FILE_PATH = "C:\\Users\\ab048704\\Desktop\\d.txt";
	
	//static File sourceFile =  new File(""); 
	static File destFile = new File(D_FILE_PATH); 
	public static void main(String[] args) throws Exception {
		start(S_FILE_PATH);
	}
	
	
	public static void start(String url){
		List<String> list = new ArrayList<String>();
		int blockSize = 11;
		int readCount = 0;
		File infile= new File(url);
    	FileReader fr;
    	BufferedReader br = null;
		try {
			fr = new FileReader(infile);
			br = new BufferedReader(fr);
	    	while(true){
	    		String s= br.readLine();
	    		readCount ++;
	    		if( s==null) break;
	    		list.add(s);
	    		if(readCount%blockSize==0){
	    			doAnalysis(list);
	    			list.removeAll(list);
	    		}
	    		
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
	}
	//解析
	private static void doAnalysis(List<String> list ){
		int c=0;
		String idName="id";
		String idValue="";
		String columnName="laiyuan";
		String columnValue="";
		String pre="";
		StringBuilder sb = new StringBuilder();
		for(String s:list){
			c++;
			s = s.replace("###","");
			if(c==1){
				pre = s;
			}if(c==4){
				idValue=s.replace("@2=","");
			}if(c==5){
				columnValue = s.replace("@3=","");
			}
		}
		
		sb.append(" UPDATE xzspj_slsx_all_new set ")
		.append(columnName).append("=").append(columnValue)
		.append(" where ")
		.append(idName).append("=").append(idValue)
		.append(";");
		String sql = sb.toString();
		
		try {
			FileUtil.appendToFilePerLine(destFile, sql);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
