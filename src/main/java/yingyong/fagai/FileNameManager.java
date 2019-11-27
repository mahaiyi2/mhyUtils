package yingyong.fagai;

import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FileNameManager {

	public static void main(String[] args) {
		File folder = new File("F:\\fagai\\文档整理\\制度\\市发改委提供");
		File[] files = folder.listFiles();
//		List<File> fileList = new ArrayList<>(files.length);
//		Collections.addAll(fileList,files);
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
		
		for(File file : files){
			System.out.println(file.getName());
		}
//        // 如果这个路径是文件夹
//        if (file.isDirectory()) {
//            // 获取路径下的所有文件
//            File[] files = file.listFiles();
//            for (int i = 0; i < files.length; i++) {
//                // 如果还是文件夹 递归获取里面的文件 文件夹
//                if (files[i].isDirectory()) {
//                    System.out.println("目录：" + files[i].getPath());
//                    getFiles(files[i].getPath());
//                } else {
//                    System.out.println("文件：" + files[i].getPath());
//                }
// 
//            }
//        } else {
//            System.out.println("文件：" + file.getPath());
//        }
	}
}
