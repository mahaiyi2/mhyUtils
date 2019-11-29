package yingyong.fagai;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import utils.CollectionUtil;
import utils.FileUtil;


public class FileNameManager {

	public static void main(String[] args) throws IOException {
		List<String> sFileNames = FileUtil.file2List("C:\\Users\\Administrator\\Desktop\\文档整理临时.txt");
		List<String> dFileNames = FileUtil.file2List("C:\\Users\\Administrator\\Desktop\\文档整理市发改pdf.txt");
//		获取文件名
//		getFileNames();
		//查包含
//		compairNames(sFileNames,dFileNames);
		//查重
		List<String> duplicateNames = CollectionUtil.duplicateString(dFileNames);
		for(String n : duplicateNames){
			System.out.println(n);
		}
	}
	//比较文件名是否包含
	private static void compairNames(List<String> sStrList,List<String> dStrList){
		for(String ss : sStrList){
			if(!dStrList.contains(ss)){
				System.out.println(ss);
			}
		}
		
	}
	
	private static void getFileNames() throws IOException{
//		File folder = new File("F:\\fagai\\文档整理\\完成\\市发改");
		File folder = new File("C:\\Users\\Administrator\\Desktop\\f\\市发改");
		
		File[] files = folder.listFiles();
		//设置按拼音排序
		Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		//设置按文件名排序
		Comparator cp = new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				// TODO Auto-generated method stub
				return cmp.compare(o1.getName(), o2.getName());
			}
		};
		
		Arrays.sort(files,cp);
		List<String> fileNames = new ArrayList<String>();
		
		for(File file : files){
			
			File[] dFiles = file.listFiles();
			if(dFiles.length!=1 || !(dFiles[0].getName().contains(".pdf"))){
				System.out.println(file.getName() + ": 有问题");
			}else{
				//复制文件
				FileUtil.copyFileToDirectory(dFiles[0], new File("C:\\Users\\Administrator\\Desktop\\f\\抽取"));
				if(dFiles[0].getName().contains("关于印发《内蒙古自治区企业守信联合激励实施办法（试行）》的通知"
						+ "（内社信办字2016-11号）")){
					System.out.println(file.getName());
				}

				fileNames.add(dFiles[0].getName().replaceFirst(".pdf",""));
			}
			
//			if(!file.isDirectory()){
//				fileNames.add(file.getName().replaceFirst(".pdf",""));
//			}
		}
//		FileUtil.list2file(fileNames, "C:\\Users\\Administrator\\Desktop\\文档整理临时.txt");
	}
}
