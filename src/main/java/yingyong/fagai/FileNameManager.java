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


	//比较文件名是否包含
	private static void compairNames(List<String> sStrList,List<String> dStrList){
		int count = 0;
		for(String ss : sStrList){
			if(!dStrList.contains(ss)){
				System.out.println(ss);
				count ++;
			}
		}
		System.out.println("共有 "+count+" 条新数据");
		
	}
	/**
	 * 获取文件名
	 * @throws IOException
	 */
	private static void getFileNames() throws IOException{
//		File folder = new File("F:\\fagai\\文档整理\\完成\\市发改");
//		File folder = new File("C:\\Users\\Administrator\\Desktop\\f\\市发改");
		File folder = new File("F:\\fagai\\文档整理\\备忘录\\信用内蒙古");
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
		//排序操作
		Arrays.sort(files,cp);
		List<String> fileNames = new ArrayList<String>();
		
		for(File file : files){
			fileNames.add(file.getName().replaceAll("\\..*", ""));
			if(true)continue;
			File[] dFiles = file.listFiles();
			
			for(File dFile:dFiles){
				fileNames.add(dFile.getName().replaceAll("\\..*", ""));
			}
		}
		FileUtil.list2file(fileNames, "C:\\Users\\Administrator\\Desktop\\临时.txt");
	}
	/**
	 * 抽取文件(复制	)
	 * @throws IOException 
	 */
	public static void extractFiles() throws IOException{
		File folder = new File("F:\\fagai\\文档整理\\完成\\制度文件\\市发改");
		File[] files = folder.listFiles();
		for(File file:files){
			File[] dFile = file.listFiles();
			if(dFile == null || dFile.length!=1){
				System.out.println(file.getName()+"有问题");
			}else{
				FileUtil.copyFileToDirectory(dFile[0], new File("C:\\Users\\Administrator\\Desktop\\t"));
			}
		}
	}
	public static void main(String[] args) throws IOException {
		//查看是否包含
		List<String> sFileNames = FileUtil.file2List("C:\\Users\\Administrator\\Desktop\\全部受理事项库存.txt",true);
		List<String> dFileNames = FileUtil.file2List("C:\\Users\\Administrator\\Desktop\\全部受理事项新.txt",true);
		compairNames(dFileNames,sFileNames);
/*****************************************************************************/
		//去重复
//		List<String> dList = CollectionUtil.removeDuplicateStr(sFileNames);
//		FileUtil.list2file(dList, "C:\\Users\\Administrator\\Desktop\\临时.txt");
/*****************************************************************************/
		//		获取文件名
//		getFileNames();
		//查包含
/*****************************************************************************/
		//查重
//		List<String> duplicateNames = CollectionUtil.duplicateString(dFileNames);
//		for(String n : duplicateNames){
//			System.out.println(n);
//		}
//		
/*****************************************************************************/
		//抽取文件到一个目录
//		extractFiles();
		
		
		
	}
}
